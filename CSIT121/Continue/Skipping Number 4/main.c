#include <stdio.h>

int main(){
    
    int n;
    
    printf("Enter a number: ");
    scanf("%d", &n);
    
    for(int i = 1; i <= n; i++){
        if(i == 4){
            continue;
        }
        printf("%d ", i);
    }
    printf("\n");
    return 0;
}
