#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void){
    char str[50];
    
    printf("Enter a string containing a float: ");
    scanf("%s", str);
    
    float f1 = atof(str);
    
    printf("Floating-point number: %.2f", f1);
    return 0;
}
