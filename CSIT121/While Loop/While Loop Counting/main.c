#include <stdio.h>

int main(){
    int n;
    
    printf("Enter a number: "); //ask an integer
    scanf("%d", &n);
    
    int i = 1; // starting point loop
    
    while(i <= n){  // maggukod ang starting point sa number
        printf("%d\n", i); //mo print first siya sa so 1
        i++; // kay diri ang increment, +1, ang int i = 1,
        // mahimong int i = 2,
        // dili lang pud sa condition mabag o 
        // apil pud ng naa sa inside sa 
        // sa printf, so print napud siyag 2.
    }
    
    return 0;
    
}
