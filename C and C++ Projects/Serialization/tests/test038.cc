#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  short x {6};
  s.put(x);
  if (s.empty()!= false){
      cerr << "Case38 failed" << endl;
  }
  return 0;
}
