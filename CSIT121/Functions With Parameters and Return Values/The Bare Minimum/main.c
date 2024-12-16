#include "stdio.h"

float min(float num1, float num2, float num3){
    return num1 < num2 && num1 < num3 ? num1 : num2 < num1 && num2 < num3 ? num2 : num3;
}


int main(){
    float num1, num2, num3;
    
    printf("Enter first value: ");
    scanf("%f", &num1);
    printf("Enter second value: ");
    scanf("%f", &num2);
    printf("Enter third value: ");
    scanf("%f", &num3);
    
    printf("Minimum Value: %.3f", min(num1, num2, num3));
    
    return 0;
}
