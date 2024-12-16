#include <stdio.h>
#include <math.h>

int main(void){
    double a, b;
    
    printf("Enter the base: ");
    scanf("%lf", &a);
    
    printf("Enter the exponent: ");
    scanf("%lf", &b);
    
    double result = pow(a, b);
    printf("Result: %.2lf", result);
    return 0;
}
