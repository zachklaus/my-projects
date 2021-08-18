#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s+="Testing serial case1"s;
  Serial s2;
  s2 = s + s2;
  //string st;
  try{
    //s2.get(st);
    if (s != s2){
	cerr << "Case150 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "150 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "150 Error msg not std::string" << '\n';
  }
  return 0;
}
