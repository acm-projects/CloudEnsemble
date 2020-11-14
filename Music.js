/*export default function Collab() {
    const [saved, updateSaved] = useState(filteredClips);
    return(
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
    )

    function handleOnDragEnd(result)
    {
    const items = Array.from(saved);
    const [reorderedItems] = items.splice(result.source.index,1);
    items.splice(result.destination.index, 0, reorderedItems);
    updateSaved(items);
    
    }

  }
  
*/