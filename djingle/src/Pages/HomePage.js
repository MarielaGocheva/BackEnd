import axios from "axios";
import { useEffect, useState } from "react";

function HomePage() {

const [posts, setPosts] = useState([]);

    useEffect(()=>{
        axios.get("http://localhost:8080/playlists")
        .then((response) => {
            console.log(response.data.playlists);
            setPosts(response.data.playlists);
        });
    },[]);

    return (
      <div className="HomePage">
        <body>
          <div className="menu">
           <p>Home page</p>
           <h1>Playlist Id is 
           </h1>
           {posts.map(element => ( <p>{element.id}</p>))}
            </div>
        </body>
      </div>
    );
  
    }
  export default HomePage;