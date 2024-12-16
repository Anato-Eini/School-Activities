#include <stdio.h> 
#include <math.h>

int main(void){
    double a;
    
    printf("Enter a negative number: ");
    scanf("%lf", &a);
    
    printf("Absolute value: %.0lf", fabs(a));
    return 0;
}
