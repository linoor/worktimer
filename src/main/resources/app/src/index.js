import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';
import Commute from './Commute.js';
import Starter from './Starter.js';

// using an ES6 transpiler, like babel
import { Router, Route, browserHistory, IndexRoute} from 'react-router'

ReactDOM.render((
    <Router history={browserHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={Starter} />
            <Route path="/commutes/:commuteId" component={Commute} />
        </Route>
    </Router>
), document.getElementById('root'));

