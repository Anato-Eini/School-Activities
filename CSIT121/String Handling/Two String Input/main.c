#include <stdio.h>
#include <string.h>

int main(){
    
    char str1[100];
    char str2[100];
    
    printf("Enter the first string: ");
    fgets(str1, sizeof(str1), stdin);
    printf("Enter the second string: ");
    fgets(str2, sizeof(str2), stdin);
    
    size_t len = strlen(str1);
    if(len > 0 && str1[len-1] == '\n'){
        str1[len-1] = '\0';
    }
    
    size_t len1 = strlen(str2);
    if(len1 > 0 && str2[len1-1] == '\n'){
        str2[len1-1] = '\0';
    }
    
    printf("%s %s %s", str1, str2, str1);
    
    return 0;
}
