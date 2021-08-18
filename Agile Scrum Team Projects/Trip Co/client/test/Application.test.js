import './enzyme.config.js'
import React from 'react'
import {shallow} from 'enzyme'
import Application from '../src/components/Application/Application'
import {getOriginalServerPort} from "../src/api/restfulAPI";


let state = {
    serverConfig: null,
    planOptions: {
        units: {'miles': 3959, 'kilometers': 6371, 'nautical miles': 3440, 'user defined': -1},
        activeUnit: 'miles',
        optimizationLevel: 'none'
    },
    clientSettings: {
        serverPort: getOriginalServerPort()
    },
    itinerary: {
        options: null,
        places: [],
        distances: []
    },
    calculatorData: {
        origin: {latitude: '', longitude: ''},
        destination: {latitude: '', longitude: ''},
        originPoint: null,
        destinationPoint: null,
        distance: 0
    },
    errorMessage: null
};


function testInitialState() {
  mockConfigResponse();

  const app = shallow(<Application/>);

  let actualConfig = app.state().serverConfig;
  let expectedConfig = null;
  expect(actualConfig).toEqual(expectedConfig);

  let actualOptions = app.state().planOptions;
  let expectedOptions = {
    units: {"kilometers":6371, "miles": 3959, "nautical miles":3440},
    activeUnit: 'miles',
    optimizationLevel: "none"
  };

  expect(actualOptions).toEqual(expectedOptions);
}

function mockConfigResponse() {
  fetch.mockResponse(JSON.stringify(
      {
        status: 200,
        statusText: 'OK',
        body: {
          'placeAttributes': ["latitude", "longitude", "serverName"],
          'requestType': "config",
          'requestVersion': 1,
          'serverName': "t18"
        },
        type: 'basic',
        url: '',
        redirected: false,
        ok: true
      }));
}

test("Testing Application's initial state", testInitialState);

function testConfigValidate(){
    mockConfigResponseVersion4();
    const app = shallow(<Application/>);
    const inst = app.instance();
    inst.state = state;
    inst.updateServerConfig();

    let config = {
        statusCode: 200,
        statusText: 'OK',
        body: {
            "requestType": "config",
            "requestVersion": 4,
            "serverName": "t18 name",
            "placeAttributes": ["name", "latitude", "longitude", "id", "municipality", "region", "country", "continent", "altitude"],
            "optimizations": ["none", "short"],
            "filters": [{
                "name": "type",
                "values": ["airport", "heliport", "balloonport", "closed"]
            }]
        }
    };
    inst.processConfigResponse(config);
    expect(inst.errorMessage == null).toEqual(true);
}
test("Testing Applications Valid Config validation", testConfigValidate);

function testInvalidConfigValidate(){
    mockConfigResponseVersion4();

    const app = shallow(<Application/>);
    const inst = app.instance();

    inst.state = state;

    inst.updateServerConfig();

    let config = {
        statusCode: 200,
        statusText: 'Invalid',
        body: {
            "requestType": "config",
            "requestVersion": 4,
            "serverName": "t18 name",
            "placeAttributes": ["name", "latitude", "longitude", "id", "municipality", "region", "country", "continent"],
            "optimizations": ["none", "short"],
            "filters": [{
                "name": "type",
                "values": ["airport", "heliport", "balloonport", "closed"]
            }],
            "extravalue": "something Lame"
        }
    };

    inst.processConfigResponse(config);
    expect(inst.state.errorMessage == null).toEqual(false);

}

test("Testing Application Invalid Config Validation", testInvalidConfigValidate);

function mockConfigResponseVersion4() {
    fetch.mockResponse(JSON.stringify(
        {
            status: 200,
            statusText: 'OK',
            body: {
                "requestType"        : "config",
                "requestVersion"     : 4,
                "serverName"         : "t## name",
                "placeAttributes"    : ["name", "latitude", "longitude", "id", "municipality", "region", "country", "continent", "altitude"],
                "optimizations"      : ["none", "short"],
                "filters"            : [{"name": "type",
                    "values": ["airport","heliport","balloonport","closed"]}
                ]
            },
            type: 'basic',
            url: '',
            redirected: false,
            ok: true
        }));
}




const mockItinerary = {
  "requestType": "itinerary",
  "requestVersion": 2,
  "options": { "title": "Colorado Ski Resorts", "earthRadius": "6371", "optimization":"none" },
  "places": [
    { "name": "Winter Park", "id": 16, "latitude": "39.53233", "longitude": "-105.4563333" }
  ],
  "distances": []
};


function testUpdateOption() {
  const app = shallow(<Application/>);

  fetch.mockResponse(JSON.stringify(mockItinerary));

  app.instance().setState({itinerary: mockItinerary});
  app.instance().updatePlanOption("activeUnit", "miles");

  let actualUnit = app.state().planOptions.activeUnit;
  let expectedUnit = "miles";
  expect(actualUnit).toEqual(expectedUnit);
}

test("Testing Application's updatePlanOption function", testUpdateOption);
