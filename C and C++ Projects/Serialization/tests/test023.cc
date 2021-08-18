#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put('r');
  char x;
  try{
    s.get(x);
    if (x!='r'){
	cerr << "Case23 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "23 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "23 Error msg not std::string" << '\n';
  }
  return 0;
}
