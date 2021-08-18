import React, {Component} from 'react';
import {Button, ButtonGroup} from 'reactstrap';
import Pane from '../Pane';
import {Input, Label,Container, Row, Col, Dropdown, DropdownMenu, DropdownToggle, DropdownItem} from "reactstrap";
import MapXMLString from "../Misc/MapXMLString";
import Coordinates from "coordinate-parser";

/***************************************************************************************
 *    Title: Uncontrolled Components - React
 *    Author: ReactJS.org
 *    Availability: https://reactjs.org/docs/uncontrolled-components.html#the-file-input-tag
 *    Download:
 *      : https://stackoverflow.com/questions/34156282/how-do-i-save-json-to-local-text-file
 *      : https://stackoverflow.com/questions/43413177/how-do-i-make-a-save-button-that-will-make-a-html-file
 *    The Source material has been altered to make use of some ReactStrap components
 *    while also conforming to the class standard.
 ***************************************************************************************/

export default class FileManager extends Component {
  constructor(props) {
    super(props);
    this.handleUpload = this.handleUpload.bind(this);
    this.handleClickShow = this.handleClickShow.bind(this);
    this.handleClickHide = this.handleClickHide.bind(this);
    this.convertToDecimal = this.convertToDecimal.bind(this);
    this.state = {
      buttonActive:false,
      dropDownOpen: false,
      dropDownFileType: 'File Type'
    };

    this.createDropDown = this.createDropDown.bind(this);
    this.toggleDropDown = this.toggleDropDown.bind(this);
    this.generateCustomFile = this.generateCustomFile.bind(this);
  }

  handleUpload(event) {
    let callback = this.props.updateItinerary;
    let callback2 = this.props.updateShowMarker;
    let callback3 =this.convertToDecimal;
    event.preventDefault();
    alert(
      `File - ${event.target.files[0].name} - has been uploaded!`
    );
    let reader = new FileReader();
    reader.readAsText(event.target.files[0]);
    reader.onload = function(event) {
      let result = reader.result;
      let itinerary = JSON.parse(result);

      let showMarkerReset = [];
      callback2(showMarkerReset);
      itinerary = callback3(itinerary);
      callback(itinerary);
    };
  }

  generateKML(){
    let kmlString = `<?xml version="1.0" encoding="UTF-8"?>` + "\n"+
        `<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom">`+"\n"+
        `<Document>` + "\n"+
        `<name>SVG</name>`+"\n"+
        `<open>1</open>`+"\n"+
        `<description>KML of itinerary</description>`+"\n"+
        `<Style id="lineStyle">`+"\n"+
        `<LineStyle><color>ffffffb6</color><width>4</width></LineStyle></Style>`;
    kmlString += this.generateKmlLines();
    kmlString += `</Document>`+"\n"+`</kml>`;
    return kmlString;
  }

  generateKmlLines(){
    let kmlLineString = '';
    let places = this.props.itinerary.places;
    for(let i = 0; i < places.length;i++){
      let current = places[i];
      let next = places[i+1];
      if(i+1 >= places.length){
        next = places[0];
      }
      let currentAlt = typeof current.altitude === "undefined" ? 0 : current.altitude;
      let nextAlt = typeof next.altitude === "undefined" ? 0 : next.altitude;
      let names = current.name+ ' To '+next.name;
      names = names.replace('&', 'and');
      kmlLineString += `<Placemark>` + "\n"+
        '<name>'+names+'</name>'+"\n"+
          '<styleUrl>#lineStyle</styleUrl>'+"\n"+
          '<LineString>'+
          '<coordinates>'+current.longitude+','+current.latitude+','+currentAlt +"\n"+
          next.longitude+','+next.latitude+','+nextAlt + '</coordinates>'+"\n"+
          '</LineString></Placemark>';
    }
    return kmlLineString + "\n";
  }

  generateSVG(){
    let xmlString = MapXMLString.XML + '\n';
    let places = this.props.itinerary.places;
    for(let i = 0; i < places.length;i++){
      let current = places[i];
      let next = places[i+1];
      if(i+1 >= places.length) {
        next = places[0];
      }

      xmlString += '<line ' +
          ' x1="'+current['longitude']+'" y1="'+current['latitude']+'"' +
          ' x2="'+next['longitude']+'" y2="'+next['latitude']+'"' +
          ' stroke="blue" stroke-width="0.25"/>' + '\n';
    }
    xmlString += `</g></svg></svg>`;
    return xmlString;
  }


  generateCSV(){
    let headers = 'From, To,';
    let rows = '';
    let places = this.props.itinerary.places;
    let distances = this.props.itinerary.distances;

    if(places.length !== 0) {
      let keys = Object.keys(places[0]);
      keys.forEach((key)=>{
        if(key!=='name'){
          headers += key + ',';
        }
      });
      headers += 'LegDistance,TotalDistance \r';

      let totalDistance = 0;
      for(let i = 0; i < places.length;i++){
        totalDistance += distances[i];
        let nextPlace = i+1;
        if(nextPlace > places.length -1){
          nextPlace=0;
        }
        rows += this.generateCSVRow(places[i], places[nextPlace], distances[i], totalDistance);
      }
    }
    return headers + rows;
  }

  generateCSVRow(place,nextPlace, dist, totalDist){
    let name = place.name;
    let row = name+','+nextPlace.name+',';
    let placeValues = Object.values(place);
    placeValues.forEach((value)=>{
      if(value !== name) {
        row += value + ',';
      }
    });
    row += dist+','+totalDist+' \r';
    return row;
  }


  generateCustomFile(){
    let fileType = this.state.dropDownFileType;
    if(this.props.itinerary.places.length === 0){
      alert('No places in itinerary');
      return;
    }
    console.log(fileType);
    let dataString;
    let fileName;
    switch(fileType){
      case 'Excel or other table tools (.CSV)':
        dataString = this.generateCSV();
        fileName = 'My-Itinerary.csv';
        break;
      case 'Visual Map for Browsers (.SVG)':{
        dataString = this.generateSVG();
        fileName = 'My-Itinerary.svg';
        break;
      }
      case 'Google Earth/other tools (.KML)':{
        dataString = this.generateKML();
        fileName = 'My-Itinerary.kml';
        break;
      }
      case 'Default Itinerary':{
        dataString = JSON.stringify(this.props.itinerary);
        fileName = 'My-Itinerary.json';
        break;
      }
      default:
        alert('Must select a file type');
        return;
    }
    this.DownloadCustomFile(dataString, fileName);
  }

  DownloadCustomFile(data, fileName){
    let doc = document.createElement('a');
    document.body.appendChild(doc);
    doc.style = 'display: none';
    let blob = new Blob([data], {type: "octet/stream"});
    let url = window.URL.createObjectURL(blob);
    doc.href= url;
    doc.download = fileName;
    doc.click();
    window.URL.revokeObjectURL(url);
  }

  toggleDropDown(){
    this.setState(prevState =>({
      dropDownOpen: !prevState.dropDownOpen
    }));
  }

  dropDownChangeValue(e){
    this.setState({
      dropDownFileType: e.currentTarget.textContent
    });
  }

  createDropDown(){
    return(
        <Dropdown
            isOpen={this.state.dropDownOpen}
            toggle={this.toggleDropDown}>
          <DropdownToggle className={'m-1 w-100'}
                          style={{ whiteSpace: 'normal'}}
                          caret
                          color={'success'}>
                          {this.state.dropDownFileType}
          </DropdownToggle>
          <DropdownMenu right>
            <DropdownItem onClick={(e)=>{this.dropDownChangeValue(e)}}>Default Itinerary</DropdownItem>
            <DropdownItem onClick={(e)=>{this.dropDownChangeValue(e)}}>Excel or other table tools (.CSV)</DropdownItem>
            <DropdownItem onClick={(e)=>{this.dropDownChangeValue(e)}}>Google Earth/other tools (.KML)</DropdownItem>
            <DropdownItem onClick={(e)=>{this.dropDownChangeValue(e)}}>Visual Map for Browsers (.SVG)</DropdownItem>
          </DropdownMenu>
        </Dropdown>);
  }

  createForm() {
    return (
      <Container>
        <Row>
          <Col>
            <Label>Upload Your Trip Itinerary</Label>
            <Input type={"file"} name={"file"} onChange={this.handleUpload}/>
            <hr></hr>
            <Row>
              <Col>
                {this.createDropDown()}
                <Button className={'m-1 w-100'}
                    onClick={()=>{this.generateCustomFile()}}
                    color={'success'}>
                    Export
                </Button>

              </Col>
            </Row>
          </Col>

          <Col>
            <hr></hr>
            <ButtonGroup className={'m-2 w-100'}>
                <Button onClick={this.handleClickShow}
                        type={"button"}
                        id={"showMarkerButton"}
                        color={"secondary"}>Show Markers</Button>
                <Button onClick={this.handleClickHide}
                        type={"button"}
                        id={"hideMarkerButton"}
                        color={"success"}>Hide Markers</Button>
            </ButtonGroup>
          </Col>
        </Row>
      </Container>);
  }

  render() {
    return (
      <Pane header={"Plan your Trip"} bodyJSX={this.createForm()}/>
    );
  }

  handleClickShow() {

    let newShowMarker = [];
    let places = this.props.itinerary.places;

    for (let i = 0; i < places.length; i++) {
      newShowMarker[places[i].name] = true;
    }
    this.props.updateShowMarker(newShowMarker);

  }

  handleClickHide() {

    let newShowMarker = [];
    let places = this.props.itinerary.places;

    for (let i = 0; i < places.length; i++) {
      newShowMarker[places[i].name] = false;
    }
    this.props.updateShowMarker(newShowMarker);

  }

  convertToDecimal(itinerary) {

    let newItinerary = itinerary;
    let newPlaces = [];

    for (let placeIndex = 0; placeIndex < itinerary.places.length; placeIndex++) {
      let newPlace = itinerary.places[placeIndex];
      let newPlaceCoordinate = new Coordinates(itinerary.places[placeIndex].latitude.toString() + ' ' + itinerary.places[placeIndex].longitude.toString());
      newPlace.latitude = newPlaceCoordinate.getLatitude().toString();
      newPlace.longitude = newPlaceCoordinate.getLongitude().toString();
      newPlaces.push(newPlace);
    }

    newItinerary.places = newPlaces;
    return newItinerary;
  }

}
