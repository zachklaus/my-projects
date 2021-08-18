#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(9832759025);
  if (s.empty()!= false){
      cerr << "Case22 failed" << endl;
  }
  return 0;
}
