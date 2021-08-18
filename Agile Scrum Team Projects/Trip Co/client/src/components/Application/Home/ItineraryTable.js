import React, {Component} from 'react';
import {Button, FormGroup, Input, Label} from 'reactstrap';
import Pane from '../Pane';
import { Table } from 'reactstrap';
import {ButtonGroup} from 'reactstrap';

/***************************************************************************************
 *    Title: Table - React
 *    Author: ReactJS.org
 *    Availability: https://reactstrap.github.io/components/tables/
 *
 *    The Source material has been altered to make use of some ReactStrap components
 *    while also conforming to the class standard.
 ***************************************************************************************/

/*call function to get each row that returns array of rows this.props.what its passed as*/
export default class ItineraryTable extends Component {

    constructor(props) {
        super(props);
        this.renderCheckBox = this.renderCheckBox.bind(this);
        this.handleChecked = this.handleChecked.bind(this);
        this.inTable = this.inTable.bind(this);
        this.addedPlaces = [];
        this.handleNewStartPointButtonClick = this.handleNewStartPointButtonClick.bind(this);
        this.handleDeleteButtonClick = this.handleDeleteButtonClick.bind(this);
        this.renderChangeItineraryButtons = this.renderChangeItineraryButtons.bind(this);
      this.handleSwap = this.handleSwap.bind(this);
    }

    tableRows(){
        let distances = this.props.itinerary.distances;
        let places = this.props.itinerary.places;
        let tableList = [];
        let distanceTotal = 0;
        this.addedPlaces = [];
        if(this.props.showDetails) {
          for (let i = 0; i < places.length; i++) {
            tableList.push(
              <tr key={places[i].id}>
                <td>{this.renderCheckBox(places[i])}</td>
                <td visible={false}>{this.renderChangeItineraryButtons(places, i)}</td>
                <td>{places[i].name}</td>
                <td>{places[i].id}</td>
                <td>{Number.parseFloat(places[i].latitude).toFixed(3)}</td>
                <td>{Number.parseFloat(places[i].longitude).toFixed(3)}</td>
                <td>{places[i].municipality}</td>
                <td>{places[i].altitude}</td>
                <td>{places[(i === places.length - 1) ? 0 : i + 1].name}</td>
                <td>{distances[i]}</td>
                <td>{distanceTotal += distances[i]}</td>
              </tr>
            )
          }
        } else {
          for (let i = 0; i < places.length; i++) {
            tableList.push(
              <tr key={places[i].id}>
                <td>{this.renderCheckBox(places[i])}</td>
                <td visible={false}>{this.renderChangeItineraryButtons(places, i)}</td>
                <td>{places[i].name}</td>
                <td>{places[(i === places.length - 1) ? 0 : i + 1].name}</td>
                <td>{distances[i]}</td>
                <td>{distanceTotal += distances[i]}</td>
              </tr>
            )
          }
        }
        return (tableList);
    }
    createTable(){
      if(this.props.showDetails) {
        return (
          <Table responsive>
            <thead>
            <tr>
              <th>Map Marker</th>
              <th>Order</th>
              <th>From</th>
              <th>Id</th>
              <th>Latitude</th>
              <th>Longitude</th>
              <th>Municipality</th>
              <th>Altitude</th>
              <th>To</th>
              <th>Leg Distance</th>
              <th>Total Distance</th>
            </tr>
            </thead>
            <tbody>
            {this.tableRows()}
            </tbody>
          </Table>
        );
      } else {
        return(
        <Table responsive>
          <thead>
          <tr>
            <th>Map Marker</th>
            <th>Order</th>
            <th>From</th>
            <th>To</th>
            <th>Leg Distance</th>
            <th>Total Distance</th>
          </tr>
          </thead>
          <tbody>
          {this.tableRows()}
          </tbody>
        </Table>);
      }

    }

    renderChangeItineraryButtons(places, i) {
      return (
        <ButtonGroup>
          <Button onClick ={() =>  {this.handleSwap(i, i-1)}}
                  type={"button"}
                  id={"up"}
                  disabled={i===0}
                  size={"sm"}
                  color={"primary"}>↑</Button>
          <Button onClick={() =>  {this.handleSwap(i, i+1)}}
                  type={"button"}
                  id={"test"}
                  disabled={i=== places.length -1}
                  size={"sm"}
                  color={"secondary"}>↓</Button>
          <Button onClick={() =>  {this.handleNewStartPointButtonClick(i)}}
                  type={"button"}
                  disabled={i===0}
                  id={"3"}
                  size={"sm"}
                  color={"warning"}>⇈</Button>
          <Button onClick={() =>  {this.handleDeleteButtonClick(i)}}
                  type={"button"}
                  id={"4"}
                  size={"sm"}
                  color={"danger"}>✖</Button>
        </ButtonGroup>
      );
    }

    //ternary for toggling pane on and off
    render() {
        return (
            (this.props.itinerary.places.length !== 0)
                ?<Pane header={"Itinerary Table"} bodyJSX={this.createTable()}/>
                : <div/>

        );
    }

  renderCheckBox(place) {

    return (
      <FormGroup check>
        <Label check>
          <Input type={"checkbox"}
                 name={'name_' + place.name}
                 id={'checkbox_' + place.name}
                 checked={this.props.showMarker[place.name] === true}
                 onChange={() => this.handleChecked(place)}/>{' '}
        </Label>
      </FormGroup>
    );


  }

  handleChecked(place) {

      let newShowMarker = this.props.showMarker;
      let oldValue = this.props.showMarker[place.name];
      newShowMarker[place.name] = !oldValue
      this.props.updateShowMarker(newShowMarker);

  }

  getPlaceIndex(place, places){
    for (let i = 0; i < places.length; i++) {
      if (JSON.stringify(places[i]) === JSON.stringify(place)) {
        return i;
      }
    }
    return -1;
  }

  inTable(place) {

    for (let i = 0; i < this.addedPlaces.length; i++) {
      if (place.name === this.addedPlaces[i].name) {
        return true;
      }
    }
    return false;
  }

  handleNewStartPointButtonClick(index){
      if(this.props.itinerary.options == null || this.props.itinerary.places.length === 0 ){
        return;
      }

      let newStartItinerary = this.props.itinerary;
      let tempPlaces = this.props.itinerary.places;
      let addedElement = tempPlaces[index];

      tempPlaces.splice(index,1);
      tempPlaces.splice(0, 0, addedElement);

      newStartItinerary.places = tempPlaces;
      this.props.updateItinerary(newStartItinerary, true);
    }

    handleDeleteButtonClick(index) {
      if(this.props.itinerary.options == null || this.props.itinerary.places.length === 0 ){
          return;
      }

      let newDeleteItinerary = this.props.itinerary;
      let newPlaces = this.props.itinerary.places;
      newPlaces.splice(index, 1);
      newDeleteItinerary.places = newPlaces;
      this.props.updateItinerary(newDeleteItinerary);
    }

  // movement of arrays is borrowed from https://stackoverflow.com/questions/5306680/move-an-array-element-from-one-array-position-to-another
  handleSwap(from, to) {
    if(this.props.itinerary.options == null || this.props.itinerary.places.length === 0 ){
      return;
    }

    let newUpItinerary = this.props.itinerary;
    let tempPlaces = this.props.itinerary.places;

    tempPlaces.splice(to, 0, tempPlaces.splice(from, 1) [0]);

    newUpItinerary.places = tempPlaces;
    this.props.updateItinerary(newUpItinerary, true);

  }

}