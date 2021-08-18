#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  bool b {true};
  s.put(b);
  if (s.str()!= "t"s){
      cerr << "Case32 failed" << endl;
  }
  return 0;
}
