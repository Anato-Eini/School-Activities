#include "stdio.h"
#include "math.h"

double calculateLinearEquation(double x, double slope, double yIntercept){
    return x * slope + yIntercept;
}

int main(){
    double x, slope, yIntercept;
    
    printf("Enter x: ");
    scanf("%lf", &x);
    printf("Enter slope: ");
    scanf("%lf", &slope);
    printf("Enter y intercept: ");
    scanf("%lf", &yIntercept);
    
    printf("Result: %.2lf", calculateLinearEquation(x, slope, yIntercept));
    
    return 0;
}
