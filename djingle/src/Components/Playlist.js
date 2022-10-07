import { useState } from "react";

export default function getAllPlaylists(){
    //const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[id,setId]=useState('')
    const[duration,setDuration]=useState('')
    const[userId, setUserId] = useState('');
    const[playlists,setPlaylists]=useState([])
    // const classes = useStyles();

//   const handleClick=(e)=>{
//     e.preventDefault()
//     const playlist ={id,userId, duration}
//     console.log(playlist)
//     fetch("http://localhost:8080/student/add",{
//       method:"POST",
//       headers:{"Content-Type":"application/json"},
//       body:JSON.stringify(student)

//   }).then(()=>{
//     console.log("New Student added")
//   })
// }

// useEffect(()=>{
//   fetch("http://localhost:8080/playlists")
//   .then(res=>res.json())
//   .then((result)=>{
//     setPlaylists(result);
//   }
// )

// },[])

// const [val, setVal] = useState();

// const getAnswer = async () => {
//   const { data } = await axios("https://localhost:8080/playlists");
//   setVal(data.answer);

//   useEffect(() => {
//     getAnswer();
//   }, []);

    
    return (
        <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1 style={{color:"blue"}}><u>Add Playlist</u></h1>

    {/* <form className={classes.root} noValidate autoComplete="off">
    
      <TextField id="outlined-basic" label="Playlist Name" variant="outlined" fullWidth 
      value={name}
      onChange={(e)=>setName(e.target.value)}
      />
      <TextField id="outlined-basic" label="Playlist Adress" variant="outlined" fullWidth
      value={address}
      onChange={(e)=>setAddress(e.target.value)}
      />
      <Button variant="contained" color="secondary" onClick={handleClick}>
  Submit
</Button>
    </form> */}
   
    </Paper>
    <h1>Playlists</h1>

    {/* <Paper elevation={3} style={paperStyle}>

      {playlists.map(playlist=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={playlist.id}>
         Id:{playlist.id}<br/>
         UserId:{playlist.userId}<br/>
         Duration:{playlist.duration}

        </Paper>
      ))
}
    </Paper> */}
    </Container>
    );
}