#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  string a = "Testing serial case2"s;
  s+=a;
  Serial s2;
  //s2 = s + s2;
  //string st;
  try{
    //s2.get(st);
    if ( s == s2){
	cerr << "Case151 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "151 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "151 Error msg not std::string" << '\n';
  }
  return 0;
}
