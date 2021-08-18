import React, { Component } from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import { Button, ButtonGroup} from 'reactstrap'

export default class Optimizations extends Component {
  constructor(props) {
    super(props)
    this.clickButton = this.clickButton.bind(this);
    this.renderOptimizationButton = this.renderOptimizationButton.bind(this);
    this.renderOptimizationButtons = this.renderOptimizationButtons.bind(this);
  }

  render() {
    return (
      <Card className='text-center'>
        <CardHeader className='bg-csu-gold text-white font-weight-semibold'>Optimizations</CardHeader>
        <CardBody>
          <ButtonGroup vertical className='w100'>
            {this.renderOptimizationButtons()}
          </ButtonGroup>
        </CardBody>
      </Card>
    );
  }

  renderOptimizationButtons() {

    let optimizationLevels = this.props.serverConfig.optimizations;
    let optimizationButtonsList = [];

    for (let optIndex = 0; optIndex < optimizationLevels.length; optIndex++) {
      optimizationButtonsList.push(
        this.renderOptimizationButton(optimizationLevels[optIndex])
      );
    }

    return optimizationButtonsList;

  }

  renderOptimizationButton(optimizationLevel) {

    return (
      <Button
        className='btn-csu w-100 text-left'
        key={"key_button_" + optimizationLevel}
        id={"id_button_" + optimizationLevel}
        active={this.props.optimizationLevel === optimizationLevel}
        value={optimizationLevel}
        onClick={(event) => this.clickButton(event, optimizationLevel)}>
        {optimizationLevel.charAt(0).toUpperCase() + optimizationLevel.slice(1)}</Button>
    );
  }

  clickButton(event) {
    this.props.updateOption('optimizationLevel', event.target.value);
  }
}