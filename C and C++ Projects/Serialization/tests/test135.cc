#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s='$' + s;
  char c;
  try{
    s.get(c);
    if (c!='$'){
	cerr << "Case135 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "135 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "135 Error msg not std::string" << '\n';
  }
  return 0;
}
