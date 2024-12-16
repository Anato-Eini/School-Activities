#include <stdio.h>
#include <math.h>

int main(){

    int num;

    printf("Enter a number: "); // ask a number
    scanf("%d", &num);

    int i = 1; // starting point kay 1
    int result; // walay value
    while( i <= num){ // ang starting point agad sa number
        result = pow(i,2);
        //power(i, 2) where our result is our y
        //perfect square ni siya since x^2 = y man
        //first kay (1,2): 1x1 = 1, then ang value ihatag nimo
        // kay result, so value ni result kay 1, kahuman,
        // i print na nimo sa ubos.
        printf("%d\n", result);
        // maong 1
        i++;
        // increment man, so 2 napud, niya diba atong i is
        // dili lang pud pag determine pilay loop, kay 
        // gigamit pud nato siya sa as base sa code block
        // maong ma 2 pud ang naas codeblock, then pasa napud
        // value sa result, nya i print napud, so on..
    }
    return 0;
}
