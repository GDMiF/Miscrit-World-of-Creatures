import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<MyCreatures> myCreaturesList = new ArrayList<>();
        ArrayList<EnemyCreatures> enemyCreaturesList = new ArrayList<>();

        ArrayList<MyCreatures> myTeam = new ArrayList<>();

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
        myTeam.add(myCreaturesList.get(3));
        myTeam.add(myCreaturesList.get(4));
        myTeam.add(myCreaturesList.get(5));

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean menuIsRunning = true;


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
                    System.out.println("4) Change Miscrit");
                    System.out.print("Type here: ");
                    int choseMove = scanner.nextInt();

                    switch (choseMove) {
                        case 1 -> System.out.println("Burn");
                        case 2 -> player.activatePowerUp(3);
                        case 3 -> player.smack(enemy);
                        case 4 -> {
                            for (int i = 0; i < myTeam.size(); i++) {
                                System.out.println((i + 1) + ") " + myTeam.get(i).name);
                            }
                            System.out.print("Chose Miscrit");
                            choseMove = scanner.nextInt();

                        }

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
                while(menuIsRunning){
                    System.out.println("Press C for Change your Team");
                    System.out.println("Press S for show your team");
                    System.out.println("Press E for Exit");
                    System.out.print("Type here: ");
                    choseOption = scanner.nextLine().toUpperCase().charAt(0);
                    switch (choseOption) {
                        case 'C' -> {
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Choose creature number for slot " + (i + 1) + ":");
                                int choice = scanner.nextInt();
                                scanner.nextLine();
                                MyCreatures selected = myCreaturesList.get(choice -1);

                                if(myTeam.contains(selected)){
                                    System.out.println("You already chose this Myscrit! Choose another one.");
                                    i--;
                                }else{
                                    myTeam.add(selected);
                                }
                            }
                            System.out.println("Your Team is");
                            for (int i = 0; i < myTeam.size(); i++) {
                                System.out.println((i + 1) + ") " + myTeam.get(i).name);
                            }
                        }
                        case 'S' -> {
                            System.out.println("My Team is: ");
                            for (int i = 0; i < myTeam.size(); i++) {
                                System.out.println((i + 1) + ") " + myTeam.get(i).name);
                            }
                        }
                        case 'E' -> {
                            System.out.println("You exited menu");
                            menuIsRunning = false;
                        }
                    }
                }
            }






            default -> System.out.println("Press F for playing");
        }
        scanner.close();
    }
}
