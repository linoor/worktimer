import React, { Component } from 'react';
import './App.css';
import Timer from './Timer.js';

class App extends Component {
  render() {
    return (
      <div className="App">
          <Timer seconds={13753}/>
      </div>
    );
  }
}

export default App;
