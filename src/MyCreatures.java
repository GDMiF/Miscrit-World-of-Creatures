public class MyCreatures extends Creatures{

    MyCreatures(String name, String element, int hp, int attack, int magicAttack, int heal, int xp, int level){
        super(name, element, hp, attack, magicAttack, heal, level, xp);
    }

    @Override
    public String toString(){
        return "your Miscrit is " + this.name + "! element: " + this.element + " hp: " + this.hp + "!";
    }

}
