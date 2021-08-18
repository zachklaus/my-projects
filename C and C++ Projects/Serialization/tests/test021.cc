#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(9832759025);
  if (s.size()!= 6){
      cerr << "Case21 failed" << endl;
  }
  return 0;
}
