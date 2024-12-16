#include <stdio.h>

int main(){
    
    int number, i = 1, j;
    printf("Enter an integer: ");
    scanf("%d", &number);
    
    printf("\nMultiplication Table\n");
    
    int table[number][number];
    
    i = 1;
    while( i <= number ){
        j = 1;
        while( j <= number ){
            table[i - 1][j - 1] = i * j;
            j++;
        }
        i++;
    }
    
    i = 0;
    while(i < number){
        j = 0;
        while ( j < number){
            printf("%d\t", table[i][j]);
            j++;
        }
        printf("\n");
        i++;
    }
    
    
    return 0;
}
