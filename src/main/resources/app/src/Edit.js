/**
 *
 * Created by linoor on 9/25/16.
 */

import React, { Component } from 'react';
import { Well, FormGroup, FormControl, ControlLabel, Button } from "react-bootstrap";
import $ from 'jquery';

class Edit extends Component {
    constructor(props) {
        super(props);

        this.updateCommute = this.updateCommute.bind(this);
    }

    getValidationState() {
       if (this.props.from === this.props.to) {
           console.log(this.props.from);
           console.log(this.props.to);
           return 'error';
       } else {
           return 'success';
       }
    }

    updateCommute(e) {
        e.preventDefault();
        $.ajax({
            url: this.props.commuteHref,
            type: 'PUT',
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify({
                note: this.props.note,
                type: this.props.type
            }),
            success: (result) => {
                this.setState({
                    message: 'The commute has been updated'
                });
            }
        });
    }

    render() {

        const placesFrom = this.props.places.map((place) => {
            if (this.props.from === place) {
                return <option key={place} value={place} selected>{place}</option>;
            } else {
                return <option key={place} value={place}>{place}</option>;
            }
        });
        const placesTo = this.props.places.map((place) => {
            if (this.props.to === place) {
                return <option key={place} value={place} selected>{place}</option>;
            } else {
                return <option key={place} value={place}>{place}</option>;
            }
        });

        return (
            <Well>
                <form onSubmit={this.updateCommute}>
                    <FormGroup bsSize="large" validationState={this.getValidationState()}>
                        <ControlLabel>From</ControlLabel>
                        <FormControl componentClass="select" placeholder="select"
                                     onChange={this.props.updateHandler('from')}>
                            {placesFrom}
                        </FormControl>
                    </FormGroup>
                    <FormGroup bsSize="large" validationState={this.getValidationState()}>
                        <ControlLabel>To</ControlLabel>
                        <FormControl componentClass="select" placeholder="select"
                                     onChange={this.props.updateHandler('to')}>
                            {placesTo}
                        </FormControl>
                    </FormGroup>
                    <FormGroup bsSize="large">
                        <ControlLabel>Type</ControlLabel>
                        <FormControl type="text" placeholder="Type"
                                     onChange={this.props.updateHandler('type')} />
                    </FormGroup>
                    <FormGroup bsSize="large">
                        <ControlLabel>Note</ControlLabel>
                        <FormControl type="text" placeholder="Note"
                            onChange={this.props.updateHandler('note')} />
                    </FormGroup>
                    <Button id="submitButton" type="submit" bsSize="large" block>
                        Update
                    </Button>
                </form>
            </Well>
        )
    }
}

export default Edit;
