import './enzyme.config.js';
import React from 'react';
import {mount, shallow} from 'enzyme';
import Home from '../src/components/Application/Home';
import {Map, Marker, Popup, TileLayer} from "react-leaflet";
import MapPane from '../src/components/Application/MapPane';


const itinerary = {
  "requestType": "itinerary",
  "requestVersion": 4,
  "options": {"title": "Colorado County Seats", "earthRadius": "3440"},
  "places": [
    {"name": "Brighton", "id": 1, "latitude": "39.87",
      "longitude": "-104.33", "municipality": "Adams County",
      "altitude":"4984"}
  ],
  "distances":[0]
};

const itinerary2 = {

  "requestType": "itinerary",
  "requestVersion": 4,
  "options": {"title": "Colorado County Seats", "earthRadius": "3440"},
  "places": [],
  "distances":[]

};

const itinerary3 = {

  "requestType": "itinerary",
  "requestVersion": 4,
  "options": {"title": "Colorado County Seats", "earthRadius": "3440"},
  "places": [
    {"name": "Brighton", "id": 1, "latitude": "39.87",
      "longitude": "-104.33", "municipality": "Adams County",
      "altitude":"4984"},
    {"name": "Brighton", "id": 1, "latitude": "39.87",
      "longitude": "-104.33", "municipality": "Adams County",
      "altitude":"4984"}
  ],
  "distances":[0]

};

const itinerary4 = {

  "requestType": "itinerary",
  "requestVersion": 3,
  "options": {"title": "My Trip", "earthRadius": "3959", "optimization": "none"},
  "places": [
    {
      "name": "Brighton", "id": 1, "latitude": "39.87",
      "longitude": "-104.33", "municipality": "Adams County",
      "altitude": "4984"
    },
    {
      "name": "AnotherPlace", "id": 3, "latitude": "50.32",
      "longitude": "-101.13", "municipality": "Somewhere fun",
      "altitude": "2984"
    },
    {
      "name": "YetAnotherPlace", "id": 4, "latitude": "30.12",
      "longitude": "-98.1", "municipality": "Somewhere really fun",
      "altitude": "1984"
    }
  ],
  "distances": [1903, 452, 64]
};

const startingProps = {
  itinerary: {
    "requestType": "itinerary",
    "requestVersion": 2,
    "options": {"title": "Colorado County Seats", "earthRadius": "3440"},
    "places": [
      {
        "name": "Brighton", "id": 1, "latitude": "39.87",
        "longitude": "-104.33", "municipality": "Adams County",
        "altitude": "4984"
      },
      {
        "name": "Glenwood Springs", "id": 24, "latitude": "39.6",
        "longitude": "-107.91", "municipality": "Garfield County",
        "altitude": "9001"
      }
    ],
    "distances": [0, 420]
  },
  find: {
    "requestType"    : "find",
    "requestVersion" : 3,
    "match"          : "fort",
    "limit"          : 10,
    "found"          : 3,
    "places"         : [
      {
        "name": "Brighton", "id": 1, "latitude": "39.87",
        "longitude": "-104.33", "municipality": "Adams County",
        "altitude": "4984"
      },
      {
        "name": "AnotherPlace", "id": 3, "latitude": "50.32",
        "longitude": "-101.13", "municipality": "Somewhere fun",
        "altitude": "2984"
      },
      {
        "name": "YetAnotherPlace", "id": 4, "latitude": "30.12",
        "longitude": "-98.1", "municipality": "Somewhere really fun",
        "altitude": "1984"
      }
    ]
  },
  serverConfig: {
    placeAttributes: ['name', 'latitude', 'longitude', 'id', 'municipality', 'altitude'],
    filters         : [{'name': 'type', 'values': ['airport','heliport','balloonport','closed']}]
  }
};

/*
const place1 = {
  "name": "Brighton", "id": 1, "latitude": "39.87",
  "longitude": "-104.33", "municipality": "Adams County",
  "altitude": "4984"
};

const place2 = {
  "name": "Glenwood Springs", "id": 24, "latitude": "39.6",
  "longitude": "-107.91", "municipality": "Garfield County",
  "altitude": "9001"
};
]*/

const testShowMarker = {
  'Brighton': 'false',
  'Glenwood Springs': 'true'
};

const testShowMarker2 = {
  'Brighton': 'true',
};

const testShowMarker3 = {
  'Brighton': 'false',
  'AnotherPlace': 'false',
  'YetAnotherPlace': 'true'
};

function testDetailButtonToggle() {

  const home = mount(<Home itinerary={startingProps.itinerary} serverConfig={startingProps.serverConfig} find={startingProps.find} showMarker={testShowMarker}/>);
  // expect button exists
  expect(home.find({id: 'detailToggle'}).find("Button")).toHaveLength(1);

  // expect initial states
  const ChangeItinerary = home.find("ChangeItinerary").first();
  expect(ChangeItinerary.state("buttonActive")).toEqual(false);
  expect(home.state("showDetailed")).toEqual(false);

  // expect state change on click
  const button = home.find({id: 'detailToggle'}).find("Button").first();
  button.simulate('click');
  expect(ChangeItinerary.state("buttonActive")).toEqual(true);
  expect(home.state("showDetailed")).toEqual(true);
}

test("Test that details are toggleable", testDetailButtonToggle);

function testMapNoPlaces() {

  const home = mount(<Home itinerary={itinerary2} serverConfig={startingProps.serverConfig} find={startingProps.find} showMarker={testShowMarker}/>);
  const map = home.find(MapPane);

  expect(map.prop('id')).toEqual('map_no_places');

}

test("Test that correct map renders when itinerary has no places", testMapNoPlaces);

function testMapOnePlace() {

  const home = mount(<Home itinerary={itinerary} serverConfig={startingProps.serverConfig} find={startingProps.find} showMarker={testShowMarker2}/>);
  const map = home.find(MapPane);

  expect(map.prop('id')).toEqual('map_one_place');
}

test("Test that correct map renders when itinerary has one place", testMapOnePlace);

function testMapSamePlaces() {

  const home = mount(<Home itinerary={itinerary3} serverConfig={startingProps.serverConfig} find={startingProps.find} showMarker={testShowMarker2}/>);
  const map = home.find(MapPane);

  expect(map.prop('id')).toEqual('map_same_places');

}

test("Test that correct map renders when itinerary has same places", testMapSamePlaces);

function testMapOtherwise() {

  const home = mount(<Home itinerary={itinerary4} serverConfig={startingProps.serverConfig} find={startingProps.find} showMarker={testShowMarker3}/>);
  const map = home.find(MapPane);

  expect(map.prop('id')).toEqual('map_otherwise');

}

test("Test that correct map renders when itinerary has more than one different places", testMapOtherwise);
