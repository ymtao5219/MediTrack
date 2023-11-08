import React from 'react';
import ReactDOM from 'react-dom';
import './index.css'; // Importing a base CSS file
import App from './App'; // Import the main App component
import reportWebVitals from './reportWebVitals'; // This utility is for measuring performance

// If you use a service worker and you've set up one
// import * as serviceWorker from './serviceWorker';

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you're not interested in measuring performance, you can safely remove this.
reportWebVitals();

// If you use a service worker and you've set up one, you can register it as well
// serviceWorker.unregister();
