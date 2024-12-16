#include <stdio.h>

int main(){
    
    char character;
    
    printf("Enter character: ");
    scanf("%c", &character);
    
    if(!((character >= 'a' && character <= 'z' ||
         character >= 'A' && character <= 'Z' ||
         character >= '0' && character <= '9'))){
        printf("Character is a special character.");
    } 

    return 0;
}
