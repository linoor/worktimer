import React, { Component } from 'react';
import { Router, browserHistory } from 'react-router'
import './App.css';
import Timer from './Timer.js';
import $ from 'jquery';

class App extends Component {
    constructor(props, context) {
        super(props, context);

        this.state = {
            secondsElapsed: 0,
            timestamp: null,
            started: false,
            commuteHref: null
        };

        this.startTimer = this.startTimer.bind(this, this.state.secondsElapsed);
        this.tick = this.tick.bind(this);
        this.endtimer = this.endTimer.bind(this);
        this.updateTimer = this.updateTimer.bind(this);
  }
    tick() {
        this.setState({
            secondsElapsed: this.state.secondsElapsed + 1
        });
    }

    startTimer() {
        this.interval = setInterval(this.tick, 1000);
    }

    endTimer() {
        clearInterval(this.interval);
    }

    handleClick() {
       if (!this.state.started) {
           $.post('http://localhost:8080/api/commutes/start', (result) => {
               this.setState({
                   timestamp: result.timestamp,
                   commuteHref: result._links.commute.href,
                   started: true
               });
               this.startTimer();
           });
       } else if (this.state.secondsElapsed > 0) {
           $.post(this.state.commuteHref + "/stop", (result) => {
               this.endTimer();
               this.setState({
                   started: false
               });
               let path = this.state.commuteHref.replace("/api", "").split("/").splice(-2).join("/");
               //this.props.history.push(path, {new: true});
               browserHistory.push({
                   pathname: path,
                   search: '?new=true'
               });
           })
       }
    }

    updateTimer(seconds) {
        this.setState({
            secondsElapsed: seconds
        })
    }

    render() {
    return (
      <div className="App">
          <Timer seconds={this.state.secondsElapsed} started={this.state.started} />
          {this.props.children && React.cloneElement(this.props.children, {
             started: this.state.started,
              handler: this.handleClick.bind(this),
              updateTimer: this.updateTimer
          })}
      </div>
    );
    }
}

export default App;
