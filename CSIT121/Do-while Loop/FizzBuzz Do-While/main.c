#include <stdio.h>

int main(){
    
    int num;
    
  
    do{
        
        printf("Enter a number: ");
        scanf("%d", &num);
        
        if(num % 3 == 0 && num % 5 == 0){
            printf("FizzBuzz\n");
        }else if(num % 3 == 0){
            printf("Fizz\n");
        }else if(num % 5 == 0){
            printf("Buzz\n");
        }else{
            printf("%d\n", num);
        }
        
    }while(num !=0);
    
    
    return 0;
}
