package com.example.individual.business.impl;

import com.example.individual.business.SearchUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.UserConverter;
import com.example.individual.domain.GetSearchResultsRequest;
import com.example.individual.domain.GetSearchResultsResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.domain.User;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class SearchUseCaseImpl implements SearchUseCase {
    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;
    @Override
    public GetSearchResultsResponse getResults(GetSearchResultsRequest request) {
        //Searches for results which contain the search item
        List<Playlist> playlistResults = playlistRepository.findByTitleContainsIgnoreCase(request.getSearchItem()).stream().map(PlaylistConverter::convertToPlaylist).toList();
        Map<Playlist, Integer> calculated = new HashMap<>();
        /*Calculates which results are closest to the input value;
        Uses the LevenshteinDistance algorithm to calculate the number of changes(distances)
        needed so the result can equal the input*/
        for (Playlist playlist : playlistResults) {
            calculated.put(playlist,LevenshteinDistance.Compute(request.getSearchItem(), playlist.getTitle()));
        }
        //Sorts out the results using the value entry
        Map<Playlist, Integer> sortedResults = sortByValue(calculated);
        List<Playlist> resultsPlaylists = new ArrayList<>();
        for (Map.Entry<Playlist,Integer> entry : sortedResults.entrySet())
            resultsPlaylists.add(entry.getKey());
        System.out.println("sending results: " + resultsPlaylists);

        List<User> userResults = userRepository.findAllByWholeName(request.getSearchItem()).stream().map(UserConverter::convertToUser).toList();
        Map<User, Integer> calculatedUsers = new HashMap<>();
        for (User user : userResults) {
            calculatedUsers.put(user,LevenshteinDistance.Compute(request.getSearchItem(), user.getFName() + " " + user.getLName()));
        }
        Map<User, Integer> sortedUsers = sortByValue(calculatedUsers);
        List<User> resultsUsers = new ArrayList<>();
        for(Map.Entry<User, Integer> entry : sortedUsers.entrySet())
           resultsUsers.add((entry.getKey()));

        return  GetSearchResultsResponse.builder().playlistResults(resultsPlaylists).userResults(resultsUsers).build();
    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            //Limits the results to 10 to prevent overload
            if(result.size() < 10) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}


