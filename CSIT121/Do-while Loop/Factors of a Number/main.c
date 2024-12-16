#include <stdio.h>

int main(){
    int number;
    
    printf("Enter an integer: ");
    scanf("%d", &number);
    
    printf("Factors of %d: ", number);
    
    int i = 1;
    do{
        if(number % i == 0){
            printf("%d ", i);
        }    
        
        i++;
    }while( i <= number);
    
    return 0;
}
