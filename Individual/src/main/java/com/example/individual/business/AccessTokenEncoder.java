package com.example.individual.business;

import com.example.individual.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
