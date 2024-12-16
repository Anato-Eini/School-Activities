#include <stdio.h>

int main(){
    
    int num;
    
    do{
        
        printf("Enter an integer: ");
        scanf("%d", &num);
        
        if(num > 0){
            printf("positive\n");
        }else if(num < 0){
            printf("negative\n");
        }
    }while(num != 0);
    return 0;
}
