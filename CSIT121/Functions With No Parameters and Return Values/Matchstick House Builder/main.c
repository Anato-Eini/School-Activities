#include <stdio.h>

void getMatchSticksNeeded(){
    int houses;

    printf("Enter the number of houses to make: ");
    scanf("%d", &houses);

    int matchsticks;
    if(houses > 0){
        matchsticks = 6 + (houses - 1) * 5;
    }else{
        matchsticks = 0;
    }

    printf("Number of matchsticks needed: %d\n", matchsticks);
}

int main(){
    getMatchSticksNeeded();

}
