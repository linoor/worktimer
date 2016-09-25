import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';

// using an ES6 transpiler, like babel
import { Router, Route, Link, browserHistory} from 'react-router'

ReactDOM.render((
    <Router history={browserHistory}>
        <Route path="/" component={App}>
        </Route>
    </Router>
), document.getElementById('root'));

