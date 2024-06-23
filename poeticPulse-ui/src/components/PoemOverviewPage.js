import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import poemService from '../services/poemService';
import '../styles/PoemOverviewPage.css';

function PoemOverviewPage() {
  const [poems, setPoems] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    poemService.getAll()
      .then(data => setPoems(data))
      .catch(error => console.log(error));
  },);

  const handleAdd = () => {
    navigate('/poem');
  };

  const handleUpdate = () => {
    if (selectedItem === null) {
      alert('Please select a poem to update.');
    } else {
      navigate(`/poem/${selectedItem.id}`);
    }
  };

  const handleDelete = () => {
    poemService.remove(selectedItem.id)
      .then(response => {
        if (response.ok) {
          const updatedPoems = poems.filter(poem => poem.id !== selectedItem.id);
          setPoems(updatedPoems);
        } else {
          throw new Error('Failed to delete poem');
        }
      })
      .catch(error => console.log(error));
  };

  const handleItemClick = (item) => {
    setSelectedItem(item)
  };

  return (
    <div class="overviewBox">
      <div class="title spacing">Poetic Pulse</div>
      <div>
      <div>Stored Poems: (select a poem for further actions)</div>
        <button onClick={handleAdd}>Add new Poem</button>
        <button onClick={handleUpdate}>Update Poem</button>
        <button onClick={handleDelete}>Delete Poem</button>
        <ul id="nav">
          {poems.map(item => (
            <li id={item.id} key={item.id} className={selectedItem?.id === item.id ? "selected" : ""} onClick={() => handleItemClick(item)}>
              {item.title}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default PoemOverviewPage;