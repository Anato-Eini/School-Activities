#include <stdio.h>

int main(){
    int num, i;
    
    printf("Enter a number: ");
    scanf("%d", &num);
    
    printf("Prime factors of %d (excluding multiples of 3): ", num);
    
    
    for(int i = 2; i <= num; i++){
        
        if(num % i != 0){
            continue;
        }
        
        if(i % 3 == 0){
            continue;
        }
        
        printf("%d ", i);
        while(num % i == 0){
            num /= i;
        }
    }
    printf("\n");
    return 0;
}
