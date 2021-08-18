import './enzyme.config.js'

import React from 'react'
import { shallow } from 'enzyme'
import About from '../src/components/Application/About/About'

const startProperties = {
    'data': {'name' : { 'name': 'John'}},
    'image' : 'test'
};


function testRender() {
    const about = shallow(<About/>);


    expect(about.contains(about.instance().createHeader())).toEqual(true);
}


test("Testing to see if about page renders", testRender);


function testabout() {
    const about = shallow(<About/>);

    expect(about.contains(about.instance().createCard(startProperties.data, startProperties.image))).toEqual(false);
}

test("Test about things", testabout);