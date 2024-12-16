#include <stdio.h>

int main(){
    int n;
    
    printf("Enter a number: ");
    scanf("%d", &n);
    
    int i = 1;
    do{ 
        printf("%d\n", i*3);
        i++;
    }while(i <= n);
    
    return 0;
}
