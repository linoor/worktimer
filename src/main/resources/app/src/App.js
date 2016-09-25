import React, { Component } from 'react';
import './App.css';
import Timer from './Timer.js';
import Starter from './Starter.js';

class App extends Component {


  render() {
    return (
      <div className="App">
          <Timer seconds={13753} started={false}/>
          <Starter started={false} />
      </div>
    );
  }
}

export default App;
