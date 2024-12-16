#include <stdio.h>
#include <math.h>

_Bool isPerfectSquare(int num){
    long long int result = sqrt(num);
    return result * result == num;
}

_Bool isPerfectCube(int num){
    long long int result = cbrt(num);
    
    return result * result * result == num;
    
}

int main(){
    int integer;
    printf("Enter an integer: ");
    scanf("%d", &integer);
    
    if(isPerfectCube(integer) && isPerfectSquare(integer)){
        printf("Perfect in every way");
    }else if (isPerfectCube(integer)){
        if(integer % 2 == 0)
            printf("Perfect in even cubes");
        else
            printf("Perfect in an odd way");
    }else{
        printf("Nothing special");
    }
    
    
    return 0;
}
