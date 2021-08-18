import React, {Component} from 'react';
import {Card, CardHeader, CardBody, FormGroup, Button, Input} from 'reactstrap';
import Cookies from 'universal-cookie';

export default class CustomUnits extends Component{
  constructor(props) {
    super(props);

    this.state = {
      units: this.props.options.units
    };

    this.cookie = new Cookies();
    this.customUnitCookieName = 'customUnits';
    this.customUnitName = '';
    this.customUnitRadius = '';
    this.onAddButtonClick = this.onAddButtonClick.bind(this);
    this.onRemoveButtonClick = this.onRemoveButtonClick.bind(this);
  }


  render(){
    return (<Card className='text-center'>
      <CardHeader className='bg-csu-gold text-white font-weight-semibold'>Custom Units</CardHeader>
      <CardBody>
        {this.renderUserDefinedForm()}
        <Button
            id='customAddButton'
            onClick={() =>{this.onAddButtonClick()}}
        >
          {'Add'}
        </Button>
        <hr/>
        <Button
            id='customRemoveButton'
            onClick={() =>{this.onRemoveButtonClick()}}
        >
          {'Remove Selected'}
        </Button>
      </CardBody>
    </Card>);
  }

  renderUserDefinedForm() {
    return (
        <FormGroup>
          <Input type="text" id='unitName' placeholder='Enter unit name' onChange={(event)=>{this.customUnitName = event.target.value}}/>
          <Input type="text" id='unitRadius' placeholder='Enter unit radius' onChange={(event)=>{this.customUnitRadius = event.target.value}}/>
        </FormGroup>
    )
  }

  /*
  * set the state (for testing) and save the cookie
  * */
  setUnitsState(newUnits){
    this.setState({
      units: newUnits
    });
   //can't set cookie to not expire. Set date to forever away.
    let date = new Date('December 25, 2999 00:00:00');
    this.cookie.set(this.customUnitCookieName, newUnits, {expires: date});
  }

  /*
  * add new unit to list of units
  * */
  onAddButtonClick(){
    let units = this.props.options.units;
    let radius = parseFloat(this.customUnitRadius);
    let name = this.customUnitName;
    if(this.validateCustomUnits(radius)) {
      units[name]=radius;
      this.props.updateOption('units',units);
    }
    this.setUnitsState(units);
  }

  onRemoveButtonClick(){
    let name = this.props.options.activeUnit;
    //Do not allow user to remove default units
    if(name === 'miles' || name ===  'kilometers'
        || name === 'nautical miles'){
      return;
    }

    let units = this.props.options.units;
    //set active to miles after removing custom unit
    this.props.options.activeUnit = 'miles';
    this.props.updateOption('activeUnit', 'miles');
    delete units[name];

    this.props.updateOption('units',units);
    this.setUnitsState(units);
  }

  /*
  * Make sure radius is an int or float
  * */
  validateCustomUnits(radius){
    let valid = true;
    let radiusInt = parseInt(radius);
    let radiusFloat = parseFloat(radius);
    if(isNaN(radiusInt) || isNaN(radiusFloat)){
      valid =false;
    }
    if(!valid){
      this.setState({
        errorMessage:
            this.props.createErrorBanner(
                'Invalid Custom Unit',
                '400',
                'Unit Name Must Alphabet Letters Only and Unit Radius must be a number'
            )
      });
    }
    return valid;
  }
}