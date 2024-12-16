#include <stdio.h>
int main(void)
{
    int n;
    int result;
    
    printf("Enter an integer: ");
    scanf("%d", &n);
    
    result = n&25;
    printf("%d AND 25 = %d", n, result);
    return 0;
}
