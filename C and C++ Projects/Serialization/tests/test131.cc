#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const short a = -4;
  s=a + s;
  short i;
  try{
    s.get(i);
    if (i!=-4){
	cerr << "Case131 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "131 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "131 Error msg not std::string" << '\n';
  }
  return 0;
}
