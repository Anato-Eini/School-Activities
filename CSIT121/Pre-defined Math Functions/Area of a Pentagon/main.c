#include <stdio.h>
#include <math.h>

int main(void){
    
    double a, b;
    printf("Enter side: ");
    scanf("%lf", &a);
    
    b =(1.0/4.0) * sqrt(5 * (5 + 2 * sqrt(5))) * pow(a, 2);
    
    printf("Area of Pentagon: %.2lf", b);
    
    return 0;
}
