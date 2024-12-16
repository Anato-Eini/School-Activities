#include "stdio.h"
#include "math.h"

void evaluatePolynomial(double a, double b, double c, double d, double x){
    printf("Value of the polynomial: %.2lf", 
                        (a * pow(x, 3)) + (b * pow(x, 2)) + (c * x) + d);
}

int main(){
    double a, b, c, d, x;
    
    printf("Enter coefficient a: ");
    scanf("%lf", &a);
    printf("Enter coefficient b: ");
    scanf("%lf", &b);
    printf("Enter coefficient c: ");
    scanf("%lf", &c);
    printf("Enter coefficient d: ");
    scanf("%lf", &d);
    printf("Enter value of x: ");
    scanf("%lf", &x);
    
    evaluatePolynomial(a, b, c, d, x);
    
    return 0;
}
