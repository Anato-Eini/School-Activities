#include <stdio.h>
#include <string.h>

int main(void){
    char a, b;
    
    printf("Enter first character: ");
    scanf(" %c", &a);
    int AV = (int)a;
    
    printf("Enter second character: ");
    scanf(" %c", &b);
    int AV1 = (int)b;
    
    int sum = AV + AV1;
    printf("Sum: %d", sum);
    
    return 0;
    
}
