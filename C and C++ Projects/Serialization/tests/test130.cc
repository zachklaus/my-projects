#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  short a = 4;
  s=a + s;
  short i;
  try{
    s.get(i);
    if (i!=4){
	cerr << "Case130 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "130 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "130 Error msg not std::string" << '\n';
  }
  return 0;
}
