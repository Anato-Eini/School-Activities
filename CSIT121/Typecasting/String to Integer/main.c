#include <stdio.h>
#include <string.h>

int main(void){
    char a[10];
    
    printf("Enter a string: ");
    scanf(" %s", &a);
    
    int b = atoi(a);
    printf("String converted to integer: %d", b);
    
    return 0;
}
