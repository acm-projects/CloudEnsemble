
import React, { Component } from 'react'
import './UserTracks.css'
import Pic3 from "./UT-AlbumArt3.png";
import clipsList from "./clipslist.json";
import {
    Input,
    Card,
    CardBody,
    CardTitle
  } from "mdbreact";
import Header from './Header/Header';
class UserTracks extends Component{

  state = {
    search: ""
  };

  renderClips = clips => {
    const { search } = this.state;

    /*if( search !== "" && country.name.toLowerCase().indexOf( search.toLowerCase() ) === -1 ){
        return null
    }*/

    return (
      <div className="col-md-3" style={{ marginTop: "20px" }}>
        <Card>
          <CardBody>
            <p className="card-body">
              <img
                src={Pic3}
                alt={clips.name}

              />
                <CardTitle title={clips.name}>
                {clips.name.substring(0, 15)}
                {clips.name.length > 15 && "..."}
              </CardTitle> 
            </p>
          </CardBody>
        </Card>
      </div>
    );
  };

  onchange = e => {
    this.setState({ search: e.target.value });
  };

  render() {
    const { search } = this.state;
    const filteredClips = clipsList.filter(clips => {
      if(clips.name.toLowerCase().indexOf(search.toLowerCase()) !== -1)
        return clips.name.toLowerCase().indexOf(search.toLowerCase()) !== -1;
      if(clips.user.toLowerCase().indexOf(search.toLowerCase()) !== -1)
        return clips.user.toLowerCase().indexOf(search.toLowerCase()) !== -1;  

    });
    return (
        <div className = "Header"><Header></Header>
        <div className="flyout">
          
            <div className="container">
              <div className="row">
                <div className="col">
                  <Input
                    label="Filter by:"
                    icon="search"
                    onChange={this.onchange}
                  />
                </div>
                <div className="col" />
              </div>
              <div className="row">
            <div className = "clips">
                {filteredClips.map(clips => {
                  return this.renderClips(clips);
                })}
                </div>
              </div>
              </div>

           </div>
           </div>
);
}
}
export default UserTracks;