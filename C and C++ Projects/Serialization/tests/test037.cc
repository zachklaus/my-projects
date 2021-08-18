#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  short x {6};
  s.put(x);
  if (s.size()!= 2){
      cerr << "Case37 failed" << endl;
  }
  return 0;
}
