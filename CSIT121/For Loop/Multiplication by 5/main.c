#include <stdio.h>

int main(){

    int number;

    printf("Enter a number: ");
    scanf("%d", &number);

    int starting = 0;

    for(int i = 1; i <= number; i++){
        // hmm, meaning sa babaw kay i <= number, murag sa nested na loop,
        // akong condition sa taas gihimo nakong number of rows base sa gi input 

        // ma update napud siya for second, second line, 2 <= number
        starting = starting + 5;
        // dinhi akong starting kay zero, so mao every first line nako kay 5

        // starting 5+5 
        printf("%d\n", starting);
        // so print ang first 5, then balik napud ug loop sa taas

        //starting value = 5
        // prints 10
    }

    return 0;
}
