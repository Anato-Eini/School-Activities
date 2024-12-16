#include <stdio.h>
#include <ctype.h>

int main(void){
    char c;
    
    printf("Enter a character: ");
    scanf("%c", &c);
    printf("%d\n", isalpha(c));
    return 0;
}
