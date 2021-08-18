#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  const Serial s;
  if (s.size() != 0){
      cerr <<"Case04 failed" << '\n';
  }
  return 0;
}
