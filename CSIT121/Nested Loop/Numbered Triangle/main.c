#include <stdio.h>

int main(){
    int integer;
    
    printf("Enter an integer: ");
    scanf("%d", &integer);
    
    for(int i = 1; i <= integer; i++){
        for(int j = 1; j <= i; j++){
            printf("%d ", j);
        }
        printf("\n");
    }
    
    return 0;
}
