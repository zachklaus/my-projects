import React, {Component} from 'react';
import Pane from '../Pane';
import { Table } from 'reactstrap';
import { Button, Container, FormGroup, Label, Input, Row} from 'reactstrap';

/***************************************************************************************
 *    Title: Table - React
 *    Author: ReactJS.org
 *    Availability: https://reactstrap.github.io/components/tables/
 *
 *    The Source material has been altered to make use of some ReactStrap components
 *    while also conforming to the class standard.
 ***************************************************************************************/

export default class FindTable extends Component {

  constructor(props) {
    super(props);
    this.renderCheckBox = this.renderCheckBox.bind(this);
    this.handleChecked = this.handleChecked.bind(this);
    this.getPlaceIndex = this.getPlaceIndex.bind(this);
    this.placesAndSessionIDs = new Map([]);
  }

  tableRows(){;
    let places = this.props.find.places;
    let tableList = [];
    for (let i = 0; i < places.length; i++) {
      tableList.push(
        <tr key={places[i].id}>
          <td>{this.renderCheckBox(places[i])}</td>
          <td>{places[i].name}</td>
          <td>{Math.round(places[i].latitude * 1000) / 1000}</td>
          <td>{Math.round(places[i].longitude * 1000) / 1000}</td>
          <td>{places[i].municipality}</td>
          <td>{places[i].altitude}</td>
        </tr>
      )
    }
    return (tableList);
  }

  createTable(){

    return (
      <Table responsive>
        <thead>
        <tr>
          <th>Add to Itinerary</th>
          <th>Name</th>
          <th>Latitude</th>
          <th>Longitude</th>
          <th>Municipality</th>
          <th>Altitude</th>
        </tr>
        </thead>
        <tbody>
        {this.tableRows()}
        </tbody>
      </Table>
    );
  }

  renderCheckBox(place) {

    return (
      <FormGroup check>
        <Label check>
          <Input type={"checkbox"}
                 name={'name_' + place.name}
                 id={'checkbox_' + place.name}
                 onChange={() => this.handleChecked(place)}/>{' '}
        </Label>
      </FormGroup>
    );


  }

  handleChecked(place) {

    let updatedItinerary = this.props.itinerary;

    if(this.getPlaceIndex(place, updatedItinerary.places) === -1) {
      updatedItinerary.places.push(place);
      this.placesAndSessionIDs.set(place, this.props.findSessionID);
    }
    else if (this.placesAndSessionIDs.get(place) === this.props.findSessionID) {
      updatedItinerary.places.splice(this.getPlaceIndex(place, updatedItinerary.places),1);
    }

    if (this.props.itinerary.options !== {}) {
      let newItinerary = {
        requestType: "itinerary",
        requestVersion: 3,
        options: {title: "My Trip", earthRadius: "3958.761316", optimization: "none"},
        places: updatedItinerary.places,
        distances: []
      };
      this.props.updateItinerary(newItinerary, true);
    }else{
      this.props.updateItinerary(updatedItinerary, true);
    }

  }

  render() {

    return (
      (this.props.find.places.length !== 0)
        ?<Pane header={"Search Results"} bodyJSX={this.createTable()}/>
        : <div></div>
    );
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