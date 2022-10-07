import { useState } from "react";

export default function getAllPlaylists(){
    const[id,setId]=useState('')
    const[duration,setDuration]=useState('')
    const[userId, setUserId] = useState('');
    const[playlists,setPlaylists]=useState([])
    
    return (
        <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1 style={{color:"blue"}}><u>Add Playlist</u></h1>
    </Paper>
    <h1>Playlists</h1>
    </Container>
    );
}