import React, { Component } from 'react'
import {HeaderItems} from "./HeaderItems"
import CloudLogo from "./cloud-ensemble-logo.png"
import './Header.css'
import Item from 'antd/lib/list/Item'
import UserProfile from './UserProfile'
import UserTracks from './UserTracks'
import Collab from './Collab.js'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

export default function Header(){

return(
<nav className="HeaderItems">
    <div className = "header-logo"> <img src={CloudLogo} alt="Cloud Ensemble Logo"/>

    </div>
    <Router>
    <ul className = 'header-menu'>
        {HeaderItems.map((item,index) => {
            return(
                <li>
                    <a className = {item.cName} href ={item.url}> 
                    {item.title}
                    </a>
                    <Link to={item.href}></Link>
                </li>

            )

        })}
        
    </ul>
    <Switch>
          <Route path="/browse">
            <Browse />
          </Route>
          <Route path="/create">
            <Collaborate/>
          </Route>
          <Route path="/profile">
            <Profile />
          </Route>
        </Switch>
        </Router>
</nav>

)
}
function Browse() {
    return <UserTracks></UserTracks>;
  }
  
  function Profile() {
    return <UserProfile></UserProfile>;
  }
  function Collaborate() {
    return <Collab></Collab>;
  }


