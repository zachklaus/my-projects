import React, {Component} from 'react';
import {Button, ButtonGroup, FormGroup, Form, Input, InputGroup,
  InputGroupAddon} from 'reactstrap';
import Pane from '../Pane';
import {Container, Row, Col} from 'reactstrap';
import {Modal, ModalHeader, ModalFooter, ModalBody, Label, ButtonDropdown, DropdownMenu, DropdownToggle, DropdownItem} from 'reactstrap';
import CustomLocationForm from "./CustomLocationForm";
import Coordinates from "coordinate-parser";

export default class ChangeItinerary extends Component {
  constructor(props) {
    super(props);
    this.handleReverseButtonClick = this.handleReverseButtonClick.bind(this);
    this.toggleCustomLocationField = this.toggleCustomLocationField.bind(this);
    this.updateCustomLocation = this.updateCustomLocation.bind(this);
    this.submitModalData = this.submitModalData.bind(this);
    this.submitFindModalData = this.submitFindModalData.bind(this);
    this.getFilterDropdowns = this.getFilterDropdowns.bind(this);
    this.updateMatchString = this.updateMatchString.bind(this);
    this.toggle = this.toggle.bind(this);
    this.getFilterDropdownItems = this.getFilterDropdownItems.bind(this);
    this.toggleFilter = this.toggleFilter.bind(this);
    this.buildFilters = this.buildFilters.bind(this);


    this.state = {
      reverseOrder: false,
      showCustomLocationField: false,
      showFindLocationField: false,
      customLocation: {},
      matchString: "",
      dropdownOpen: new Array(this.props.serverConfig.filters.length).fill(false),
      activeDropDownItems: [],
      activeFilters: [],
      narrow: [],
      buttonActive:false
    };

    this.buildActiveFilters();
    
  }

  render() {
    return (<Pane header={"Modify itinerary"} bodyJSX={this.createForm()}/>);
  }

  createForm() {
    return (
      <Container>
        <Row>
          <Col>
            {this.renderFindLocationField()}

          </Col>
        </Row>
        <Row>
          <Col>
            {this.renderCustomLocationField()}
          </Col>
          <Col>
            <ButtonGroup>
              <Button onClick={() => {
                this.props.toggleDetails();
                this.setState({buttonActive:!this.state.buttonActive});}}
                      active={this.state.buttonActive}
                      type={"button"}
                      size={"sm"}
                      id={"detailToggle"}
                      color={"secondary"}>Show Details</Button>

            <Button onClick={this.handleReverseButtonClick}
                    type={"button"}
                    size ={"sm"}
                    id={"reverse"}
                    color={"success"}>Reverse Trip Order</Button>
            </ButtonGroup>
          </Col>
        </Row>
      </Container>);
  }

  //find location methods
  //className={"w-50"}

  renderFindLocationField() {

    return (
      <Form onSubmit={(event) => {event.preventDefault();}}>
        <FormGroup>
          <div>
            <Label><strong>Search for an existing location:</strong></Label>
            <InputGroup>
            <Input type={'text'} width={1} id={'searchLoc'} key={'searchLocInput'} placeholder={'name or attribute...'}
                   onChange={(e) => this.updateMatchString(name, e.target.value)}/>
            <InputGroupAddon addonType="append">
              <Button color="success" id={'findLocation'} onClick={this.submitFindModalData} type={'button'}>Search</Button>
               {this.getFilterDropdowns()}
            </InputGroupAddon>
          </InputGroup>
        </div>
      </FormGroup>
      </Form>
    );
  }


  getFilterDropdowns() {

    let dropdownList = [];
    let filters = this.props.serverConfig.filters;

    for (let filtersIndex = 0; filtersIndex < filters.length; filtersIndex++) {
      dropdownList.push(<ButtonDropdown isOpen={this.state.dropdownOpen[filtersIndex]} toggle={() => this.toggle(filtersIndex)}>
                        <DropdownToggle caret>
                          {this.capitalize(filters[filtersIndex].name)}
                        </DropdownToggle>
                        <DropdownMenu className={'dropdown-menu pre-scrollable'}>
                          {this.getFilterDropdownItems(filtersIndex, filters[filtersIndex].values)}
                        </DropdownMenu>
                      </ButtonDropdown>);
    }
    return dropdownList;
  }

  buildActiveFilters() {

    let filters = this.props.serverConfig.filters;
    let newActiveFilters = [];

    for (let filterIndex = 0; filterIndex < filters.length; filterIndex++) {
      let newActiveFilter = [];

      for (let valueIndex = 0; valueIndex < filters[filterIndex].values.length; valueIndex++) {
        newActiveFilter.push(false);
      }
      newActiveFilters.push(newActiveFilter);
    }

    this.state.activeFilters = newActiveFilters;
  }

  getFilterDropdownItems(filterIndex, values) {

    let dropdownItemsList = [];

    for (let valueIndex = 0; valueIndex < values.length; valueIndex++) {
      dropdownItemsList.push(
        <DropdownItem toggle={false}
                      active={this.state.activeFilters[filterIndex][valueIndex]}
                      onClick={() => this.toggleFilter(filterIndex, valueIndex)}>{values[valueIndex]}</DropdownItem>
      );
    }
    return dropdownItemsList;
  }

  toggleFilter(filterIndex, filterValueIndex) {

    let newActiveFilters = this.state.activeFilters;
    newActiveFilters[filterIndex][filterValueIndex] = !newActiveFilters[filterIndex][filterValueIndex];
    this.setState({activeFilters: newActiveFilters});
  }

  toggle(index) {

    let newDropdownOpen = [];
    for (let i=0; i<this.state.dropdownOpen.length; i++) {
      if (i == index) {
        newDropdownOpen[i] = !this.state.dropdownOpen[i];
      }
      else {
        newDropdownOpen[i] = this.state.dropdownOpen[i];
      }
    }

    this.setState({
      dropdownOpen: newDropdownOpen
    });
  }

  submitFindModalData() {

    this.buildFilters();

    if(this.shouldSubmitFind()) {

      let newFind = {
        requestType: "find",
          requestVersion: 5,
          match: this.state.matchString,
          narrow: this.state.narrow,
          limit: 10,
          found: 0,
          places: []
      };
      this.props.updateFind(newFind);
    } else {
      alert("Invalid place name or attribute. Please retry your search.");
    }
  }

  shouldSubmitFind(){
    let badValues = [undefined, null, '', ' '];
    return !(badValues.includes(this.state.matchString));
  }

  updateMatchString(field, value){
    this.setState((prev) => prev.matchString = value);
  }

  buildFilters() {

    let filters = this.props.serverConfig.filters;
    let newFilters = [];
    for (let filterIndex = 0; filterIndex < filters.length; filterIndex++) {
      let newFilter = {
        name: filters[filterIndex].name,
        values: []
      };
      for (let valueIndex = 0; valueIndex < filters[filterIndex].values.length; valueIndex++) {
        if (this.state.activeFilters[filterIndex][valueIndex] === true) {
          newFilter.values.push(filters[filterIndex].values[valueIndex]);
        }
      }
      newFilters.push(newFilter);
    }
    this.state.narrow = newFilters;
  }

  //custom location methods

  toggleCustomLocationField(){
    this.setState((prev) => prev.showCustomLocationField = !prev.showCustomLocationField);
  }

  updateCustomLocation(field, value){
    this.setState((prev) => prev.customLocation[field] = value);
  }

  submitModalData(){
    if(this.shouldSubmit()) {
      this.toggleCustomLocationField();
      this.addLocation(this.state.customLocation);
      this.setState((prev) => prev.customLocation = {});
    } else {
      alert("Name, Latitude, Longitude, and Id are required fields!")
    }
  }

  shouldSubmit(){
    let badValues = [undefined, null, '', ' '];
    return !(badValues.includes(this.state.customLocation.name) ||
            badValues.includes(this.state.customLocation.latitude) ||
            badValues.includes(this.state.customLocation.longitude) ||
            badValues.includes(this.state.customLocation.id));
  }

  addLocation(place){

    let placeCoordinate = new Coordinates(place.latitude.toString() + ' ' + place.longitude.toString());
    place.latitude = placeCoordinate.getLatitude().toString();
    place.longitude = placeCoordinate.getLongitude().toString();

    this.props.itinerary.places.push(place);
    this.props.updateItinerary(this.props.itinerary);
  }

  renderCustomLocationField(){
    return (
      <div>
        <Button size={"sm"} color="success" id={'toggleModal'} onClick={this.toggleCustomLocationField}>Add a custom location</Button>
        <Modal isOpen={this.state.showCustomLocationField} toggle={this.toggleCustomLocationField}>
          <ModalHeader toggle={this.toggleCustomLocationField}>Custom Location Properties</ModalHeader>
          <ModalBody>
            <CustomLocationForm attributes={this.props.attributes} updateLocation={this.updateCustomLocation}/>
          </ModalBody>
          <ModalFooter>
            <Button color="success" id={'addLocation'} onClick={this.submitModalData}>Add Location</Button>
          </ModalFooter>
        </Modal>
      </div>
    );
  }

  handleReverseButtonClick() {

    if (this.props.itinerary.options == null || this.props.itinerary.places.length === 0) {
      return;
    }

    let reverseItinerary = this.props.itinerary;
    let reversePlaces = [];
    reversePlaces.push(this.props.itinerary.places[0]);

    let i = this.props.itinerary.places.length - 1;
    while(i !== 0) {
      reversePlaces.push(this.props.itinerary.places[i]);
      i--;
    }
    reverseItinerary.places = reversePlaces;
    let callback = this.props.updateItinerary;
    callback(reverseItinerary, true);
  }

  //borrowed from https://dzone.com/articles/how-to-capitalize-the-first-letter-of-a-string-in
  capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
  }

}