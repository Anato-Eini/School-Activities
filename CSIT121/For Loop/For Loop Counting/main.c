#include <stdio.h>

int main(){
    int n;
    
    printf("Enter a positive integer: ");
    scanf("%d", &n);
    
    for(int i = 1; i <= n; i++){ // start ang n sa 1, then mo stop hantud
                                 // sa gi input na integer
        printf("%d\n", i);
    }
    
    return 0;
}
