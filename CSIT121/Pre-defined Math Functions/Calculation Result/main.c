#include <stdio.h>
#include <math.h>

int main(void){
    double a, b;
    
    printf("Enter the first number: ");
    scanf("%lf", &a);
    printf("Enter the second number: ");
    scanf("%lf", &b);
    
    double result = sqrt(pow(fabs(a - b), 3));
    printf("Result: %.2lf", result);
    
    
    
    return 0;
}
