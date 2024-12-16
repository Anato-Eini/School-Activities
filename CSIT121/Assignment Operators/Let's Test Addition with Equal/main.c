#include <stdio.h>

int main(void)
{
    int a;
    
    printf("Enter an integer number: ");
    scanf("%d", &a);
    
    a += 1;
    printf("The updated number is: %d", a);
    return 0;
    
}
