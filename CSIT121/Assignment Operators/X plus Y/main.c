#include <stdio.h>

int main(void){
    
    int x;
    int z;
    
    printf("Enter x: ");
    scanf("%d", &x);
    
    printf("Enter y: ");
    scanf("%d", &z);
    
    x += z;
    printf("Result: %d", x);
    return 0;
    
}
