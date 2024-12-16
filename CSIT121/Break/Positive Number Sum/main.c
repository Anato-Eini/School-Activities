#include <stdio.h>

int main(){
    int num, sum = 0;
    
    while(1){
        printf("Enter a number: ");
        scanf("%d", &num);
        
        if(num < 0){
            break;
        }
        
        sum = sum + num;
    }
    printf("Sum of positive numbers: %d\n", sum);
    return 0;
}
