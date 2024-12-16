#include <stdio.h>
#include <string.h>

int main(void){
    char a[100];
    char b[100];
    char c[100];
    
    printf("Enter the first string: ");
    scanf("%s", &a);
    printf("Enter the second string: ");
    scanf("%s", &b);
    printf("Enter the third string: ");
    scanf("%s", &c);
    
    printf("Length of the first string: %d", strlen(a));
    printf("\nLength of the second string: %d", strlen(b));
    printf("\nLength of the third string: %d", strlen(c));
    
    printf("\nResult: %d", (strlen(a)+strlen(b))/strlen(c));
    return 0;
}
