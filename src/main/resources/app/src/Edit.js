/**
 *
 * Created by linoor on 9/25/16.
 */

import React, { Component } from 'react';
import { Well, FormGroup, FormControl, ControlLabel  } from "react-bootstrap";

class Edit extends Component {
    constructor(props) {
        super(props);
    }

    getValidationState() {
       if (this.props.from === this.props.to) {
           console.log(this.props.from);
           console.log(this.props.to);
           return 'error';
       } else {
           return 'sucess';
       }
    }

    render() {

        let placesFrom = this.props.places.map((place) => <option key={place} value={place}>{place}</option>);
        let placesTo = placesFrom;

        return (
            <Well bsSize="small">
                <form>
                    <FormGroup bsSize="large">
                        <FormControl type="text" placeholder="Note" />
                    </FormGroup>
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
                </form>
            </Well>
        )
    }
}

export default Edit;
