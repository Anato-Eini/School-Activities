#include <stdio.h>

int main(void)
{
    int x, y, z;
    
    printf("Enter x: ");
    scanf("%d", &x);
    printf("Enter y: ");
    scanf("%d", &y);
    printf("Enter z: ");
    scanf("%d", &z);
    
    y += z;
    x += y;
    
    printf("Result: %d", x);
    return 0;
}
