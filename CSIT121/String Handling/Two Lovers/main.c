#include <stdio.h>
#include <string.h>

int main(){
    char lover1[20];
    char lover2[20];
    
    printf("Enter lover1: ");
    fgets(lover1, sizeof(lover1), stdin);
    printf("Enter lover2: ");
    fgets(lover2, sizeof(lover2), stdin);
    
    size_t len1 = strlen(lover1);
    if(len1 > 0 && lover1[len1-1] == '\n'){
        lover1[len1-1] = '\0';
    }
    
    size_t len2 = strlen(lover2);
    if(len2 > 0 && lover2[len2-1] == '\n'){
        lover2[len2-1] = '\0';
    }

    printf("%s and %s are in love.", lover1, lover2);
   
    return 0;
}
