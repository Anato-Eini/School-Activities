#include <stdio.h>

int main(void){
    int x, y;
    int result;
    
    printf("Enter X: ");
    scanf("%d", &x);
    printf("Enter Y: ");
    scanf("%d", &y);
    
    result = x >> y;
    printf("%d >> %d = %d", x, y, result);
    return 0;
}
