#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  int i {39534};
  s.put(i);
  if (s.empty()!= false){
      cerr << "Case42 failed" << endl;
  }
  return 0;
}
