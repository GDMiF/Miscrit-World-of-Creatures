public class Creatures {

    String name;
    String element;
    int hp;
    int attack;
    int magicAttack;
    int heal;
    int level;
    int xp;
    int maxXp;

    boolean powerUpActive = false;
    int powerUpTurns = 0;

    Creatures(String name, String element, int hp, int attack, int magicAttack, int heal, int level, int xp, int maxXp){
        this.name = name;
        this.element = element;
        this.hp = hp;
        this.attack = attack;
        this.magicAttack = magicAttack;
        this.heal = heal;
        this.level = level;
        this.xp = xp;
        this.maxXp = maxXp;
    }


        static int fire = 0;
        static int water = 1;
        static int nature = 2;
        static int lightning = 3;
        static int earth = 4;
        static int wind = 5;

    double[][] elementMatrix = {
            {1.0, 0.5, 3.0, 1.0, 1.0, 1.0},
            {3.0, 1.0, 0.5, 1.0, 1.0, 1.0},
            {0.5, 3.0, 1.0, 1.0, 1.0, 1.0},
            {1.0, 1.0, 1.0, 1.0, 0.5, 3.0},
            {1.0, 1.0, 1.0, 3.0, 1.0, 0.5},
            {1.0, 1.0, 1.0, 0.5, 3.0, 1.0},
    };

    public int getElementIndex() {
        switch (element) {
            case "Fire":
                return fire;
            case "Water":
                return water;
            case "Nature":
                return nature;
            case "lightning":
                return lightning;
            case "earth":
                return earth;
            case "wind":
                return wind;
            default:
                return 0;
        }
    }

    public double getElementMultiplier(Creatures enemy) {
        int myIndex = this.getElementIndex();
        int enemyIndex = enemy.getElementIndex();
        return elementMatrix[myIndex][enemyIndex];
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

    public void updateMaxXp() {
        this.maxXp = this.level * 20;
    }

    public int getMaxXp() {
        return this.maxXp;
    }

    public void gainXpFrom(Creatures enemy) {
        int xpGained = enemy.level + 9;
        this.xp += xpGained;
        System.out.println("✨ " + this.name + " gained " + xpGained + " XP!");

        while (this.xp >= this.getMaxXp()) {
            this.level++;
            this.xp -= this.getMaxXp();
            this.updateMaxXp();
            System.out.println("⭐ " + this.name + " reached level " + this.level + "!");
        }
    }


    public void burn(Creatures target) {

        double multiplier = this.getElementMultiplier(target);
        magicAttack = (int) (magicAttack * multiplier);

        if (powerUpActive) {
            magicAttack *= 2;
            powerUpTurns--;
        }

        target.takeDamage(magicAttack);
        System.out.println(this.name + " uses Burn on " + target.name + " for " + magicAttack + " damage!");
    }

}
