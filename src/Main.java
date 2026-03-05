import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<MyCreatures> myCreaturesList = new ArrayList<>();
        ArrayList<EnemyCreatures> enemyCreaturesList = new ArrayList<>();

        enemyCreaturesList.add(new EnemyCreatures("Flue", "Fire", 100, 20, 0, 0));
        enemyCreaturesList.add(new EnemyCreatures("Flowerpiller", "Nature", 80, 15, 0, 0));
        enemyCreaturesList.add(new EnemyCreatures("Prawnja", "Water", 110, 25, 0, 0));



        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0));
        myCreaturesList.add(new MyCreatures("Flowerpiller", "Nature", 80, 15, 0, 0));
        myCreaturesList.add(new MyCreatures("Prawnja", "Water", 110, 25, 0, 0));
        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0));
        myCreaturesList.add(new MyCreatures("Flowerpiller", "Nature", 80, 15, 0, 0));
        myCreaturesList.add(new MyCreatures("Prawnja", "Water", 110, 25, 0, 0));
        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0));
        myCreaturesList.add(new MyCreatures("Flowerpiller", "Nature", 80, 15, 0, 0));
        myCreaturesList.add(new MyCreatures("Prawnja", "Water", 110, 25, 0, 0));
        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0));
        myCreaturesList.add(new MyCreatures("Flowerpiller", "Nature", 80, 15, 0, 0));
        myCreaturesList.add(new MyCreatures("Prawnja", "Water", 110, 25, 0, 0));


        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        System.out.println("Welcome To Miscrits");
        System.out.println("Press F to start the fight: ");
        System.out.println("Press M to see your Miscrits: ");
        Character choseOption = scanner.nextLine().toUpperCase().charAt(0);

        switch(choseOption){
            case 'F' -> {
                    EnemyCreatures enemy = enemyCreaturesList.get(random.nextInt(enemyCreaturesList.size()));
                    MyCreatures player = myCreaturesList.get(0);
                    System.out.println(enemy);
                    System.out.println(player);
                while (enemy.hp > 0 && player.hp > 0) {
                    System.out.println("Chose your move: ");
                    System.out.println("1) Burn");
                    System.out.println("2) Power up");
                    System.out.println("3) Smack");
                    System.out.print("Type here: ");
                    int choseMove = scanner.nextInt();

                    switch (choseMove) {
                        case 1 -> System.out.println("Burn");
                        case 2 -> player.activatePowerUp(3);
                        case 3 -> player.smack(enemy);

                    }
                    if (enemy.hp <= 0){
                        System.out.println(enemy.name + " is defeated!");
                        break;
                    }

                    int choseEnemyMove = random.nextInt(3) + 1;
                    switch(choseEnemyMove){
                        case 1 -> System.out.println("Burn");
                        case 2 -> enemy.activatePowerUp(3);
                        case 3 -> enemy.smack(player);
                    }
                    if (player.hp <= 0){
                        System.out.println(player.name + " is defeated!");
                        break;
                    }
                }
            }

            case 'M'-> {
                System.out.println("My Miscrits:");
                for (int i = 0; i < myCreaturesList.size(); i++) {
                    System.out.println((i + 1) + ") " + myCreaturesList.get(i).name);
                }
            }






            default -> System.out.println("Press F for playing");
        }
        scanner.close();
    }
}
