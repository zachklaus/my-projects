#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  long l {9832759025L};
  s.put(l);
  long x;
  try{
    s.get(x);
    if (x != 9832759025L){
	cerr << "Case43 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "43 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "43 Error msg not std::string" << '\n';
  }
  return 0;
}
