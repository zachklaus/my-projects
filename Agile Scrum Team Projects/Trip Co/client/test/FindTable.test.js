import './enzyme.config.js'
import React from 'react'
import { shallow, mount } from 'enzyme'
import FindTable from '../src/components/Application/Home/FindTable'
import {Table, Input, Button} from 'reactstrap'
import {sendServerRequestWithBody} from "../src/api/restfulAPI";
import JsonSchemas from "../src/components/Application/Misc/JsonSchemas";

const startingProps = {
  "requestType"    : "find",
  "requestVersion" : 3,
  "match"          : "bright",
  "limit"          : 10,
  "found"          : 1,
  "places"         : [
    {
      "name": "Brighton", "id": 1, "latitude": "39.87",
      "longitude": "-104.33", "municipality": "Adams County",
      "altitude": "4984"
    },
  ]
};

const place = {
  "name": "Brighton", "id": 1, "latitude": "39.87",
  "longitude": "-104.33", "municipality": "Adams County",
  "altitude": "4984"
};

const itinerary = {
  "requestType": "itinerary", "requestVersion": 2,
    "options": {"title": "My Trip", "earthRadius": "3958.761316"},
  "places": [{"id": "dnvr", "name": "Denver", "latitude": "39.7392", "longitude": "-104.9903"}, {
    "id": "bldr",
    "name": "Boulder",
    "latitude": "40.01499",
    "longitude": "-105.27055"
  }, {"id": "foco", "name": "Fort Collins", "latitude": "40.585258", "longitude": "-105.084419"}],
    "distances": [29, 58, 65]
};

function testEmptyFindTable(){
  const findTable = shallow(<FindTable find = {startingProps}
                                       itinerary={itinerary}/>);
  expect(findTable.contains(<tr></tr>)).toEqual(false);
  expect(findTable.contains(<Table/>)).toEqual(false);
}

test('Testing empty find table element', testEmptyFindTable);

function testCheckBoxRender() {
  const findTable = mount(<FindTable find = {startingProps}
                                     itinerary={itinerary}/>);
  const checkBox = findTable.find(Input);
  expect(checkBox.prop('id')).toEqual('checkbox_Brighton');
}

test('Test checkbox renders', testCheckBoxRender);
