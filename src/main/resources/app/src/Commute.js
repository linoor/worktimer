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
            message: '',
            commuteHref: null
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
        const commuteHref = `http://localhost:8080/api/commutes/${this.props.params.commuteId}`;
        this.setState({
            commuteHref: commuteHref
        });
        $.get(commuteHref, (result) => {
            this.setState({
                note: result.note,
                type: result.type
            })
        });

        $.get(commuteHref + '/measurements/elapsed', (result) => {
          this.props.updateTimer(result.content);
        });

        $.get('http://localhost:8080/api/places', (results) => {
            const places = results._embedded.places;
            this.setState({
                places: places,
            });
        });

        $.get(commuteHref + '/fromPlace', (result) => {
            this.setState({
                from: result || this.state.places[0]
            });
        });

        $.get(commuteHref + '/toPlace', (result) => {
            this.setState({
                to: result || this.state.places[1]
            })
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
                          commuteHref={this.state.commuteHref}
                          updatePlace={this.updatePlace}
                          updateHandler={this.update} />
                </Row>
            </div>
        )
    }
}

export default Commute;