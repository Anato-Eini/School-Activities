#include <stdio.h>
#include <ctype.h>

int main(void){
    
    char a;
    
    printf("Enter a character: ");
    scanf(" %c", &a);
    printf("Is Alpha: %d", isalpha(a));
    printf("\nIs Digit: %d", isdigit(a));
    printf("\nIs AlNum: %d", isalnum(a));
    printf("\nIs Punct: %d", ispunct(a));
    printf("\nIs XDigit: %d", isxdigit(a));
    printf("\nIs Print: %d", isprint(a));
    printf("\nTo Upper: %c", toupper(a));
    printf("\nIs Upper: %d", isupper(a));
    printf("\nTo Lower: %c", tolower(a));
    printf("\nIs Lower: %d", islower(a));
    
    return 0;
    
}
