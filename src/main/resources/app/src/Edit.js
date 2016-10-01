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
        this.resetBtn = this.resetBtn.bind(this);

        this.state = {
            btnStyle: 'primary'
        }
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

    resetBtn() {
        this.setState({
            btnStyle: 'primary'
        })
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
                    message: 'The commute has been updated',
                    btnStyle: 'success'
                });
            }
        });
    }

    render() {
        const placesNames  = this.props.places.map((place) => place.name);
        const placesFrom = placesNames.map((place) => {
            if (this.props.from.name === place) {
                return <option key={place} value={place}>{place}</option>;
            } else {
                return <option key={place} value={place}>{place}</option>;
            }
        });
        const placesTo = placesNames.map((place) => {
            if (this.props.to.name === place) {
                return <option key={place} value={place} selected>{place}</option>;
            } else {
                return <option key={place} value={place}>{place}</option>;
            }
        });

        return (
            <Well>
                <form onSubmit={this.updateCommute} onChange={this.resetBtn}>
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
                    <Button id="submitButton" type="submit" bsSize="large" bsStyle={this.state.btnStyle} block>
                        Update
                    </Button>
                </form>
            </Well>
        )
    }
}

export default Edit;
