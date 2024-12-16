#include <stdio.h>
#include <string.h>

int main(void){
    char str1[50], str2[50], str3[50];

    printf("Enter the first string: ");
    fgets(str1, sizeof(str1), stdin);
    printf("Enter the second string: ");
    fgets(str2, sizeof(str2), stdin);
    printf("Enter the third string: ");
    fgets(str3, sizeof(str3), stdin);
    
    size_t len1 = strlen(str1);
    size_t len2 = strlen(str2);
    size_t len3 = strlen(str3);
    
    if(len1 > 0 && str1[len1-1] == '\n'){
        str1[len1-1] = '\0';
    }
    if(len2 > 0 && str2[len2-1] == '\n'){
        str2[len2-1] = '\0';
    }
    if(len2 > 0 && str3[len3-1] == '\n'){
        str3[len3-1] = '\0';
    }
    
    printf("%s - %s - %s", str1, str2, str3);
    return 0;
}
