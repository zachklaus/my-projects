#include <stdlib.h>
#include <ctype.h>
#include <stdio.h>

char int2char (int radix, int value) {
    
    char charArray[36] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H',
	'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    
	if(value > radix - 1) {
		return '?';
	}
	else {
		return charArray[value];
	}
    
}

int char2int (int radix, char digit) {

	char charArray[36] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H',
	'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	int index = -1;
	
	for(int i = 0; i < 36; i++) {
		if(toupper(digit) == charArray[i]) {
				index = i;
		}
	}
	if (index > radix - 1)
		return -1;
	else
		return index;
	
}	
	
void divRem (int numerator, int divisor, int* quotient, int* remainder) {

	int timesSubtracted = 0;
	int whatsLeft = 0;
	
	while(numerator >= divisor) {
		numerator = numerator - divisor;
		timesSubtracted++;
	}
	whatsLeft = numerator;
	*quotient = timesSubtracted;
	*remainder = whatsLeft;
}

int ascii2int (int radix, int valueOfPrefix) {
	
	char currentChar = getchar();
	
	if (currentChar == '\n') {
		return valueOfPrefix;
	}
	else {
		int charValue = char2int(radix,currentChar);
		valueOfPrefix = radix * valueOfPrefix + charValue;
		return ascii2int(radix, valueOfPrefix);
	}
	
}

void int2ascii (int radix, int value) {
	
	
	int quotient = 0;
	int remainder = 0;
	
	divRem(value, radix, &quotient, &remainder);
	//printf("%d\n", remainder);
	
	if (remainder == 0) {
		return;
	}
	else {
		char c = int2char(radix, remainder);
		int2ascii(radix, quotient);
		putchar(c);
	}
	
}

double frac2double (int radix) {
  return radix;
}

