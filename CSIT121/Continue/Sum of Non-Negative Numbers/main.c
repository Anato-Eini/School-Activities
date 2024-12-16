#include <stdio.h>

int main(){
    int num, sum = 0;
    
    while(1){
        printf("Enter a number: ");
        scanf("%d", &num);
        
        if(num == 0){
            break;
        }
        
        if(num < 0){
            continue;    
        }
        
        sum = sum + num;
        
    }
    printf("Sum: %d\n", sum);
    
    return 0;
}
