import React, { Component } from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import { Col, Button, ButtonGroup} from 'reactstrap'

export default class Units extends Component {
  constructor(props) {
      super(props);
      this.state = {errorMessage: null};
      this.clickButton = this.clickButton.bind(this);
  }

  render() {
    return(
      <Card className='text-center'>
          <CardHeader className='bg-csu-gold text-white font-weight-semibold'>Units</CardHeader>
          <CardBody>
              <Col>
              <ButtonGroup vertical className='w100'>
                {this.renderUnitButtons(Object.keys(this.props.options.units))}
              </ButtonGroup>
              </Col>
              <Col>
                {this.state.ifDisplayUserInputFields && (
                  <form>{this.renderUserDefinedForm()}</form>)}<br/>
              </Col>
          </CardBody>
      </Card>
    );
  }

  renderUnitButtons(names) {
    return names.sort().map((unit) =>
      <Button
        className='btn-csu w-100 text-left'
        key={"button_"+unit}
        active={this.props.activeUnit === unit}
        value={unit}
        onClick={(event) => this.clickButton(event, unit)}
      >
        {unit.charAt(0).toUpperCase() + unit.slice(1)}
      </Button>
    );
  }

    clickButton(event) {
      this.props.updateOption('activeUnit', event.target.value);
    }
}
