import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<MyCreatures> myCreaturesList = new ArrayList<>();
        ArrayList<EnemyCreatures> enemyCreaturesList = new ArrayList<>();

        ArrayList<MyCreatures> myTeam = new ArrayList<>();

        enemyCreaturesList.add(new EnemyCreatures("enemy", "Water", 200, 20, 50, 0, 5, 0, 10));


        myCreaturesList.add(new MyCreatures("Flur", "Fire", 10000, 20, 50, 0, 0, 10, 10));
        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0, 0, 20, 10));
        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0, 0, 10, 10));
        myCreaturesList.add(new MyCreatures("Flur", "Fire", 100, 20, 0, 0, 0, 10, 10));


        myTeam.add(myCreaturesList.get(0));
        myTeam.add(myCreaturesList.get(1));
        myTeam.add(myCreaturesList.get(2));
        myTeam.add(myCreaturesList.get(3));

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean gameIsRunning = true;
        boolean menuIsRunning = true;

        while(gameIsRunning) {

        System.out.println("Welcome To Miscrits");
        System.out.println("Press F to start the fight: ");
        System.out.println("Press M to see your Miscrits: ");
        Character choseOption = scanner.nextLine().toUpperCase().charAt(0);
            boolean captureUsed = false;

        for(EnemyCreatures creature : enemyCreaturesList){
            creature.hp = creature.maxHp;
        }

        switch (choseOption) {
            case 'F' -> {

                int playerLevel = (myTeam.get(0).level + myTeam.get(1).level + myTeam.get(2).level + myTeam.get(3).level) / 4;
                int minLevel = Math.max(1, playerLevel - 2);
                int maxLevel = playerLevel + 2;
                int enemyLevel = random.nextInt(maxLevel - minLevel + 1) + minLevel;

                EnemyCreatures template = enemyCreaturesList.get(random.nextInt(enemyCreaturesList.size()));
                EnemyCreatures enemy = new EnemyCreatures(
                        template.name,
                        template.element,
                        150 * enemyLevel,
                        15 * enemyLevel,
                        0, 0,
                        enemyLevel,
                        0,
                        50 * enemyLevel
                );



                MyCreatures player = myTeam.get(0);
                System.out.println(player + " lvl:" + myCreaturesList.get(0).level);
                System.out.println(enemy + " lvl:" + enemyLevel);


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
                        case 1 -> player.burn(enemy);
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

                            if(captureUsed == true){
                                System.out.println("Capture is already used");
                            }else {
                                if (roll > captureChance) {
                                    System.out.println("Capture failed");
                                    captureUsed = true;
                                } else if (roll <= captureChance) {
                                    System.out.println("You capture the " + enemy.name);
                                    myCreaturesList.add(new MyCreatures(enemy.name, enemy.element, enemy.hp, enemy.attack, enemy.magicAttack, enemy.heal, player.xp, 1, 10));
                                    break fightLoop;
                                }
                            }

                        }


                    }
                    if (enemy.hp <= 0) {
                        System.out.println(enemy.name + " is defeated!");// 10 XP за уровень врага
                        player.gainXpFrom(enemy);
                        break fightLoop;
                    }



                    int choseEnemyMove = random.nextInt(3) + 1;
                    switch (choseEnemyMove) {
                        case 1 -> enemy.burn(player);
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
                    System.out.println((i + 1) + ") " + myCreaturesList.get(i).name + ": level " + myCreaturesList.get(i).level + " ✨ " + myCreaturesList.get(i).xp + "/" + myCreaturesList.get(i).maxXp);
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
