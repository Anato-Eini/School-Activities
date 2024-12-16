#include <stdio.h>
#include <string.h>

int main(void){
    char cat[50];
    char source[] = "Hello";
    
    printf("Enter a string: ");
    scanf("%s", cat);
    
    strcat(cat, source);
    printf("Concatenated string: %s", cat);
}
