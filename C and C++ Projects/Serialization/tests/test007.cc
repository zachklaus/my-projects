#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(true);
  bool x;
  try{
    s.get(x);
    if (x!=true){
	cerr << "Case07 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "07 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "07 Error msg not std::string" << '\n';
  }
  return 0;
}
