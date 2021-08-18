#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(9832759025L);
  long x;
  try{
    s.get(x);
    if (x!= 9832759025L){
	cerr << "Case19 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "19 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "19 Error msg not std::string" << '\n';
  }
  return 0;
}
