#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  int i {39534};
  s.put(i);
  int x;
  try{
    s.get(x);
    if (x!=39534){
	cerr << "Case39 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "39 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "39 Error msg not std::string" << '\n';
  }
  return 0;
}
