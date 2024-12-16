#include <stdio.h>
#include <string.h>

int main(void){
    char a[100], b[100], c[100], d[100], e[100];
    
    printf("Enter the first string: ");
    scanf("%s", &a); 
    printf("Enter the second string: ");
    scanf("%s", &b);
    printf("Enter the third string: ");
    scanf("%s", &c);
    printf("Enter the fourth string: ");
    scanf("%s", &d);
    printf("Enter the fifth string: ");
    scanf("%s", &e);
    
    printf("Length of the first string: %d", strlen(a));
    printf("\nLength of the second string: %d", strlen(b));
    printf("\nLength of the third string: %d", strlen(c));
    printf("\nLength of the fourth string: %d", strlen(d));
    printf("\nLength of the fifth string: %d", strlen(e));
    
    int result1 = strlen(a) + strlen(b);
    int result2 = strlen(c) - strlen(d);
    
    printf("\nResult: %d", result1/result2);
    
    return 0;
    
}
