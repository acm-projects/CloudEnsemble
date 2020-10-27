import React, { Component } from 'react'
import {Modal, Button} from 'antd'
import './ProfilePicChanger.css'

class ProfilePicChanger extends Component{
  constructor(props){
    super(props);
    this.state={
      visible: false,
      imagesArray: [props.pic1,props.pic2]
    }
  }  
  state = { visible: false };
  
    showModal = () => {
      this.setState({
        visible: true,
      });
    };
  
    handleOk = e => {
      console.log(e);
      this.setState({
        visible: false,
      });
    };
  
    handleCancel = e => {
      console.log(e);
      this.setState({
        visible: false,
      });
    };
  render(){
    const imageMapper = this.state.imagesArray.map((image, index) =>     {
        return (
          <img src={image}
          onClick={() =>this.props.handleImageChange(image)}
          height = "48px"
          />
        )

    })

      return(
      <div className = "ProfilePicChanger">
        <div className = "Edit-Button">
        <Button type="primary" onClick={this.showModal}>
          Edit Picture
        </Button>
        </div>
        <Modal
          title="Select a Picture"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          {imageMapper}
        </Modal>

              
          </div>
      )
  }
} 

export default ProfilePicChanger