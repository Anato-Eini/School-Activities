#include <stdio.h>

int main(){
    int num = 5;

    int start = 2;
    for(int i = 0; i < num; i++){
        printf("%d\n", start);
        start = start + 2;
    }
    return 0;
}
