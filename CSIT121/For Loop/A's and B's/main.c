#include <stdio.h>

int main(){
    char string[100];
    int counta = 0;
    int countb = 0;
    
    printf("Enter a string: ");
    fgets(string, sizeof(string), stdin);
    
    for(int i = 0; string[i] != '\0'; i++){
        if(string[i] == 'a'){
            counta++;
        }
        if(string[i] == 'b'){
            countb++;
        }
    }
    printf("Number of 'a' occurrences: %d\n", counta);
    printf("Number of 'b' occurrences: %d", countb);
    return 0;
}
