import React, { Component } from 'react';
import { browserHistory } from 'react-router'
import './App.css';
import Timer from './Timer.js';
import $ from 'jquery';
import Starter from './Starter.js';

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

    componentWillMount() {
        // if there is a commute that is already running, redirect to it
        $.get('http://localhost:8080/api/measurements', (results) => {
            const lastCommute = results._embedded.measurements.slice(-1)[0];
            if (lastCommute.type === 'START') {
                $.get(lastCommute._links.commute.href, (commuteResult) => {
                    let path = commuteResult._links.self.href
                        .replace("/api", "")
                        .split("/")
                        .splice(-2)
                        .join("/");
                    browserHistory.push({
                        pathname: path,
                        search: ''
                    });
                    this.setState({
                        started: true,
                        commuteHref: commuteResult._links.self.href
                    });
                    this.startTimer();
                });
            }
        })
    }

    startTimer() {
        this.interval = setInterval(this.tick, 1000);
    }

    endTimer() {
        clearInterval(this.interval);
    }

    handleClick() {
       if (!this.state.started) {
           if (this.state.secondsElapsed === 0) {
               $.post('http://localhost:8080/api/commutes/start', (result) => {
                   this.setState({
                       timestamp: result.timestamp,
                       commuteHref: result._links.commute.href,
                       started: true
                   });
                   this.startTimer();
               });
           } else {
               $.post(this.state.commuteHref + '/continue', (result) => {
                   this.setState({
                       started: true
                   });
                   this.startTimer();
               });
           }
       } else if (this.state.secondsElapsed > 0) {
           $.post(this.state.commuteHref + "/stop", (result) => {
               this.endTimer();
               this.setState({
                   started: false
               });
               let path = this.state.commuteHref.replace("/api", "").split("/").splice(-2).join("/");
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
          <Starter started={this.state.started}
                   handler={this.handleClick.bind(this)}
                   updateTimer={this.updateTimer}
                   seconds={this.state.secondsElapsed} />
          {this.props.children && React.cloneElement(this.props.children, {
             started: this.state.started,
              handler: this.handleClick.bind(this),
              updateTimer: this.updateTimer,
          })}
      </div>
    );
    }
}

export default App;
