#include <stdio.h>
#include <math.h>

int main(void){
    int a, b;
    
    printf("Enter the first number: ");
    scanf("%d", &a);
    printf("Enter the second number: ");
    scanf("%d", &b);
    
    int result = fmax(a, b);
    printf("The largest number is: %d", result);
    
    return 0;
}
