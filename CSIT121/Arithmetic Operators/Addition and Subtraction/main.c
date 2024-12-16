#include <stdio.h>

int main(void){
    int x, y;
    int sum;
    int subtract;
    
    printf("Enter x: ");
    scanf("%d", &x);
    
    printf("Enter y: ");
    scanf("%d", &y);
    
    sum = x+y;
    printf("%d + %d = %d\n", x, y, sum);
    
    subtract = x-y;
    printf("%d - %d = %d", x, y, subtract);
    
    return 0;
}
