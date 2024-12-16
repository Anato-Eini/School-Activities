#include <stdio.h>
     
int main(){
    int num, count = 0;
    float sum = 0.0;
    
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
        count++;
    }
    if(count > 0){
        printf("Average: %.2f\n", sum / count);
    }
    
    return 0;
}
