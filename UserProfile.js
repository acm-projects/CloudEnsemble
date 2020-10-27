import React, {Component}from 'react';
import Header from "./Header/Header";
import './UserProfile.css';
import 'antd/dist/antd.css';
import {Modal, Avatar } from 'antd';
import ProfilePicChanger from "./Profile-Pics/ProfilePicChanger";
import Pic1 from "./Profile-Pics/UT-AlbumArt1.png";
import Pic2 from "./Profile-Pics/UT-AlbumArt2.png";
import Pic3 from "./Profile-Pics/UT-AlbumArt3.png";
import UserTracks from './UserTracks';


class UserProfile extends Component {
  constructor(props){
    super(props);
    this.state={
      profileImage: ''
    }
  }

  handleImageChange = (profileImage) => {
    this.setState({
      profileImage
    })
  }
  render(){
  return (
      <div> <Header></Header>
    <div className="UserProfile">
      <div className = "Avatar">
      <Avatar size={512} icon="user" src={this.state.profileImage}/>
      <ProfilePicChanger handleImageChange={this.handleImageChange} pic1={Pic1} pic2={Pic2} pic3={Pic3}/>
      <div className = "UserName">
      <h1> Username: </h1> </div>
      <div className = "About">
      <h3> About:</h3>
      <p>This a section for the user to describe a little bit about themselves, as well as their music preferences in listening and in creating!</p>
      </div>
      </div>
    <div className ="Tracks">
    <h4>Top Tracks:</h4>  
    </div>
      <div className="Tracks-Section">
   <UserTracks></UserTracks>
     </div>
    <div className ="Clips">
    <h4>Top Clips:</h4>  
    </div>
      <div className="Clips-Section">
  
     </div>
     </div>
     </div>
  );
}
}

export default UserProfile;