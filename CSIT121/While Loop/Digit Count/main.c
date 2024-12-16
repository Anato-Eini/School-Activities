#include <stdio.h>

int main(){
    int num;

    printf("Enter a positive integer: "); // example: 232
    scanf("%d", &num);

    int digit_count = 0; // no value pani siya. ok.
    while(num > 0){ // kani kay diba sa while, magcheck
                    // jud na siyas condition first, noh, so here,
                    // check nimo if ang gihatag na number is greater than
                    // zero. niya proceed sa codeblock if true. ok.
        num = num/10; // 232 / 10 = 23.2 new value
                      // niya kani, this is a way how to remove the last digit
                      // entirely.

        digit_count++;
        // The digit count logic doesn't care about the 
        // remainders when dividing by 10. 
        // kahuman sa ug divide, ang digitcount ma +1,
        // gi divide man nimo imoha num, nahimong 23.2,
        // check napud siya, is 23.2 > 0? yes.
        // so divide napud mahimo 2.3 nalng bilin, 
        // and digitcount ma 2, nya ang int 2.3 nabilin is 
        // greater than 0 paman jud so execute siya codeblock
        // so digitcount 3, mao wala na. 
    }

    printf("Number of digits: %d", digit_count);
    return 0;
}
