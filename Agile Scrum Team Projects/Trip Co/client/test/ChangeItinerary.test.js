import './enzyme.config.js'
import React from 'react'
import {mount, shallow} from 'enzyme'
import ChangeItinerary from "../src/components/Application/Home/ChangeItinerary";
import FileManager from "../src/components/Application/Home/FileManager";
import MapXMLString from "../src/components/Application/Misc/MapXMLString";

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
  attributes:['name', 'latitude', 'longitude', 'id', 'municipality', 'altitude'],
  updateItinerary: () => null
};

const serverConfig = {
        placeAttributes: ['name', 'latitude', 'longitude', 'id', 'municipality', 'altitude'],
        filters         : [{'name': 'type', 'values': ['airport','heliport','balloonport','closed']}]
};

function testButtons() {
  const changeItinerary = mount((
    <ChangeItinerary itinerary={startingProps.itinerary}
                     attributes={startingProps.attributes}
                     updateItinerary={startingProps.updateItinerary}
                     find={startingProps.find}
                     serverConfig={serverConfig}/>
  ));

  let actualButtons = [];
  changeItinerary.find('Button').map((button) => actualButtons.push(button));

  for (let button of actualButtons) {

    if (button.prop('color') === 'success'){
      expect(button.prop('color')).toEqual('success');
    }
    else {
      expect(button.prop('color')).toEqual('secondary');
    }
  }
}

test('Check that the buttons are the right color.', testButtons);

/*
function testRender() {
  const options = shallow(<Options options={startProperties.options}
                                   config={null}
                                   updateOption={startProperties.updateOption}/>);

  expect(options.contains(<Units options={startProperties.options}
                                 activeUnit={startProperties.options.activeUnit}
                                 updateOption={startProperties.updateOption}/>)).toEqual(true);
}


test('Check to see if a Units component is rendered', testRender);
*/

test('Test that fields are some fields are required', () => {
  const ci = mount(<ChangeItinerary attributes={startingProps.attributes}
                                    itinerary={startingProps.itinerary}
                                    updateItinerary={startingProps.updateItinerary}
                                    find={startingProps.find}
                                    serverConfig={serverConfig}/>);

  let toggle = ci.find('Button').find({id:'toggleModal'}).first();
  toggle.simulate('click');

  let fields = ci.find('Input');
  expect(fields.length).toEqual(startingProps.attributes.length + 1);

  let altitudeField = fields.find({id:'altitude'}).at(0);
  let municipalityField = fields.find({id:'municipality'}).at(0);
  altitudeField.simulate('change', { target: { value: '5030' } });
  municipalityField.simulate('change', { target: { value: 'Fort Collins' } });


  let beforeSubmit = ci.state().showCustomLocationField;
  expect(beforeSubmit).toEqual(true);
  let submit = ci.find('Button').find({id: 'addLocation'}).first();
  submit.simulate('click');
  let afterSubmit = ci.state().showCustomLocationField;
  expect(afterSubmit).toEqual(true);
});

function testFindLocationForm() {


  const ci = mount(<ChangeItinerary attributes={startingProps.attributes}
                                    itinerary={startingProps.itinerary}
                                    updateItinerary={startingProps.updateItinerary}
                                    find={startingProps.find}
                                    serverConfig={serverConfig}/>);

  let matchField = ci.find({id:'searchLoc'}).first();
  matchField.simulate('change', { target: { value: 'fort' }});
}

test('Testing find location form', testFindLocationForm);

function testGenerateCSV(){
  const fileManager = shallow(<FileManager attributes={startingProps.attributes}
                                         itinerary={startingProps.itinerary}
                                         updateItinerary={startingProps.updateItinerary}/>);

    const inst = fileManager.instance();
    inst.setState({
      dropDownFileType: '.CSV'
    });

    let expected = "From, To,id,latitude,longitude,LegDistance,TotalDistance \r" +
        "Denver,Boulder,dnvr,39.7392,-104.9903,29,29 \r" +
        "Boulder,Fort Collins,bldr,40.01499,-105.27055,58,87 \r" +
        "Fort Collins,Denver,foco,40.585258,-105.084419,65,152 \r";
    let headerAndRows = inst.generateCSV('file', 'test.tst');

    expect(expected === headerAndRows).toEqual(true);
}

test('test generateCSV in fileManager', testGenerateCSV);

function testGenerateSVG(){
  const fileManager = shallow(<FileManager attributes={startingProps.attributes}
                                           itinerary={startingProps.itinerary}
                                           updateItinerary={startingProps.updateItinerary}/>);

  const inst = fileManager.instance();
  inst.setState({
    dropDownFileType: '.SVG'
  });

  let expected = MapXMLString.XML + '\n'+
      "<line  x1=\"-104.9903\" y1=\"39.7392\" x2=\"-105.27055\" y2=\"40.01499\" stroke=\"blue\" stroke-width=\"0.25\"/>\n" +
      "<line  x1=\"-105.27055\" y1=\"40.01499\" x2=\"-105.084419\" y2=\"40.585258\" stroke=\"blue\" stroke-width=\"0.25\"/>\n" +
      "<line  x1=\"-105.084419\" y1=\"40.585258\" x2=\"-104.9903\" y2=\"39.7392\" stroke=\"blue\" stroke-width=\"0.25\"/>\n" +
      "</g></svg></svg>";

  let xml = inst.generateSVG();

  expect(expected === xml).toEqual(true);
}

test('test generateSVG in fileManager', testGenerateSVG);

function testGenerateKML(){
  const fileManager = shallow(<FileManager attributes={startingProps.attributes}
                                           itinerary={startingProps.itinerary}
                                           updateItinerary={startingProps.updateItinerary}/>);

  const inst = fileManager.instance();
  inst.setState({
    dropDownFileType: '.KML'
  });

  let expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
      "<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\n" +
      "<Document>\n" +
      "<name>SVG</name>\n" +
      "<open>1</open>\n" +
      "<description>KML of itinerary</description>\n" +
      "<Style id=\"lineStyle\">\n" +
      "<LineStyle><color>ffffffb6</color><width>4</width></LineStyle></Style><Placemark>\n" +
      "<name>Denver To Boulder</name>\n" +
      "<styleUrl>#lineStyle</styleUrl>\n" +
      "<LineString><coordinates>-104.9903,39.7392,0\n" +
      "-105.27055,40.01499,0</coordinates>\n" +
      "</LineString></Placemark><Placemark>\n" +
      "<name>Boulder To Fort Collins</name>\n" +
      "<styleUrl>#lineStyle</styleUrl>\n" +
      "<LineString><coordinates>-105.27055,40.01499,0\n" +
      "-105.084419,40.585258,0</coordinates>\n" +
      "</LineString></Placemark><Placemark>\n" +
      "<name>Fort Collins To Denver</name>\n" +
      "<styleUrl>#lineStyle</styleUrl>\n" +
      "<LineString><coordinates>-105.084419,40.585258,0\n" +
      "-104.9903,39.7392,0</coordinates>\n" +
      "</LineString></Placemark>\n" +
      "</Document>\n" +
      "</kml>";

  let kml = inst.generateKML();

  expect(expected === kml).toEqual(true);
}

test('test generateKML in fileManager', testGenerateKML);

