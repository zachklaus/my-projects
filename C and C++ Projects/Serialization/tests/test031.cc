#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  bool b {true};
  s.put(b);
  bool x;
  try{
    s.get(x);
    if (x!=true){
	cerr << "Case31 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "31 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "31 Error msg not std::string" << '\n';
  }
  return 0;
}
