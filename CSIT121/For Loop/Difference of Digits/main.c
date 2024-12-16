#include <stdio.h>

int main(){

    int number;

    printf("Enter a positive integer: ");  //12345
    scanf("%d", &number);


    int storage = number % 10; //first kuhaon sa ang 5 ibutang ditso sa storage, 
                               // kay if di nimo kuhaon, ang storage = storage - remainder1 sa ubos 
                               // sa first loop ma -5 siya, nya ang
                               // next na -4 kay ig plus nila ma -9, soooo, sayop na siya daan jud.

    number = number/10; // 12345/10 = 1234

    // so ang sugod na kay 1234 sa ubos
    for(int i = number; i > 0;){
        int remainder1 = i % 10; // 1234 % 10 = 4

        storage = storage - remainder1 ; // dinhi magamit ang 5 na gi una sa taas,
                                         // 5 - 4 = 1

        i = i/10; // 1234/10 = 123, then 123 nasad ang next i loop.

    }

    printf("Result of subtracting digits: %d", storage); //result sa storage
    return 0;
}
