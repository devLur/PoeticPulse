import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './utils/reportWebVitals';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; // Import the Routes component
import PoemOverviewPage from './components/PoemOverviewPage'; // Import the StartPage component
import PoemPage from './components/PoemPage';
import './styles/index.css';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/" element={<PoemOverviewPage />} />
        <Route path="/poem" element={<PoemPage />} />
        <Route path="/poem/:id" element={<PoemPage />} />
      </Routes>
    </Router>
  </React.StrictMode>
);

reportWebVitals();
