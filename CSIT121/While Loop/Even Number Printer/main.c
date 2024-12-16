#include <stdio.h>

int main(){
    
    int n;
    
    printf("Enter a positive integer: "); // ask p integer
    scanf("%d", &n);
    
    int i = 1; // Start at 1
    
    while (i <= n) {
        if (i % 2 == 0) { // check i
            printf("%d\n", i); // print
        }
        i++; // nya ma 2 napud
    // ahh samokaaaa!!
    }
    return 0;
}
