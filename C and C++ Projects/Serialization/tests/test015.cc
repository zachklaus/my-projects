#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(39534);
  int x;
  try{
    s.get(x);
    if (x!=39534){
	cerr << "Case15 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "15 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "15 Error msg not std::string" << '\n';
  }
  return 0;
}
