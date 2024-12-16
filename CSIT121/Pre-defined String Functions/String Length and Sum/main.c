#include <stdio.h>
#include <string.h>

int main(void){
    
    char a[100];
    char b[100];
 
    
    printf("Enter the first string: ");
    scanf(" %s", &a);
    printf("Enter the second string: ");
    scanf(" %s", &b);
    
    printf("Length of the first string: %d", strlen(a));
    printf("\nLength of the second string: %d", strlen(b));
    
    printf("\nSum: %d", strlen(a) + strlen(b));
    
    return 0;
    
    
}
