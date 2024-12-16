#include "stdio.h"
#include "math.h"

int addNumbers(int num1, int num2){
    return num1 + num2;
}


int main(){
    int num1, num2;
    
    printf("Enter first number: ");
    scanf("%d", &num1);
    printf("Enter second number: ");
    scanf("%d", &num2);
    
    printf("Sum of numbers: %d", addNumbers(num1, num2));
    
    return 0;
}
