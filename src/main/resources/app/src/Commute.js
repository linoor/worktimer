/**
 * Created by linoor on 9/25/16.
 */


import React, { Component } from 'react';
//import { Row } from "react-bootstrap";
import Timer from './Timer.js';
import Edit from './Edit.js';
import { Row } from "react-bootstrap";

class Commute extends Component {
    constructor(props) {
        super(props);

        this.state = {
            secondsElapsed: 0,
            note: "",
            type: "",
            places: []
        }
    }

    componentDidMount() {
        $.get('http://localhost:8080/api/places', (results) => {
            this.setState({
                places: results._embedded.places.map((result) => result.name)
            });
        })
    }

    render() {
        return (
            <div>
                <Row>
                    <Edit note={this.state.note} type={this.state.type} places={this.state.places} />
                </Row>
            </div>
        )
    }
}

export default Commute;