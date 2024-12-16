#include <stdio.h>

int main(){
    
    char firstChar, secondChar;
    int size;
    
    printf("Enter First Character: ");
    scanf(" %c", &firstChar);
    printf("Enter Second Character: ");
    scanf(" %c", &secondChar);
    printf("Enter Size: ");
    scanf("%d", &size);
    
    for(int i = 1; i <= size; i++){
        for(int j = 1; j <= i - 1; j++){
            printf("-");
        }
        
        if(i % 2 == 1){
            printf("%c", firstChar);
        }else{
            printf("%c", secondChar);
        }
        
        printf("\n");
    }
    
    
    return 0;
}
