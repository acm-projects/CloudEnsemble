import React from 'react';
import microphone from './microphone_cropped_1.png';
import './App.css';
import CloudLogo from "./Cloud_Ensemble_logo-01.png";


function App() {
  return (
    <div className="App">
      <header className="App-header">
      <div className = "header-logo"><img src={CloudLogo} alt="Cloud Ensemble Logo"/>
      <div className = "menu-icon">
      </div>
      </div>
      </header>

      <body className="body">
      <div>
      <img src={microphone} className="Main-picture" alt="microphone"/>
      </div>
      <button className = "btn-outline">LOG IN</button>

      <div>
      <h1 className= "text-title">
          Welcome to <br></br>Cloud Ensemble!
      </h1>
      <h4 className= "text-sub">
          Upload clips, collaborate, and mix + match <br></br> to make your next track!
      </h4>
      <button className ="btn-main"> SIGN UP </button>
      <h4 className="text-sub-center">
        Making music together is as easy as 1-2-3!
      </h4>
      </div>
      </body>

      <thirds className = "text-thirds">
        <p>
          <h1>1</h1>
          Create an account to access <br></br>
          your dashboard and upload <br></br>
          your clips.
        </p>

        <p>
          <h1>2</h1>
          Browse and use different <br></br>
          clips to create your newest <br></br>
          track.
        </p>

        <p>
          <h1>3</h1>
          Collaborate, share with<br></br>
          friends, and review other <br></br>
          people's tracks.
        </p>
      </thirds>

      <div>
        <h4 className = "text-sub-center-2">
          Ready to get started?
        </h4>
        <button className = "btn-sub-main">SIGN UP
        </button>
        <h5 className = "text-sub-center-3">
          Already have an account? Log in! 
        </h5>
        </div>

    </div>
    
  );
}

export default App;
