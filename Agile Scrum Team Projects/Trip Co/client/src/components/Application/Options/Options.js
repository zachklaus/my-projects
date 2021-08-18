import React, {Component} from 'react'
import {Container, Row, Col} from 'reactstrap'
import Pane from '../Pane';
import Units from './Units'
import Optimizations from './Optimizations'
import CustomUnits from "./CustomUnits";

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Distance object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
export default class Options extends Component{
  constructor(props) {
    super(props);
  }

  render() {
    return(
        <Container>
          <Row>
            <Col xs="12">
              {this.heading()}
            </Col>
          </Row>
          <Row>
            <Col  xs="12" sm="6" md={"3"} lg={"4"}>
              <Units options={this.props.options}
                     activeUnit={this.props.options.activeUnit}
                     updateOption={this.props.updateOption}
                     createErrorBanner={this.props.createErrorBanner}/>
            </Col>
            <Col  xs="12" sm="6" md={"6"} lg={"4"}>
             <CustomUnits options={this.props.options}
                          activeUnit={this.props.options.activeUnit}
                          updateOption={this.props.updateOption}
                          createErrorBanner={this.props.createErrorBanner}/>
            </Col>
            <Col  xs="12" sm="6" md={"3"} lg={"4"}>
              <Optimizations options={this.props.options}
                     optimizationLevel={this.props.options.optimizationLevel}
                     updateOption={this.props.updateOption}
                     serverConfig={this.props.config}/>
            </Col>

          </Row>
        </Container>
    )
  }



  heading() {
    return (
        <Pane header={'Plan Options'}
              bodyJSX={'Select ...'}/>
    );
  }
}
