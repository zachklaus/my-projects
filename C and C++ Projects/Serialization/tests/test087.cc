#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s+=-45678;
  int i;
  try{
    s.get(i);
    if (i!=-45678){
	cerr << "Case87 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "87 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "87 Error msg not std::string" << '\n';
  }
  return 0;
}
