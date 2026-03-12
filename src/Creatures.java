public class Creatures {

    String name;
    String element;
    int hp;
    int attack;
    int magicAttack;
    int heal;
    int level;
    int xp;

    boolean powerUpActive = false;
    int powerUpTurns = 0;

    Creatures(String name, String element, int hp, int attack, int magicAttack, int heal, int level, int xp){
        this.name = name;
        this.element = element;
        this.hp = hp;
        this.attack = attack;
        this.magicAttack = magicAttack;
        this.heal = heal;
        this.level = level;
    }

    public void takeDamage(int damage){
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public void activatePowerUp(int turn){
        powerUpActive = true;
        powerUpTurns = turn;
        System.out.println(this.name + " powered up, Miscrit attacks will be increased for " + turn + " turns");
    }

    public void smack(Creatures target){
        int damage = 50;
        if(powerUpActive){
            damage *= 2;
            powerUpTurns --;
        } if(powerUpTurns <= 0){
            powerUpActive = false;
        }

        target.takeDamage(damage);
        System.out.println(this.name + " hits " + target.name + " for " + damage + " damage!");
        System.out.println(target.name + "'s hp is " + target.hp);
    }

}
