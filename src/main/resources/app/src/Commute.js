/**
 * Created by linoor on 9/25/16.
 */


import React, { Component } from 'react';
//import { Row } from "react-bootstrap";
import Edit from './Edit.js';
import { Row } from "react-bootstrap";
import $ from 'jquery';

class Commute extends Component {
    constructor(props) {
        super(props);

        this.state = {
            secondsElapsed: 0,
            note: "",
            type: "",
            places: [],
            from: null,
            to: null,
            message: ''
        };

        this.update = this.update.bind(this);
        this.updatePlace = this.updatePlace.bind(this);
    }

    update(field) {
        return (e) => {
            let state = {};
            state[field] = e.target.value;
            this.setState(state);
        }
    }

    updatePlace(name) {
        return (e) => {
            let state = {};
            let place = this.state.places.filter((place) => place.name === e.target.value)[0];
            state[name] = place;
            this.setState(state);
        }
    }

    componentDidMount() {
        $.get('http://localhost:8080/api/places', (results) => {
            const places = results._embedded.places;
            this.setState({
                places: places,
                from: places[0],
                to: places[1]
            });
        })
    }

    render() {
        return (
            <div>
                <Row>
                    <Edit note={this.state.note}
                          type={this.state.type}
                          places={this.state.places}
                          from={this.state.from}
                          to={this.state.to}
                          commuteHref={this.props.commuteHref}
                          updatePlace={this.updatePlace}
                          updateHandler={this.update} />
                </Row>
            </div>
        )
    }
}

export default Commute;