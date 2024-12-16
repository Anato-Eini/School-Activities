#include <stdio.h>

int main(){
    
    int number;
    printf("Enter a number: ");
    scanf("%d", &number);
    
    for(int i = 1; i <= number; i++){
        for(int space = 1; space <= number - i; space++){
            printf(" ");
        }
        for(int j = 1; j <= i; j++){
            printf("%d", i);
        }
        printf("\n");
    }
    
    
    return 0;
}
