#include "stdio.h"

void sumOfIntegers(int a, int b, int c){
    printf("%d", a + b + c);
}

int main(){
    int num1, num2, num3;
    
    printf("Enter first integer: ");
    scanf("%d", &num1);
    printf("Enter second integer: ");
    scanf("%d", &num2);
    printf("Enter third integer: ");
    scanf("%d", &num3);
    
    sumOfIntegers(num1, num2, num3);
    return 0;
}
