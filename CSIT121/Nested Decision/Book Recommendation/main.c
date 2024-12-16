#include <stdio.h>

int main(){
    
    int age;
    char genre;
    
    printf("Enter your age: ");
    scanf("%d", &age);
    
    printf("Enter your genre preference (a for adventure, m for mystery, f for fantasy, s for science fiction): ");
    scanf(" %c", &genre);
    
    if(age >= 8 && age <= 12){
        if(genre == 'a'){
            printf("The Adventures of Tom Sawyer");
        }else if(genre == 'm'){
            printf("Nancy Drew: The Secret of the Old Clock");
        }
    }
    
    if(age >= 13){
        if(genre == 'f'){
        printf("Harry Potter and the Sorcerer's Stone");
        }else if(genre == 's'){
        printf("Ender's Game");
    }
    } else if(age < 8 || age > 17){
        printf("No recommendation available");
    }
    
    return 0;
}
