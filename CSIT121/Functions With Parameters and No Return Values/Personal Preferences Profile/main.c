#include "stdio.h"

void displayFavorites(char * color, char *animal, int number){
    printf("Favorite Color: %s\n", color);
    printf("Favorite Animal: %s\n", animal);
    printf("Favorite Number: %d", number);
}

int main(){
    char color[50], animal[50];
    int number;
    
    printf("Enter your favorite color: ");
    scanf("%s", &color);
    printf("Enter your favorite animal: ");
    scanf("%s", &animal);
    printf("Enter your favorite number: ");
    scanf("%d", &number);
    
    displayFavorites(color, animal, number);
    return 0;
}
