#include <stdio.h>

int main(void){
    
    char a, b;
    int AV, AV1;
    
    printf("Enter character 1: ");
    scanf(" %c", &a);
    AV = (int)a;
    
    printf("Enter character 2: ");
    scanf(" %c", &b);
    AV1 = (int)b;
    
    if (AV > AV1){
        printf("The lesser character is: %c", AV1);
    }else{
        printf("The lesser character is: %c", AV);
    }
    return 0;
}
