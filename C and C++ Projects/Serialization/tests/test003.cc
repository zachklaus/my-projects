#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  if (s.empty() != true){
      cerr <<"Case03 failed" << '\n';
  }
  return 0;
}
