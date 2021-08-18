import './enzyme.config.js'
import React from 'react'
import {mount, shallow} from 'enzyme'
import ItineraryTable from '../src/components/Application/Home/ItineraryTable'
import {Table} from 'reactstrap'
import {ButtonGroup} from 'reactstrap';
import ChangeItinerary from "../src/components/Application/Home/ChangeItinerary";

const startingProps = {

    itinerary: {
        "requestType": "itinerary", "requestVersion": 2,
        "options": {"title": "My Trip", "earthRadius": "3958.761316"},
        "places": [{"id": "dnvr", "name": "Denver", "latitude": "39.7392", "longitude": "-104.9903"}, {
            "id": "bldr",
            "name": "Boulder",
            "latitude": "40.01499",
            "longitude": "-105.27055"
        }, {"id": "foco", "name": "Fort Collins", "latitude": "40.585258", "longitude": "-105.084419"}],
        "distances": [29, 58, 65]
    },

    "requestType" :"itinerary","requestVersion":2,
    "options":{"title":"My Trip","earthRadius":"3958.761316"},
    "places":[{"id":"dnvr","name":"Denver","latitude":"39.7392","longitude":"-104.9903"},{"id":"bldr","name":"Boulder","latitude":"40.01499","longitude":"-105.27055"},{"id":"foco","name":"Fort Collins","latitude":"40.585258","longitude":"-105.084419"}],
    "distances": [29,58,65],

    attributes:['name', 'latitude', 'longitude', 'id', 'municipality', 'altitude'],
    updateItinerary: () => null

};

const testShowMarker = {
    'Denver': false,
    'Fort Collins': false
};

const testShowMarker2 = {
    'Denver': false,
    'Boudler': false
};

function testItineraryTable(){
    const sampleIT = shallow(<ItineraryTable itinerary = {startingProps} showMarker={testShowMarker}/>);


    expect(sampleIT.contains(<tr></tr>)).toEqual(false);
    expect(sampleIT.contains(<Table/>)).toEqual(false);
}

test('Testing EmptyItineraryTable element', testItineraryTable);
function testTable(){
    const sampleItinerary = shallow((<ItineraryTable itinerary={startingProps.itinerary}
                                                     updateItinerary={startingProps.updateItinerary}
                                                     showMarker={testShowMarker2}/>));


    expect(sampleItinerary.contains(<div/>)).toEqual(false);
}
test('Testing testTable method', testTable);


function testEmptyRemove(){
    let emptyPlaces = [];
    const sampleItinerary = mount((
        <ItineraryTable     itinerary = {startingProps.itinerary}
                            updateItinerary={startingProps.updateItinerary}
                            showMarker={testShowMarker2}/>
    ));


    let result = sampleItinerary.instance().handleDeleteButtonClick(emptyPlaces[0]);

    expect(result).toEqual(undefined);
}

test('Testing Handle DeleteButtonClick with empty array', testEmptyRemove);


function testSwap(){
    let samplePlaces = startingProps.itinerary.places;
    // const app = mount((
    //     <Application    itinerary = {startingProps.itinerary}/>
    //     ));

    const sampleItinerary = mount((
        <ItineraryTable     itinerary = {startingProps.itinerary}
                            updateItinerary={startingProps.updateItinerary}
                            showMarker={testShowMarker2}/>
    ));


    //let result = app.ItineraryTable.handleSwap(samplePlaces[1], samplePlaces[2]);
    let result = sampleItinerary.instance().handleSwap(samplePlaces[1], samplePlaces[2]);

    expect(result).toEqual(undefined);
}

test('Testing handle Swap method', testSwap);

function teststartingPoint(){
    let samplePlaces = startingProps.itinerary.places;
    const sampleItinerary = mount((
        <ItineraryTable     itinerary = {startingProps.itinerary}
                            updateItinerary={startingProps.updateItinerary}
                            showMarker={testShowMarker2}/>
    ));


    let result = sampleItinerary.instance().handleNewStartPointButtonClick(samplePlaces[1]);

    expect(result).toEqual(undefined);
}

test('Test handling of changing starting point.' , teststartingPoint);