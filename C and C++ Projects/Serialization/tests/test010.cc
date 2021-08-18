#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(true);
  if (s.empty()!= false){
      cerr << "Case10 failed" << endl;
  }
  return 0;
}
