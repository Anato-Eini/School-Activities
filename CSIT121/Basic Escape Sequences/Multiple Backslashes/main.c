#include <stdio.h>

void multipleback(char lash){

    int number = 3;
    printf("These are backslashes: ");
    for(int i = 0; i < number; i++){
        printf("%c", lash);
    }
}

int main(){
    char lash = '\\';
    multipleback(lash);
    return 0;
}