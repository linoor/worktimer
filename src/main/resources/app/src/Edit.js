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


    render() {

        let placesFrom = this.props.places.map((place) => <option key={place} value={place}>{place}</option>);
        let placesTo = placesFrom;

        return (
            <Well bsSize="small">
                <form>
                    <FormGroup bsSize="large">
                        <FormControl type="text" placeholder="Note" />
                    </FormGroup>
                    <FormGroup bsSize="large">
                        <ControlLabel>From</ControlLabel>
                        <FormControl componentClass="select" placeholder="select">
                            {placesFrom}
                        </FormControl>
                    </FormGroup>
                    <FormGroup bsSize="large">
                        <ControlLabel>To</ControlLabel>
                        <FormControl componentClass="select" placeholder="select">
                            {placesTo}
                        </FormControl>
                    </FormGroup>
                </form>
            </Well>
        )
    }
}

export default Edit;
