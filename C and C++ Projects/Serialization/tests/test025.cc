#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put('r');
  if (s.size()!= 2){
      cerr << "Case25 failed" << endl;
  }
  return 0;
}
