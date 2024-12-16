#include <stdio.h>
#include <string.h>


int main(){
    
    char password[100];
    int attempts = 3;
    char correct_password[] = "secret";
    
    for(int i = attempts; i > 0; i--){
        printf("Enter the password: ");
        scanf("%s", password);
        
        if(strcmp(password, correct_password) == 0){
            printf("Access granted!\n");
            break;
        }else{
            if(i - 1 > 0){
                printf("Access denied! %d attempts remaining.\n", i - 1);
            }else{
                printf("Access denied! 0 attempts remaining.\n");
                printf("Access denied!\n");
            }
        }
    }
    
    return 0;
}
