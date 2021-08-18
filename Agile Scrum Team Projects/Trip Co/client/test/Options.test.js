import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Options from '../src/components/Application/Options/Options'
import Units from '../src/components/Application/Options/Units'
import CustomUnits from "../src/components/Application/Options/CustomUnits";


const startProperties = {
  'options': {
    'units': {'miles':3959, 'kilometers':6371},
    'activeUnit': 'miles'
  },
  'updateOption' : () => {}
};



  test('Check Units Constructor', () => {
  const obj = new Units();
  expect(obj).toBe(obj);
});

function testRender() {
  const options = shallow(<Options options={startProperties.options}
                                   config={null}
                                   updateOption={startProperties.updateOption}/>);

  expect(options.contains(<Units options={startProperties.options}
                                 activeUnit={startProperties.options.activeUnit}
                                 updateOption={startProperties.updateOption}/>)).toEqual(true);
}

test('Check to see if a Units component is rendered', testRender);

function testCustomUnitsRender(){
  const options = shallow(<Options options={startProperties.options}
                                  config={null}
                                  updateOption={startProperties.updateOption}/>);

  expect(options.contains(<CustomUnits options={startProperties.options}
                                 activeUnit={startProperties.options.activeUnit}
                                 updateOption={startProperties.updateOption}/>)).toEqual(true);
}

test('Check to see if a CustomUnits component is rendered', testCustomUnitsRender);

function testAddRemoveValid(){
  const customUnits = mount(<CustomUnits options={startProperties.options}
                                           activeUnit={startProperties.options.activeUnit}
                                           updateOption={startProperties.updateOption}
                                          createErrorBanner={()=>{}}/>);

  customUnits.find({id:'unitName'}).first().simulate('change', {target: {value: 'testUnit'}});
  customUnits.find({id:'unitRadius'}).first().simulate('change', {target: {value: '12345'}});

  let inst = customUnits.instance();
  customUnits.find('#customAddButton').first().simulate('click');

  expect('testUnit' in inst.state.units).toEqual(true);

  inst.props.options.activeUnit = 'testUnit';
  customUnits.find('#customRemoveButton').first().simulate('click');

  expect('testUnit' in inst.state.units).toEqual(false);
}

test('Test simulate add and remove button click with valid data', testAddRemoveValid);

function testAddRemoveInvalid(){
  const customUnits = mount(<CustomUnits options={startProperties.options}
                                         activeUnit={startProperties.options.activeUnit}
                                         updateOption={startProperties.updateOption}
                                         createErrorBanner={()=>{}}/>);

  customUnits.find({id:'unitName'}).first().simulate('change', {target: {value: 'testUnit'}});
  customUnits.find({id:'unitRadius'}).first().simulate('change', {target: {value: 'adc12345'}});


  let inst = customUnits.instance();

  customUnits.find('#customAddButton').first().simulate('click');

  expect('testUnit' in inst.state.units).toEqual(false);

  inst.props.options.activeUnit = 'miles';
  customUnits.find('#customRemoveButton').first().simulate('click');

  expect('miles' in inst.state.units).toEqual(true);

}

test('Test simulate add and remove button with invalid data', testAddRemoveInvalid);