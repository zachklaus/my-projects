#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  int i {39534};
  bool b {true};
  s.put(i);
  s.put(b);

  bool caught {false};
  try {
  bool bb;
  s.get(bb);
  } catch (string st) {
    if (!st.empty()) caught = true;
  }
  if (!caught){
      cerr << "Case56 failed" << endl;
  }
  return 0;
}
