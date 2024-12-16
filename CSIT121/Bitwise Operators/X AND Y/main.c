#include <stdio.h>

int main(void)
{
    int x, y;
    int result;
    printf("Enter the first integer: ");
    scanf("%d", &x);
    printf("Enter the second integer: ");
    scanf("%d", &y);
    
    result = x&y;
    printf("%d AND %d = %d", x , y, result);
    return 0;
}
