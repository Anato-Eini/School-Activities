public class Director {
    public void buildPlayer1(Builder builder){
            builder.hp(100)
                    .damage(10)
                    .name("Player 1");
    }
    public void buildPlayer2(Builder builder){
        builder.hp(100)
                .damage(10)
                .name("Player 2");
    }
    public void buildPlayer3(Builder builder){
        builder.hp(100)
                .damage(10)
                .name("Player 2");
    }
    public void buildPlayer4(Builder builder){
        builder.hp(100)
                .damage(10)
                .name("Player 2");
    }
    public void buildBoss(Builder builder){
        builder.hp(1000)
                .damage(20)
                .name("Boss");
    }
}
