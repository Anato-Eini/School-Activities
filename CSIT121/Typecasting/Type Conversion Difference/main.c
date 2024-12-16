#include <stdio.h>

int main(void){
    
    int num;
    float num1;
    
    printf("Enter an integer: ");
    scanf("%d", &num);
    
    printf("Enter a float: ");
    scanf("%f", &num1);
    
    float f1 = (float) num;
    printf("The difference is: %.2f", f1 - num1);
    return 0;
}
