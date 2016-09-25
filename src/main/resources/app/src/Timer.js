/**
 * Created by linoor on 9/11/16.
 */

import React, { Component } from 'react';
import { Row } from "react-bootstrap";

function str_pad_left(string,pad,length) {
    return (new Array(length+1).join(pad)+string).slice(-length);
}

class Timer extends Component {
    render() {
        let minutes = this.props.started? str_pad_left(Math.floor(this.props.seconds / 60), '0', 2) : '00';
        let seconds = this.props.started? str_pad_left(this.props.seconds - minutes * 60, '0', 2) : '00';

        return (
            <Row className="timer">
                <span className="minutes">{minutes}</span>
                <span>:</span>
                <span className="seconds">{seconds}</span>
            </Row>
        )
    }
}

export default Timer;
