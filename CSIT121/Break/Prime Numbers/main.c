#include <stdio.h>

int main(){
    int positiveint, i;
    
    printf("Enter a positive integer: ");
    scanf("%d", &positiveint);
    
    if(positiveint <= 1){
        printf("%d is not a prime number\n", positiveint);
        return 0;
    }  
    
    int isPrime = 1;
    
    for(i = 2; i <= sqrt(positiveint); i++){
        if(positiveint % i == 0){
            isPrime = 0;
            break;
        }
    }
    
    if(isPrime){
        printf("%d is a prime number\n", positiveint);
    }else{
        printf("%d is not a prime number\n", positiveint);
    }
    
    return 0;
}
