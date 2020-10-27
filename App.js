import React, {Component}from 'react';
import Header from "./Components/Header/Header";
import './App.css';
import 'antd/dist/antd.css';
import {Modal, Avatar } from 'antd';
import ProfilePicChanger from "./Components/Profile-Pics/ProfilePicChanger";
import Pic1 from "./Components/Profile-Pics/UT-AlbumArt1.png";
import Pic2 from "./Components/Profile-Pics/UT-AlbumArt2.png";

import UserTracks from './Components/UserTracks';
import UserProfile from './Components/UserProfile';


class App extends Component {
  render(){
        
    return(
        <UserTracks></UserTracks>
    )
    }
    }



export default App;
