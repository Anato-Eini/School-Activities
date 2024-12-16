#include <stdio.h>
#include <string.h>

int main(){
    char c1[50];
    char c2[50];
    char c3[50];
    char c4[50];
    
    printf("Enter the name of your first character: ");
    fgets(c1, sizeof(c1), stdin);
    printf("Enter the name of your second character: ");
    fgets(c2, sizeof(c2), stdin);
    printf("Enter the name of your third character: ");
    fgets(c3, sizeof(c3), stdin);
    printf("Enter the name of your fourth character: ");
    fgets(c4, sizeof(c4), stdin);
    
    size_t len1 = strlen(c1);
    if(len1 > 0 && c1[len1-1] == '\n'){
        c1[len1-1] = '\0';
    }
    size_t len2 = strlen(c2);
    if(len2 > 0 && c2[len2-1] == '\n'){
        c2[len2-1] = '\0';
    }
    size_t len3 = strlen(c3);
    if(len3 > 0 && c3[len3-1] == '\n'){
        c3[len3-1] = '\0';
    }
    size_t len4 = strlen(c4);
    if(len4 > 0 && c4[len4-1] == '\n'){
        c4[len4-1] = '\0';
    }
    
    printf("My most played characters in the game are %s, %s, %s, and %s.", c1, c2, c3, c4);
    return 0;
}
