/**
 * Created by linoor on 9/25/16.
 */

import React, { Component } from 'react';
import { Button, Row } from "react-bootstrap";

class Starter extends Component {
    render() {

        let buttonText = this.props.started? "Stop" : (this.props.seconds > 0 ? "Continue" : "Start");

        return (
            <Row>
                <Button id="startButton" bsStyle="primary" bsSize="large" onClick={this.props.handler}>{buttonText}</Button>
            </Row>
        )
    }
}

export default Starter;