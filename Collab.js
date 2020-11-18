import React, { useState, Component } from 'react'
import UserTracks from './UserTracks.js'
import './Collab.css'
import {DragDropContext, Draggable, Droppable, } from 'react-beautiful-dnd'
import Pic3 from "./UT-AlbumArt3.png";
import clipsList from "./clipslist.json";
import Epic from "./bensound-epic.mp3"
import Ambient from "./bensound-betterdays.mp3"
import Dubstep from "./bensound-dubstep.mp3"
import ScriptTag from 'react-script-tag'

  const filteredClips = clipsList
export default function Collab() {
    const rack = []

    const [saved, updateSaved] = useState(filteredClips);
    function handleOnDragEnd(result)
    {
     
    const items = Array.from(saved);
    const [reorderedItems] = items.splice(result.source.index,1);
    items.splice(result.destination.index, 0, reorderedItems);
    updateSaved(items);
    }
    return(
      <div>
      <DragDropContext onDragEnd={handleOnDragEnd}>
    
      <Droppable droppableId="saved">
        {(provided) => (
          <ul className="saved" {...provided.droppableProps} ref={provided.innerRef}>
            {saved.map(({name, url}, index) => {
              return (
                <Draggable key={name} draggableId={name} index={index}>
                  {(provided) => (
                    <li ref={provided.innerRef} {...provided.draggableProps} {...provided.dragHandleProps}>
                      <div className="audio">
                      <audio>
                        <source crossOrigin = 'anonymous' src = {url} type="audio/mp3" ></source> </audio>
                        <img src = {Pic3}></img> 
                     </div>
                      <p>
                        { name }
                      </p>
                      
                    </li>
                  )}
                  
                </Draggable>
              );
            })}
            {provided.placeholder}
          </ul>
        )}
      </Droppable>
      </DragDropContext>     
   

    <div>

  <div className = 'Rack'>
  <div id="volume">
    <h3>Volume</h3>
    <input id="volume-range" type="range"></input>
    <p id="volumep">50</p>
  </div>

  <button id="start">Start</button>
  <button id="test">Test</button>

  <div id="add-buttons"></div>
  <div id="select-buttons"></div>
  <audio crossOrigin="anonymous" src={Dubstep}></audio>
  <audio crossOrigin="anonymous" src={Epic}></audio>
  <audio crossOrigin="anonymous" src={Ambient}></audio>

 
</div>
</div>
<ScriptTag type="text/javascript" src="./script.js" />
</div>



    )


  }

  
  
  


