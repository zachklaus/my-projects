#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put('r');
  if (s.empty()!= false){
      cerr << "Case26 failed" << endl;
  }
  return 0;
}
