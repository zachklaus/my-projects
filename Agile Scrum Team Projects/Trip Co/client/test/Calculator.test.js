import './enzyme.config.js';
import React from 'react';
import {mount, shallow} from 'enzyme';
import Calculator from '../src/components/Application/Calculator/Calculator';
import Application from '../src/components/Application/Application';
import Home from "./Home.test";
import MapPane from "../src/components/Application/MapPane";
import {getOriginalServerPort} from "../src/api/restfulAPI";
import GeoMap from "../src/components/Application/Home/GeoMap";

const startingProps = {
  showMarker: [{'Origin':true}, {'Destination':true}],
  itinerary: {
    "requestType": "itinerary", "requestVersion": 2,
    "options": {"title": "My Trip", "earthRadius": "3958.761316"},
    "places": [{"id": "dnvr", "name": "Denver", "latitude": "39.7392", "longitude": "-104.9903"}],
    "distances": [29, 58, 65]
  }
};


const calcData1 = {
  origin: {latitude: '37', longitude: '-102'},
  destination: {latitude: '37', longitude: '-102'},
  originPoint: L.latLng(40.576179, -105.080773),
  destinationPoint: L.latLng(40.576179, -105.080773)
};

const calcData2 = {
  origin: {latitude: '37', longitude: '-102'},
  destination: {latitude: '30', longitude: '-100'},
  originPoint: L.latLng(40.576179, -105.080773),
  destinationPoint: L.latLng(35.33, -100.11)
};

const serverConfig = {
  'server' : {
    'serverName': "T18 Ctrl Alt Elite",
    'optimizations' : {'none': 'none'},
    'placeAttributes' : {"name": '', "latitude" : '', "longitude": '', "id": '', "municipality": '', "altitude": ''},
    'filters'         : [{'name': 'type',
                          'values': ['airport','heliport','balloonport','closed']}],
    'requestType': 'config',
    'requestVersion' : 5
  }
};

const options = {
  planOptions: {
    units: {'miles': 3959, 'kilometers': 6371, 'nautical miles': 3440, 'user defined': -1},
    activeUnit: 'miles',
    optimizationLevel: 'none'
  }
};

function testCreateInputFields() {
  const app = mount((
      <Application page={'calc'}/>
  ));
  app.setState({serverConfig: serverConfig.server});
  app.instance().forceUpdate();
  app.update();
  let numberOfInputs = app.find('Calculator').at(0).find('Input').length;
  expect(numberOfInputs).toEqual(4);

  let actualInputs = [];
  app.find('Input').map((input) => actualInputs.push(input.prop('name')));

  let expectedInputs = [
    'latitude',
    'longitude',
    'latitude',
    'longitude'
  ];

  expect(actualInputs).toEqual(expectedInputs);
}

/* Tests that createForm() correctly renders 4 Input components */
test('Testing the createForm() function in Calculator', testCreateInputFields);

function testInputsOnChange() {
  const app = mount((
      <Application page={'calc'}/>
  ));

  app.setState({serverConfig: serverConfig.server});
  app.instance().forceUpdate();
  app.update();
  
  for (let inputIndex = 0; inputIndex < 4; inputIndex++){
    simulateOnChangeEvent(inputIndex, app);
  }

  expect(app.state().calculatorData.origin.latitude).toEqual(0);
  expect(app.state().calculatorData.origin.longitude).toEqual(1);
  expect(app.state().calculatorData.destination.latitude).toEqual(2);
  expect(app.state().calculatorData.destination.longitude).toEqual(3);
}

function simulateOnChangeEvent(inputIndex, reactWrapper) {
  let eventName = (inputIndex % 2 === 0) ? 'latitude' : 'longitude';
  let event = {target: {name: eventName, value: inputIndex}};
  switch(inputIndex) {
    case 0:
      reactWrapper.find('#originLatitude').at(0).simulate('change', event);
      break;
    case 1:
      reactWrapper.find('#originLatitude').at(0).simulate('change', event);
      break;
    case 2:
      reactWrapper.find('#destinationLatitude').at(0).simulate('change', event);
      break;
    case 3:
      reactWrapper.find('#destinationLongitude').at(0).simulate('change', event);
      break;
    default:
  }
  reactWrapper.update();
}

/* Loop through the Input indexes and simulate an onChange event with the index
 * as the input. To simulate the change, an event object needs to be created
 * with the name corresponding to its Input 'name' prop. Based on the index,
 * find the corresponding Input by its 'id' prop and simulate the change.
 *
 * Note: using find() with a prop as a selector for Inputs will return 2 objects:
 * 1: The function associated with the Input that is created by React
 * 2: The Input component itself
 *
 * The values in state() should be the ones assigned in the simulations.
 *
 * https://github.com/airbnb/enzyme/blob/master/docs/api/ShallowWrapper/simulate.md
 * https://airbnb.io/enzyme/docs/api/ReactWrapper/props.html
 * https://airbnb.io/enzyme/docs/api/ReactWrapper/find.html
 */
test('Testing the onChange event of longitude Input in Calculator', testInputsOnChange);

/*
function testMapSamePoints() {

  const calculator = mount(<Calculator calculatorData={calcData1}
                                       options={options}/>);
  const map = calculator.find(MapPane);

  expect(map.prop('id')).toEqual('map_otherwise');

}
*/

function testMapSamePoints() {
  let place = {"id": "dnvr", "name": "Denver", "latitude": "39.7392", "longitude": "-104.9903"};
  startingProps.itinerary.places.push(place);
  const calculator = mount(<Calculator options={options}
                                       settings={null}
                                       calculatorData={calcData1}
                                       updateCalculatorPoints={null}
                                       createErrorBanner={null}/>);

  const geoMap = shallow(<GeoMap itinerary={startingProps.itinerary}
                                 showMarker={startingProps.showMarker}/>);

  const map = geoMap.find(MapPane);
  expect(map.prop('id')).toEqual('map_same_places');

}

test("Test that correct map renders when the points are the same", testMapSamePoints);

function testMapNotSamePoints() {

  let place =  {"id": "foco", "name": "Fort Collins", "latitude": "40.585258", "longitude": "-105.084419"};
  startingProps.itinerary.places.push(place);
  const calculator = shallow(<Calculator options={options}
                                       settings={null}
                                       calculatorData={calcData2}
                                       updateCalculatorPoints={null}
                                       createErrorBanner={null}/>);

  const geoMap = shallow(<GeoMap itinerary={startingProps.itinerary}
                                  showMarker={startingProps.showMarker}/>);

  const map = geoMap.find(MapPane);
  expect(map.prop('id')).toEqual('map_otherwise');
}

test("Test that correct map renders when the points aren't the same", testMapNotSamePoints);