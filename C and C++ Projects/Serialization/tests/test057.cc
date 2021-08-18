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

  bool caught {false};
  try {
  bool bb;
  s.get(bb);
  } catch (string st) {
    if (!st.empty()) caught = true;
  }
  if (!caught){
      cerr << "Case57 failed" << endl;
  }
  int ii;
  try{
    s.get(ii);
    if (ii!=39534){
	cerr << "Case57 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "57 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "57 Error msg not std::string" << '\n';
  }
  return 0;
}
