import {Link, useMatch, useResolvedPath} from "react-router-dom";

export default function NavBar(){
    return <nav className="nav">
        <div className="menu-container">
        <ul>
            <CustomLink to="/home">Home</CustomLink>
            <CustomLink to="/playlists">Playlists</CustomLink>          
            <CustomLink to="/recentlyplayed">Recently played</CustomLink>
            <CustomLink to="/charts">Charts</CustomLink>
        </ul>
        </div>
       
    </nav>
}

function CustomLink({to, children, ...props}){
    const resolvedPath = useResolvedPath(to)
    const isActive = useMatch({ path: resolvedPath.pathname, end: true})
return (
    <li className={isActive? "active" : ""}>
        <Link to ={to} {...props}>{children}</Link>
    </li>
)
}