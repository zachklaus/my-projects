#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  short x {6};
  s.put(x);
  short y;
  try{
    s.get(y);
    if (y!=x){
	cerr << "Case35 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "35 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "35 Error msg not std::string" << '\n';
  }
  return 0;
}
