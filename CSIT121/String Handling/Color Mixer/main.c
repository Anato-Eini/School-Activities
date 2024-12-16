#include <stdio.h>
#include <stdio.h>

int main(void)
{
    char color1[50];
    char color2[50];
    char color3[50];
    
    printf("Enter color 1: ");
    fgets(color1, sizeof(color1), stdin);
    printf("Enter color 2: ");
    fgets(color2, sizeof(color2), stdin);
    printf("Enter color 3: ");
    fgets(color3, sizeof(color3), stdin);
    
    size_t len1 = strlen(color1);
    size_t len2 = strlen(color2);
    size_t len3 = strlen(color3);
    
    if(len1 > 0 && color1[len1-1] == '\n'){
        color1[len1-1] = '\0';
    }
    if(len2 > 0 && color2[len2-1] == '\n'){
        color2[len2-1] = '\0';
    }
    if(len3 > 0 && color3[len3-1] == '\n'){
        color3[len3-1] = '\0';
    }
    
    printf("Mixing %s, %s, and %s creates a new color!", color1, color2, color3);
    
    return 0;
}
