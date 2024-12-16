#include <stdio.h>
#include <ctype.h>

int main(void){
    char a, b, c;
    printf("Enter the first character: ");
    scanf(" %c", &a);
    printf("Enter the second character: ");
    scanf(" %c", &b);
    printf("Enter the third character: ");
    scanf(" %c", &c);
    
    int result = isalpha(a) - isdigit(b) - isalnum(c);
    
    printf("%d", result);
    
    return 0;
}
