#include <stdio.h>

int main(void)
{
    int x,y;
    
    printf("Enter the first number: ");
    scanf("%d", &x);
    printf("Enter the second number: ");
    scanf("%d", &y);
    
    printf("Before swap: num1 = %d, num2 = %d\n", x, y);
    x = x^y;
    y = x^y;
    x = x^y;
    printf("After swap: num1 = %d, num2 = %d", x, y);
    
    return 0;
}
