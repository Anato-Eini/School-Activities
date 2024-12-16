#include <stdio.h>

int main(){
    
    char str1[50];
    char game = str1[0];
    
    printf("Enter the difficulty level (Easy, Medium, Hard): ");
    scanf("%c", &game);
    
    switch(game){
        case 'E':
            printf("Message: You selected Easy difficulty. Enjoy the game!");
            break;
        case 'M':
            printf("Message: You selected Medium difficulty. Get ready for a challenge!");
            break;
        case 'H':
            printf("Message: You selected Hard difficulty. Brace yourself for a tough gameplay!");
            break;
        default:
            printf("Invalid difficulty level.");
            break;
    }
    
    return 0;
}
