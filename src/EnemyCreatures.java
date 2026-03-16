public class EnemyCreatures extends Creatures{

    int maxHp;

    EnemyCreatures(String name, String element, int hp, int attack, int magicAttack, int heal, int level, int xp, int maxXp){
        super(name, element, hp, attack, magicAttack, heal, level, xp, maxXp);
        this.maxHp = hp;
    }

    @Override
    public String toString(){
        return "your enemy is " + this.name + "! element: " + this.element + " hp " + this.hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp(){
        return hp;
    }

}
