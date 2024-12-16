#include <stdio.h>

int main(){
    char weather[50];
    char firstLetter = weather[0];
    
    printf("Enter the weather condition: ");
    scanf("%c", &firstLetter);
    
    switch(firstLetter){
        case 'S':
            printf("Suggestion: Enjoy the sunshine");
            break;
        case 'C':
            printf("Suggestion: Stay indoors");
            break;
        case 'R':
            printf("Suggestion: Bring an umbrella");
            break;
        case 'W':
            printf("Suggestion: Fly a kite");
            break;
        default:
            printf("Invalid weather condition.");
            break;
    }
    
    return 0;
}
