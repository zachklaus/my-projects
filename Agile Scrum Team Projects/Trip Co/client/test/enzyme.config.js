import Enzyme from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'

// This just configures Enzyme with tools to parse React components.
Enzyme.configure({ adapter: new Adapter() });


// Allow us to use Alerts without killing Enzyme/Jest JSDOM
window.alert = () => true;


// Help with the PolyLine killer
var createElementNSOrig = global.document.createElementNS
global.document.createElementNS = function(namespaceURI, qualifiedName) {
  if (namespaceURI==='http://www.w3.org/2000/svg' && qualifiedName==='svg'){
    var element = createElementNSOrig.apply(this,arguments)
    element.createSVGRect = function(){};
    return element;
  }
  return createElementNSOrig.apply(this,arguments)
}