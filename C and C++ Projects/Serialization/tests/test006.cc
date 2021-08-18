#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  const Serial s;
  if (s.empty() != true){
      cerr <<"Case06 failed" << '\n';
  }
  return 0;
}
