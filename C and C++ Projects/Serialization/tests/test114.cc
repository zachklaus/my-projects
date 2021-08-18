#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s=s+'$';
  char c;
  try{
    s.get(c);
    if (c!='$'){
	cerr << "Case114 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "114 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "114 Error msg not std::string" << '\n';
  }
  return 0;
}
