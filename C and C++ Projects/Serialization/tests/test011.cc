#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  const short x {6};
  s.put(x);
  short y;
  try{
    s.get(y);
    if (y!=x){
	cerr << "Case11 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "11 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "11 Error msg not std::string" << '\n';
  }
  return 0;
}
