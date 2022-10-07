import logo from './logoDark.png';
import girl from './girlCropped.png';
import record from './record.png';
import './App.css';
import NavBar from './NavBar';
import './styles.css';
import HomePage from './Pages/HomePage';
import Playlists from './Pages/Playlists';
import RegisterPage from './Pages/RegisterPage';
import {Route, Routes} from "react-router-dom";

export default function App() {
  return (
    <div className="App">
      
      <header className="App-header">
        <img src={logo} className="logo" alt="logo" />

      </header>
      <NavBar />
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='/home' element={<RegisterPage />} />
        <Route path='/playlists' element={<Playlists />} />
      </Routes>
      {/* <Component /> */}
      <body>
        <div className="record-contains">
          <span className="circle">
            <img src={record} className="App-logo" alt='record'/>
          </span>
          </div>
        
        <img src={girl} className="img-girl" alt="girl"/>
        
      </body>
    </div>
  );
}

// export default App
