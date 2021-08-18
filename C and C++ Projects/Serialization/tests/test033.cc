#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  bool b {true};
  s.put(b);
  if (s.size()!= 1){
      cerr << "Case33 failed" << endl;
  }
  return 0;
}
