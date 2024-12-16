#include <stdio.h>

int main(){
    
    int limit;
    
    printf("Enter a number: ");
    scanf("%d", &limit);
    
    for(int i = 0; i <= limit; i++){
        if( i % 2 != 0){
            continue;
        }
        
        if( i == 10){
            continue;
        }
        printf("%d ", i);
    }
    
    return 0;
}
