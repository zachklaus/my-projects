#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  bool b {true};
  s.put(b);
  if (s.empty()!= false){
      cerr << "Case34 failed" << endl;
  }
  return 0;
}
