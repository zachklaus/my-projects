#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  const short x {6};
  s.put(x);
  if (s.size()!= 2){
      cerr << "Case13 failed" << endl;
  }
  return 0;
}
