import React, {Component} from 'react';
import {Input, Label, Form, FormGroup} from 'reactstrap';


export default class CustomLocationForm extends Component {
  constructor(props) {
    super(props);
    this.updateField = this.props.updateLocation;
    this.createForm = this.createForm.bind(this);
  }

  render() {
    return (
      <Form inline>
        {this.props.attributes.map((attr) => this.createForm(attr))}
      </Form>
    );
  }

  createForm(name) {
    return (
      <FormGroup key={'formGroup' + name}>
        <Label key={'label' + name}>{this.capitalize(name)}: </Label>
        <Input type={'text'} id={name} key={'input' + name} placeholder={this.capitalize(name)} onChange={(e) => this.updateField(name, e.target.value)}/>
      </FormGroup>
    );
  }

  capitalize(s){
    return s.charAt(0).toUpperCase() + s.slice(1);
  }
}