#include <stdio.h>

int main(void)
{
    int x, y, z;
    int sum;
    
    printf("Enter x: ");
    scanf("%d", &x);
    
    printf("Enter y: ");
    scanf("%d", &y);
    
    printf("Enter z: ");
    scanf("%d", &z);
    
    sum = x + y + z;
    printf("Sum: %d", sum);
    return 0;
}
