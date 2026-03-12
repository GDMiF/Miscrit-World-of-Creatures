import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<MyCreatures> myCreaturesList = new ArrayList<>();
        ArrayList<EnemyCreatures> enemyCreaturesList = new ArrayList<>();

        ArrayList<MyCreatures> myTeam = new ArrayList<>();

        enemyCreaturesList.add(new EnemyCreatures("flur", "Fire", 200, 20, 0, 0, 1, 0));


        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0, 0, 1));
        myCreaturesList.add(new MyCreatures("Flowerpiller", "Nature", 800, 15, 0, 0, 0, 1));
        myCreaturesList.add(new MyCreatures("Prawnja", "Water", 110, 25, 0, 0, 0, 1));
        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0, 0, 1));
        myCreaturesList.add(new MyCreatures("Prawnja", "Water", 110, 25, 0, 0, 0, 1));
        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0, 0, 1));

        myTeam.add(myCreaturesList.get(1));
        myTeam.add(myCreaturesList.get(2));
        myTeam.add(myCreaturesList.get(3));
        myTeam.add(myCreaturesList.get(4));

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean gameIsRunning = true;
        boolean menuIsRunning = true;

    while(gameIsRunning) {

        System.out.println("Welcome To Miscrits");
        System.out.println("Press F to start the fight: ");
        System.out.println("Press M to see your Miscrits: ");
        Character choseOption = scanner.nextLine().toUpperCase().charAt(0);

        for(EnemyCreatures creature : enemyCreaturesList){
            creature.hp = creature.maxHp;
        }

        switch (choseOption) {
            case 'F' -> {
                EnemyCreatures enemy = enemyCreaturesList.get(random.nextInt(enemyCreaturesList.size()));
                MyCreatures player = myTeam.get(0);
                System.out.println(player);
                System.out.println(enemy);


                fightLoop:
                while (enemy.hp > 0 && player.hp > 0) {
                    System.out.println("Chose your move: ");
                    System.out.println("1) Burn");
                    System.out.println("2) Power up");
                    System.out.println("3) Smack");
                    System.out.println("4) Change Miscrit");
                    System.out.println("5) Escape");
                    System.out.println("6) Capture Miscrit \uD83D\uDCD6");
                    System.out.printf("Capture chance: %.1f%%\n", 45 + (100 - (double) enemy.getHp() / enemy.getMaxHp() * 100) / 2);
                    System.out.print("Type here: ");
                    int choseMove = scanner.nextInt();
                    scanner.nextLine();

                    switch (choseMove) {
                        case 1 -> System.out.println("Burn");
                        case 2 -> player.activatePowerUp(3);
                        case 3 -> player.smack(enemy);
                        case 4 -> {
                            for (int i = 0; i < myTeam.size(); i++) {
                                System.out.println((i + 1) + ") " + myTeam.get(i).name);
                            }
                            System.out.print("Chose Miscrit: ");
                            choseMove = scanner.nextInt();
                            switch (choseMove) {
                                case 1 -> {
                                    player = myTeam.get(0);
                                    System.out.println("your chose " + player);
                                }
                                case 2 -> {
                                    player = myTeam.get(1);
                                    System.out.println("your chose " + player);

                                }
                                case 3 -> {
                                    player = myTeam.get(2);
                                    System.out.println("your chose " + player);
                                }
                                case 4 -> {
                                    player = myTeam.get(3);
                                    System.out.println("your chose " + player);
                                }
                            }

                        }
                        case 5 -> {
                            System.out.println("You Escaped");
                            break fightLoop;
                        }
                        case 6 -> {
                            int maxHp = enemy.getMaxHp();
                            int currentHp = enemy.getHp();
                            double hpPercent = (double) currentHp / maxHp * 100;
                            double captureChance = 45 + (100 - hpPercent) / 2;

                            int roll = random.nextInt(100) + 1;
                            if (roll > captureChance) {
                                System.out.println("Capture failed");
                            } else if (roll <= captureChance) {
                                System.out.println("You capture the " + enemy.name);
                                myCreaturesList.add(new MyCreatures(enemy.name, enemy.element, enemy.hp, enemy.attack, enemy.magicAttack, enemy.heal, player.xp, enemy.level));
                                break fightLoop;
                            }


                        }


                    }
                    if (enemy.hp <= 0) {
                        System.out.println(enemy.name + " is defeated!");
                        break fightLoop;
                    }



                    int choseEnemyMove = random.nextInt(3) + 1;
                    switch (choseEnemyMove) {
                        case 1 -> System.out.println("Burn");
                        case 2 -> enemy.activatePowerUp(3);
                        case 3 -> enemy.smack(player);
                    }
                    if (player.hp <= 0) {
                        System.out.println(player.name + " is defeated!");
                        break fightLoop;
                    }
                }
            }

            case 'M' -> {
                System.out.println("My Miscrits:");
                for (int i = 0; i < myCreaturesList.size(); i++) {
                    System.out.println((i + 1) + ") " + myCreaturesList.get(i).name);
                }
                while (menuIsRunning) {
                    System.out.println("Press C for Change your Team");
                    System.out.println("Press S for show your team");
                    System.out.println("Press E for Exit");
                    System.out.print("Type here: ");
                    choseOption = scanner.nextLine().toUpperCase().charAt(0);
                    switch (choseOption) {
                        case 'C' -> {
                            myTeam.clear();
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Choose creature number for slot " + (i + 1) + ":");
                                int choice = scanner.nextInt();
                                scanner.nextLine();
                                MyCreatures selected = myCreaturesList.get(choice - 1);

                                if (myTeam.contains(selected)) {
                                    System.out.println("You already chose this Myscrit! Choose another one.");
                                    i--;
                                } else {
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


    }
        scanner.close();
    }
}
