#include <stdio.h>

int main(){

    char a;

    printf("Enter a character: ");
    scanf("%c", &a);

    if(a == 'a' || a == 'e' || a == 'i' ||a == 'o' ||a == 'u' ||
            a == 'A' || a == 'E' ||a == 'I' ||a == 'O' ||a == 'U'){
        printf("The character is a vowel.");
    } else{
        printf("The character is a consonant.");
    }

    return 0;
}
