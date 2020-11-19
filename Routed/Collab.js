import React, { useState, Component } from 'react'
import UserTracks from './UserTracks.js'
import './Collab.css'
import {DragDropContext, Draggable, Droppable, } from 'react-beautiful-dnd'
import Pic3 from "./UT-AlbumArt3.png";
import clipsList from "./clipslist.json";
import Script from './script.js'
  const filteredClips = clipsList
export default function Collab() {
    const [saved, updateSaved] = useState(filteredClips);
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
                      <audio controls>
                        <source src = {url} type="audio/mp3"></source> </audio>
                     </div>
                      <p>
                        { name }
                      </p>
                      <button>Add Clip to Rack</button>
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
</div>
</div>


    )

    function handleOnDragEnd(result)
    {
    const items = Array.from(saved);
    const [reorderedItems] = items.splice(result.source.index,1);
    items.splice(result.destination.index, 0, reorderedItems);
    updateSaved(items);
    
    }

  }

  
  
  


