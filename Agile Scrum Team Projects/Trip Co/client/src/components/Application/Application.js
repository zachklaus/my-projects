import React, {Component} from 'react';
import {Container} from 'reactstrap';

import Home from './Home';
import Options from './Options/Options';
import About from './About/About';
import Calculator from './Calculator/Calculator';
import Settings from './Settings/Settings';
import {getOriginalServerPort, sendServerRequest, sendServerRequestWithBody} from '../../api/restfulAPI';
import ErrorBanner from './ErrorBanner';
import JsonSchemas from "./Misc/JsonSchemas";
import Cookies from 'universal-cookie';


/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
export default class Application extends Component {
  constructor(props){
    super(props);

    this.updatePlanOption = this.updatePlanOption.bind(this);
    this.updateClientSetting = this.updateClientSetting.bind(this);
    this.createApplicationPage = this.createApplicationPage.bind(this);
    this.updateItinerary = this.updateItinerary.bind(this);
    this.updateCalculatorPoints = this.updateCalculatorPoints.bind(this);
    this.updateCalculatorDistance = this.updateCalculatorDistance.bind(this);
    this.updateFind = this.updateFind.bind(this);
    this.updateShowMarker = this.updateShowMarker.bind(this);

    //get cookie
    this.customUnitsName = 'customUnits';
    let cookie = new Cookies();
    let units = cookie.get(this.customUnitsName);
    //set units to default if no cookie is found
    if(units == null || units.length === 0){
      units = {'miles':3959, 'kilometers':6371, 'nautical miles':3440};
    }

    this.state = {
      serverConfig: null,
      planOptions: {
        units: units,
        activeUnit: 'miles',
        optimizationLevel: 'none'
      },
      clientSettings: {
        serverPort: getOriginalServerPort()
      },
      itinerary: {
        requestVersion:4,
        requestType:'itinerary',
        options: {},
        places: [],
        distances: []
      },
      calculatorData:{
        origin: {latitude: '', longitude: ''},
        destination: {latitude: '', longitude: ''},
        originPoint: null,
        destinationPoint: null,
        distance: 0
      },
      find: {
        requestType: "find",
        requestVersion: 5,
        match: null,
        narrow: [],
        limit: 10,
        found: 0,
        places: []
      },
      errorMessage: null,
      showMarker: []
    };
    this.updateServerConfig();
  }

  render() {
    let pageToRender = this.state.serverConfig ? this.props.page : 'settings';

    return (
        <div className='application-width'>
          { this.state.errorMessage }{ this.createApplicationPage(pageToRender) }
        </div>
    );
  }

  refreshItinerary(){
    this.updateItinerary(Object.assign({}, this.state.itinerary));
  }

  validateItineraryRequest(value){
    return JsonSchemas.validate(value, JsonSchemas.Schemas.ITINERARY);
  }

  updateItinerary(value, dontOptimize=false) {

    if(value.options !== null){
      value.options.earthRadius = String(this.state.planOptions.units[this.state.planOptions.activeUnit]);

      if (dontOptimize === true) {
        value.options.optimization = "none";
      }else {
        value.options.optimization = this.state.planOptions.optimizationLevel;
      }

      if(!this.validateItineraryRequest(value)){
        this.setState({errorMessage: this.createErrorBanner(
              'Invalid Request',
              400,
              'Request Not Sent')});
        return;
      }

      sendServerRequestWithBody("itinerary", value, this.state.clientSettings.serverPort).then
      (response =>{
        if(response.statusCode >= 200 && response.statusCode <= 299) {
          if(JsonSchemas.validate(response.body,JsonSchemas.Schemas.ITINERARY)) {
            this.setState({itinerary: response.body, errorMessage:null})
          }

          else{
            let msg = `Itinerary Response from ${this.state.clientSettings.serverPort} is invalid.`;
            this.setState({errorMessage: this.createErrorBanner(response.statusText, 400, msg)})
          }
        }
        else {
          let msg;
          if (response.statusCode >= 400 && response.statusCode <= 499) {
            msg = `Response from ${this.state.clientSettings.serverPort} is invalid.`
          } else {
            msg =  `Request to ${this.state.clientSettings.serverPort} failed.`;
          }
          this.setState({errorMessage: this.createErrorBanner(response.statusText, response.statusCode, msg)})
        }
      });
    }
  }

  updateFind(newFind, dontSendNewRequest=false) {

    if (dontSendNewRequest) {
      this.setState({find: newFind});
    }else {
      sendServerRequestWithBody("find", newFind, this.state.clientSettings.serverPort).then(response =>{
        this.setState({find: response.body})});
    }
  }

  updateClientSetting(field, value) {
    if(field === 'serverPort')
      this.setState({clientSettings: {serverPort: value}}, this.updateServerConfig);
    else {
      let newSettings = Object.assign({}, this.state.planOptions);
      newSettings[field] = value;
      this.setState({clientSettings: newSettings});
    }
  }

  updatePlanOption(option, value) {
    let optionsCopy = Object.assign({}, this.state.planOptions);
    optionsCopy[option] = value;
    this.setState({'planOptions': optionsCopy}, this.refreshItinerary);

    if (this.state.calculatorData.distance !== 0) {
      this.updateCalculatorDistance(optionsCopy.activeUnit);
    }
  }

  updateServerConfig() {
    sendServerRequest('config', this.state.clientSettings.serverPort).then(config => {
      this.processConfigResponse(config);
    });
  }

  updateCalculatorDistance(activeUnit) {

    const request = {
      'requestType': 'distance',
      'requestVersion': 4,
      'origin': {
        latitude: this.state.calculatorData.origin.latitude,
        longitude: this.state.calculatorData.origin.longitude
      },
      'destination': {
        latitude: this.state.calculatorData.destination.latitude,
        longitude: this.state.calculatorData.destination.longitude
      },
      'earthRadius': this.state.planOptions.units[activeUnit]
    };

    sendServerRequestWithBody('distance', request, this.state.clientSettings.serverPort)
      .then((response) => {
          this.updateCalculatorPoints('distance', response.body.distance);
        }
      );
  }

  updateCalculatorPoints(field, data){
    let calculatorCopy = Object.assign({}, this.state.calculatorData);
    calculatorCopy[field] = data;
    this.setState({calculatorData : calculatorCopy});
  }

  createErrorBanner(statusText, statusCode, message) {
    return (
        <ErrorBanner statusText={statusText}
                     statusCode={statusCode}
                     message={message}/>
    );
  }

  createApplicationPage(pageToRender) {
    switch(pageToRender) {
      case 'calc':
        return <Calculator options={this.state.planOptions}
                           settings={this.state.clientSettings}
                           calculatorData={this.state.calculatorData}
                           updateCalculatorPoints={this.updateCalculatorPoints}
                           createErrorBanner={this.createErrorBanner}/>;
      case 'options':
        return <Options options={this.state.planOptions}
                        config={this.state.serverConfig}
                        updateOption={this.updatePlanOption}
                        createErrorBanner={this.createErrorBanner}
                        itinerary={this.state.itinerary}/>;
      case 'settings':
        return <Settings settings={this.state.clientSettings}
                         serverConfig={this.state.serverConfig}
                         updateSetting={this.updateClientSetting}/>;
      case 'about':
        return <About/>;
      default:
        return <Home updateItinerary={this.updateItinerary}
                     itinerary={this.state.itinerary}
                     serverConfig={this.state.serverConfig}
                     find={this.state.find}
                     updateFind={this.updateFind}
                     showMarker={this.state.showMarker}
                     updateShowMarker={this.updateShowMarker}/>;
    }
  }

  processConfigResponse(config) {
    if(config.statusCode >= 200 && config.statusCode <= 299) {
      if(JsonSchemas.validate(config.body, JsonSchemas.Schemas.CONFIG)) {
        console.log("Switching to server ", this.state.clientSettings.serverPort);
        this.setState({
          serverConfig: config.body,
          errorMessage: null
        });
      }
      else{
        this.setState({errorMessage: this.createErrorBanner(
              'Invalid Response',
              400,
              `Invalid Response from server ${this.state.clientSettings.serverPort}.`
          )});
      }
    }
    else {
      this.setState({
        serverConfig: null,
        errorMessage:
            <Container>
              {this.createErrorBanner(config.statusText, config.statusCode,
                  `Failed to fetch config from ${ this.state.clientSettings.serverPort}. Please choose a valid server.`)}
            </Container>
      });
    }
  }

  updateShowMarker(newShowMarker) {
    this.setState({showMarker: newShowMarker});
  }

}
