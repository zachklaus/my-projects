import React, {Component} from 'react';
import {Container, Row, Col, Button} from 'reactstrap';
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';
import 'leaflet/dist/leaflet.css';
import { Map, Marker, Popup, TileLayer, Polyline} from 'react-leaflet';
import Pane from './Pane';
import MapPane from './MapPane';
import FileManager from './Home/FileManager';
import ItineraryTable from "./Home/ItineraryTable";
import ChangeItinerary from "./Home/ChangeItinerary";
import FindTable from "./Home/FindTable";
import GeoMap from "./Home/GeoMap";

/*
 * Renders the home page.
 */

export default class Home extends Component {

  constructor(props) {
    super(props);

    this.geoSuccess = this.geoSuccess.bind(this);
    this.toggleDetails = this.toggleDetails.bind(this);
    this.handleDoneAddingPlacesButtonClick = this.handleDoneAddingPlacesButtonClick.bind(this);
    this.findSessionID = 1;

    this.state = {
      currentLocation: L.latLng(40.576179, -105.080773),
      showDetailed: false,
      placesToAdd: []
    };

    this.geoLocation();
  }

  render() {
    return (
      <Container>
        <Row>
          <Col xs={12} sm={12} md={12} lg={8} xl={8}>
            <GeoMap itinerary={this.props.itinerary}
                   showMarker={this.props.showMarker}
                   updateShowMarker={this.props.updateShowMarker}
                    currentLocation={this.state.currentLocation}/>
          </Col>

          <Col xs={12} sm={12} md={12} lg={4} xl={4}>
            <FileManager updateItinerary={this.props.updateItinerary}
                         itinerary={this.props.itinerary}
                         showMarker={this.props.showMarker}
                         updateShowMarker={this.props.updateShowMarker}/>
          </Col>
        </Row>
        <Row>
          <Col xs={12}>
            <ChangeItinerary updateItinerary={this.props.updateItinerary}
                             itinerary={this.props.itinerary}
                             attributes={this.props.serverConfig.placeAttributes}
                             find={this.props.find}
                             updateFind={this.props.updateFind}
                             showMarker={this.props.showMarker}
                             serverConfig={this.props.serverConfig}
                             toggleDetails={this.toggleDetails}
                             updateShowMarker={this.props.updateShowMarker}/>
          </Col>
        </Row>
        <Row>
          <Col xs={12} sm={12} md={7} lg={8} xl={9}>
            <FindTable find={this.props.find}
                       itinerary={this.props.itinerary}
                       updateItinerary={this.props.updateItinerary}
                       findSessionID={this.findSessionID}
                       handleClickShow={this.handleClickShow}
                       handleClickHide={this.handleClickHide}/>
          </Col>
        </Row>
        <Row>
          <Col xs={12} sm={12} md={7} lg={8} xl={9}>
            {this.renderDoneAddingLocationsButton()}
          </Col>
        </Row>
        <Row>
          <Col xs={12}>
            <ItineraryTable itinerary={this.props.itinerary}
                            showDetails={this.state.showDetailed}
                            showMarker={this.props.showMarker}
                            updateShowMarker={this.props.updateShowMarker}
                            updateItinerary={this.props.updateItinerary}/>
          </Col>
        </Row>
      </Container>
    );
  }

  geoSuccess(position) {
    this.setState({currentLocation: L.latLng(position.coords.latitude, position.coords.longitude)})
  }

  /***************************************************************************************
   *    Title: HTML5 Geolocation
   *    Author: W3school
   *    Availability: https://www.w3schools.com/html/html5_geolocation.asp
   ***************************************************************************************/
  geoLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(this.geoSuccess);
    } else {
    }
  }

  toggleDetails() {
    let newValue = !this.state.showDetailed;
    this.setState({showDetailed: newValue});
  }

  getPoints() {

    let pointsList = [];
    let places = this.props.itinerary.places;

    for (let i = 0; i < places.length; i++) {
      let tripPoint = L.latLng(parseFloat(places[i].latitude), parseFloat(places[i].longitude));
      pointsList.push(tripPoint);
    }

    return pointsList;

  }

  renderDoneAddingLocationsButton() {

    if (this.props.find.places.length !== 0) {
      return (
        <Button value={this.state.placesToAdd}
                color={'success'}
                id={'button_find'}
                onClick={this.handleDoneAddingPlacesButtonClick}>Done Adding Locations</Button>
      );
    }
  }

  handleDoneAddingPlacesButtonClick() {
    let find = {
      requestType: "find",
      requestVersion: 5,
      match: null,
      narrow: [],
      limit: 10,
      found: 0,
      places: []
    };
    this.props.updateFind(find, true);
    this.findSessionID = this.findSessionID + 1;
  }
}




