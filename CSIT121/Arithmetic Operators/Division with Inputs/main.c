#include <stdio.h>

int main(void){
    int x, y;
    int quotient;
    
    printf("Enter x: ");
    scanf("%d", &x);
    printf("Enter y: ");
    scanf("%d", &y);
    
    quotient = x / y;
    printf("Quotient: %d", quotient);
    return 0;
}
