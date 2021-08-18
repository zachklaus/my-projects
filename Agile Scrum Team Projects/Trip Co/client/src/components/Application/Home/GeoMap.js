import React, {Component} from 'react';
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';
import 'leaflet/dist/leaflet.css';
import { Map, Marker, Popup, TileLayer, Polyline} from 'react-leaflet';
import MapPane from '../MapPane';

export default class GeoMap extends Component{
  constructor(props){
    super(props);

    this.state = {
      showDetailed: false
    }

    this.headerName = typeof this.props.headerName === "undefined"?'Itinerary Map':this.props.headerName;


  }

  render() {
    let places = this.props.itinerary.places;

    if (places.length === 0) {
      return this.renderMapNoPlaces();
    } else if (places.length === 1) {
      return this.renderMapOnePlace();
    } else if (this.areSamePlaces()) {
      return this.renderMapPlacesAreSame();
    } else {
      return this.renderMapOtherwise();
    }
  }

  renderMapNoPlaces() {
    return (
        <MapPane header={'Your Location'}
                 id={'map_no_places'}>
          <Map center={this.props.currentLocation}
               zoom={10}
               style={{height: 500, maxwidth: 700}}>
            <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                       attribution="&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
            />
            <Marker position={this.props.currentLocation}
                    icon={this.markerIcon()}>
              id={"user_location"}
              key={"loc_user"}
              <Popup className="font-weight-extrabold">You are here</Popup>
            </Marker>
          </Map>
        </MapPane>
    )
  }

  renderMapOnePlace() {
    let places = this.props.itinerary.places;
    let point = L.latLng(parseFloat(places[0].latitude), parseFloat(places[0].longitude));
    return (
        <MapPane header={'Itinerary Map'}
                 id={'map_one_place'}>
          <Map center={point}
               zoom={10}
               style={{height: 500, maxwidth: 700}}>
            <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                       attribution="&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
            />
            {this.renderMarker(places[0])}
          </Map>
        </MapPane>
    )
  }

  renderMapPlacesAreSame() {
    let places = this.props.itinerary.places;
    let point = L.latLng(parseFloat(places[0].latitude), parseFloat(places[0].longitude));
    return (
        <MapPane header={this.headerName}
                 id={'map_same_places'}>
          <Map center={point} zoom={10}
               style={{height: 500, maxwidth: 700}}>
            <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                       attribution="&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
            />
            {this.renderMarker(places[0])}
          </Map>
        </MapPane>
    )
  }

  renderMapOtherwise() {
    return (
        <MapPane header={this.headerName}
                 id={'map_otherwise'}>
          <Map bounds={this.getPointsBounds()}
               style={{height: 500, maxwidth: 700}}>
            <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                       attribution="&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
            />
            {this.renderPoints()}
            {this.renderLines()}
          </Map>
        </MapPane>
    )
  }

  renderMarker(place) {
    if (this.props.showMarker[place.name] === true) {
      let point = L.latLng(parseFloat(place.latitude), parseFloat(place.longitude));
      return (
          <Marker position={point}
                  icon={this.markerIcon()}>
            id={place.name}
            key={"loc_" + place.name}
            <Popup className="font-weight-extrabold">
              <dl>
                <dt>Name:</dt><dd>{place.name}</dd>
                <dt>Stop Number:</dt><dd>{this.getPlaceIndex(place, this.props.itinerary.places) + 1}</dd>
                <dt>Latitude:</dt><dd>{place.latitude}</dd>
                <dt>Longitude:</dt><dd>{place.longitude}</dd>
              </dl>
            </Popup>
          </Marker>
      );
    }
  }

  renderPoints() {

    let pointsList = [];
    let places = this.props.itinerary.places;

    for (let i = 0; i < places.length; i++) {
      pointsList.push(this.renderMarker(places[i]), i);
    }
    return pointsList;

  }

  renderLines() {

    let linesList = [];
    let places = this.props.itinerary.places;

    for (let i = 0; i < places.length; i++) {
      if (i === places.length - 1) {
        let lastPoint = L.latLng(parseFloat(places[places.length - 1].latitude), parseFloat(places[places.length - 1].longitude));
        let startPoint = L.latLng(parseFloat(places[0].latitude), parseFloat(places[0].longitude));
        let points = new Array(lastPoint, startPoint);
        linesList.push(
            <Polyline positions={points} color={'#0066cc'}></Polyline>
        )
      } else {
        let point0 = L.latLng(parseFloat(places[i].latitude), parseFloat(places[i].longitude));
        let point1 = L.latLng(parseFloat(places[i + 1].latitude), parseFloat(places[i + 1].longitude));
        let points = new Array(point0, point1);
        linesList.push(
            <Polyline positions={points} color={'#0066cc'}></Polyline>
        )
      }
    }
    return linesList;
  }

  getPointsBounds() {

    let pointsList = [];
    let places = this.props.itinerary.places;

    for (let i = 0; i < places.length; i++) {
      let tripPoint = L.latLng(parseFloat(places[i].latitude), parseFloat(places[i].longitude));
      pointsList.push(tripPoint);
    }

    let bounds = L.latLngBounds(pointsList);
    return bounds;

  }


  markerIcon() {
    // react-leaflet does not currently handle default marker icons correctly,
    // so we must create our own
    return L.icon({
      iconUrl: icon,
      shadowUrl: iconShadow,
      iconAnchor: [12, 40]  // for proper placement
    });
  }


  areSamePlaces() {
    let places = this.props.itinerary.places;
    let startLat = places[0].latitude;
    let startLong = places[0].longitude;
    let samePlaces = true;

    for (let i = 0; i < places.length; i++) {
      if (places[i].latitude !== startLat || places[i].longitude !== startLong) {
        samePlaces = false;
      }
    }
    return samePlaces;
  }

  getPlaceIndex(place, places){
    for (let i = 0; i < places.length; i++) {
      if (JSON.stringify(places[i]) === JSON.stringify(place)) {
        return i;
      }
    }
    return -1;
  }

}