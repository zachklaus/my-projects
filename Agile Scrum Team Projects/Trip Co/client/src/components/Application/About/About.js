import React, { Component } from 'react'
import { Container, Row, Col } from 'reactstrap'
import { Card, CardBody, CardImg, CardText, CardTitle } from 'reactstrap'
import Pane from '../Pane';

import matteoPic from './images/Matteo.jpg'
import aaronPic from './images/Aaron.jpg'
import zachPic from './images/Zach.jpg'
import davePic from './images/DavidSant.jpg'
export default class About extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Container>
                <Row>
                    <Col xs={12}>
                        {this.createHeader()}
                    </Col>
                </Row>
                <Row>
                    <Col xs={12} sm={6} md={4} lg={3}>
                        {this.createCard({name:"Matteo Vera", bio:"I am a third year CS student who is passionate about PC hardware and bikes. In my free time I like to destroy my roommates in SSBU."}, matteoPic)}
                    </Col>
                    <Col xs={12} sm={6} md={4} lg={3}>
                        {this.createCard({name:"Aaron Marquez", bio:"Third year CS student with a passion for PC building, gaming, Photography, Offroading, and camping."}, aaronPic)}
                    </Col>
                    <Col xs={12} sm={6} md={4} lg={3}>
                        {this.createCard({name:"Zachary Klausner", bio:"Junior CS student who loves anything to do with computers and computation."}, zachPic)}
                    </Col>
                    <Col xs={12} sm={6} md={4} lg={3}>
                        {this.createCard({name:"David Sant", bio:"Senior CS student that is very tired and cannot wait to graduate."}, davePic)}
                    </Col>
                </Row>
            </Container>
        );
    }

    createHeader() {
        return (
            <Pane header={'About'}
                  bodyJSX={<div>Get to know the team behind TripCo Team 18: Ctrl Alt Elite!</div>}/>
        );
    }

    createCard(data, image){
        return (
            <div>
                <Card>
                    <CardBody>
                        <CardImg top width="100%" src={image}/>
                        <CardTitle>{data.name}</CardTitle>
                        <CardText>{data.bio}</CardText>
                    </CardBody>
                </Card>
            </div>
        );
    }
}
