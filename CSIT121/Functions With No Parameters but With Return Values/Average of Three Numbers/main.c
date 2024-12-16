#include "stdio.h"

float calculateAverage(){
    float a, b, c;
    printf("Enter the first number: ");
    scanf("%f", &a);
    printf("Enter the second number: ");
    scanf("%f", &b);
    printf("Enter the third number: ");
    scanf("%f", &c);
    
    return (a + b + c) / 3;
}

int main(){
    printf("The average of the three numbers is: %.2f", calculateAverage());
    return 0;
}
