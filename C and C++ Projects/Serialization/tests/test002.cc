#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  if (s.str() != ""s){
      cerr <<"Case02 failed" << '\n';
  }
  return 0;
}
