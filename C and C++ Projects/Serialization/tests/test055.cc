#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  int i {39534};
  bool b {true};
  s.put(i);
  s.put(b);
  int ii; bool bb;
  try{
    s.get(ii);
    if (ii!=39534){
	cerr << "Case55 failed" << '\n';
    }
    s.get(bb);
    if (bb!=true){
	cerr << "Case55 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "55 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "55 Error msg not std::string" << '\n';
  }
  return 0;
}
