import React, {Component} from 'react'
import {Container, Row, Col} from 'reactstrap'
import {Button} from 'reactstrap'
import {Form, Label, Input} from 'reactstrap'
import {sendServerRequestWithBody} from '../../../api/restfulAPI'
import Pane from '../Pane';
import MapPane from'../MapPane';
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';
import 'leaflet/dist/leaflet.css';
import {Map, Marker, Polyline, Popup, TileLayer} from 'react-leaflet';
import Coordinates from 'coordinate-parser';
import JsonSchemas from "../Misc/JsonSchemas";
import GeoMap from "../Home/GeoMap";

export default class Calculator extends Component {
  constructor(props) {
    super(props);

    this.updateLocationOnChange = this.updateLocationOnChange.bind(this);
    this.calculateDistance = this.calculateDistance.bind(this);
    this.createInputField = this.createInputField.bind(this);
    this.updatePointsOnChange = this.updatePointsOnChange.bind(this);


    this.showMarker = [];
    this.showMarker['Origin'] = true;
    this.showMarker['Destination'] = true;

    this.state = {
      itinerary:{
        places: [{
          "name": "Origin",
          "latitude": "40.576179",
          "longitude": "-105.080773"
        }, {
          "name": "Destination",
          "latitude": "40.576179",
          "longitude": "-105.080773"
        }]
      },
      errorMessage: null,
      ifDisplayUserInputFields: false,
    };
  }

  render() {
    return (
        <Container>
          {this.state.errorMessage}
          <Row>
            <Col>
              {this.createHeader()}
            </Col>
          </Row>
          <Row>
            <Col xs={12} sm={6} md={4} lg={3}>
              {this.createForm('origin')}
            </Col>
            <Col xs={12} sm={6} md={4} lg={3}>
              {this.createForm('destination')}
            </Col>
            <Col xs={12} sm={6} md={4} lg={3}>
              {this.createDistance()}
            </Col>
            <Col xs={12} sm={12} >
              <GeoMap id={'geomap'}
                      itinerary={this.state.itinerary}
                      showMarker={this.showMarker}
                      headerName={'Calculator Map'}/>
            </Col>
          </Row>
        </Container>
    );
  }

  createHeader() {
    return (
        <Pane header={'Calculator'}
              bodyJSX={<div>Determine the distance between the origin and destination.
                Change the units on the <b>Options</b> page.</div>}/>
    );
  }

  createInputField(stateVar, coordinate) {
    let updateStateVarOnChange = (event) => {
      this.updateLocationOnChange(stateVar, event.target.name, event.target.value)
    };

    let capitalizedCoordinate = coordinate.charAt(0).toUpperCase() + coordinate.slice(1);
    return (
        <Input name={coordinate} placeholder={capitalizedCoordinate}
               id={`${stateVar}${capitalizedCoordinate}`}
               value={this.props.calculatorData[stateVar][coordinate]}
               onChange={updateStateVarOnChange}
               style={{width: "100%"}}/>
    );

  }

  createForm(stateVar) {
    return (
        <Pane header={stateVar.charAt(0).toUpperCase() + stateVar.slice(1)}
              bodyJSX={
                <Form>
                  {this.createInputField(stateVar, 'latitude')}
                  {this.createInputField(stateVar, 'longitude')}
                </Form>
              }
        />);
  }

  createDistance() {
    return (
        <Pane header={'Distance'}
              bodyJSX={
                <div>
                  <h5>{this.props.calculatorData.distance} {this.props.options.activeUnit}</h5>
                  <Button onClick={this.updatePointsOnChange}>Calculate</Button>
                </div>}
        />
    );
  }


  async updatePointsOnChange() {

    this.calculateDistance();

    await this.props.updateCalculatorPoints("originPoint", L.latLng(parseFloat(this.props.calculatorData.origin.latitude), parseFloat(this.props.calculatorData.origin.longitude)));
    await this.props.updateCalculatorPoints("destinationPoint", L.latLng(parseFloat(this.props.calculatorData.destination.latitude), parseFloat(this.props.calculatorData.destination.longitude)));

  }

  calculateDistance() {

    let transOrigin = new Coordinates(this.props.calculatorData.origin.latitude.toString() + ' ' + this.props.calculatorData.origin.longitude.toString());
    let transDest = new Coordinates(this.props.calculatorData.destination.latitude.toString() + ' ' + this.props.calculatorData.destination.longitude.toString());

    const tipConfigRequest = {
      'requestType': 'distance',
      'requestVersion': 4,
      'origin': {
        latitude: transOrigin.getLatitude().toString(),
        longitude: transOrigin.getLongitude().toString()
      },
      'destination': {
        latitude: transDest.getLatitude().toString(),
        longitude: transDest.getLongitude().toString()
      },
      'earthRadius': this.props.options.units[this.props.options.activeUnit]
    };
    if(!this.validateDistanceRequest(tipConfigRequest)){
      this.setState({
        errorMessage: this.props.createErrorBanner(
            'Invalid Request',
            400,
            'Request Not Sent'
        )
      });
      return;
    }
    sendServerRequestWithBody('distance', tipConfigRequest, this.props.settings.serverPort)
        .then((response) => {
          if (response.statusCode >= 200 && response.statusCode <= 299) {
            this.setState({
              //distance: response.body.distance,
              errorMessage: null
            });
            this.props.updateCalculatorPoints("distance", response.body.distance);
            this.updateMapPoints();
          } else {
            this.setState({
              errorMessage: this.props.createErrorBanner(
                  response.statusText,
                  response.statusCode,
                  `Request to ${this.props.settings.serverPort} failed.`
              )
            });
          }
        });
  }

  validateDistanceRequest(value){
    return JsonSchemas.validate(value, JsonSchemas.Schemas.DISTANCE);
  }

  updateMapPoints(){
    let origin = this.props.calculatorData.origin;
    let destination = this.props.calculatorData.destination;
    this.setState({
      itinerary:{
        places: [{
          "name": "Origin",
          "latitude": origin.latitude,
          "longitude": origin.longitude
        }, {
          "name": "Destination",
          "latitude": destination.latitude,
          "longitude": destination.longitude
        }]
      }
    });
  }

  updateLocationOnChange(stateVar, field, value) {
    let location = Object.assign({}, this.props.calculatorData[stateVar]);
    location[field] = value;
    this.props.updateCalculatorPoints(stateVar, location);

  }
}