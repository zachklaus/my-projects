#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  short a = 4;
  s=s+a;
  short i;
  try{
    s.get(i);
    if (i!=4){
	cerr << "Case109 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "109 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "109 Error msg not std::string" << '\n';
  }
  return 0;
}
