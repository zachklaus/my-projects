#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;

  bool caught {false};
  try {
  long l;
  s.get(l);
  } catch (string st) {
    if (!st.empty()) caught = true;
  }
  if (!caught){
      cerr << "Case73 failed" << endl;
  }

  return 0;
}
