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
           return 'success';
       }
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
        )
    }
}

export default Edit;
