#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  char a = '	';
  s=a + s;
  char c;
  try{
    s.get(c);
    if (c!=a){
	cerr << "Case136 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "136 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "136 Error msg not std::string" << '\n';
  }
  return 0;
}
