#include <stdio.h>

int main(){
    
    char word[40];
   
   while(1){ 
    printf("Enter a word: ");
    scanf("%s", word);
   
    int length;
    length = strlen(word);
    
    char reversed[100];
    for(int i = 0; i < length; i++){
        reversed[i] = word[length - 1 - i];
    }
    reversed[length] = '\0';
    
    if(strcasecmp(word, reversed) == 0){
        printf("Palindrome found!\n");
        break;
    }
    
   }
    return 0;
}
