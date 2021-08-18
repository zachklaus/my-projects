import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Optimizations from "../src/components/Application/Options/Options";

const startProperties = {
  "options": { "units": {"miles":3959, "kilometers":6371}},
  "activeUnit": "miles",
  "optimizationLevel": "none",
  "serverConfig": {
    "serverName":"T18 Ctrl Alt Elite",
    "placeAttributes":["name","latitude","longitude","id","municipality","region","country","continent","altitude"],
    "optimizations":["none","short","shorter"],
    "filters":[{"values":["airport","heliport","balloonport","closed"],"name":"type"}],
    "requestVersion":4,
    "requestType":"config"
  }
};

function testButtonValues() {

  //console.log(config.optimizations);

  const optimizations = mount((
    <Optimizations options={startProperties.options}
                   config={startProperties.serverConfig}
                   optimizationLevel={startProperties.optimizationLevel}/>
  ));

  let actual = [];
  optimizations.find('#id_button_none').map((element) => actual.push(element.prop('value')));
  optimizations.find('#id_button_short').map((element) => actual.push(element.prop('value')));
  optimizations.find('#id_button_shorter').map((element) => actual.push(element.prop('value')));
  let expected = ["none", "none", "short", "short", "shorter", "shorter"];

  expect(actual).toEqual(expected);


}

test('Check to see if Buttons are rendered for each optimization level option', testButtonValues);