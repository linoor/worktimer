import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';
import Commute from './Commute.js';

// using an ES6 transpiler, like babel
import { Router, Route, browserHistory} from 'react-router'

ReactDOM.render((
    <Router history={browserHistory}>
        <Route path="/" component={App}>
            <Route path="/commutes/:commuteId" component={Commute} />
        </Route>
    </Router>
), document.getElementById('root'));

