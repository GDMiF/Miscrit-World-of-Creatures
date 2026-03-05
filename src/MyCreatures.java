public class MyCreatures extends Creatures{

    MyCreatures(String name, String element, int hp, int attack, int magicAttack, int heal){
        super(name, element, hp, attack, magicAttack, heal);
    }

    @Override
    public String toString(){
        return "your Miscrit is " + this.name + "! element: " + this.element + " hp: " + this.hp + "!";
    }

}
