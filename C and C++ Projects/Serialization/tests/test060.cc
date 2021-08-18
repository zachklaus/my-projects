#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  string serialized {"\x53\x10\x5f\x59\x6f\x75\x20\x6d\x61\x79\x20\x61\x73\x73\x75\x6d\x65\x20\x74\x68\x61\x74\x20\x61\x20\x73\x74\x72\x69\x6e\x67\x20\x63\x6f\x6e\x73\x74\x61\x6e\x74\x20\x77\x69\x6c\x6c\x20\x6e\x6f\x74\x20\x63"s};
  s.str(serialized);

  bool caught {false};
  try {
    string ss;
    s.get(ss);
  } catch (string st) {
    if (!st.empty()) caught = true;
  }
  if (caught != true){
      cerr << "Case60 failed" << endl;
  }
  return 0;
}
