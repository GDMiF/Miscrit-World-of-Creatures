import javax.sound.sampled.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        String filePath = "src\\miscrit-sunfall-kingdom-ost-2-sunfall-village.wav";
        File file = new File(filePath);

        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch(FileNotFoundException e){
            System.out.println("Could not locate file");
        }
        catch(UnsupportedAudioFileException e){
            System.out.println("Audio file is not supported");
        }
        catch(LineUnavailableException e){
            System.out.println("Unable to acces audio resource");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }

        ArrayList<MyCreatures> myCreaturesList = new ArrayList<>();
        ArrayList<EnemyCreatures> enemyCreaturesList = new ArrayList<>();
        ArrayList<MyCreatures> myTeam = new ArrayList<>();

        enemyCreaturesList.add(new EnemyCreatures("Flue", "Fire", 100, 20, 20, 100, 1, 0, 10));
        enemyCreaturesList.add(new EnemyCreatures("Flowerpiller", "Nature", 80, 30, 20, 80, 1, 0, 10));
        enemyCreaturesList.add(new EnemyCreatures("Pravnja", "Water", 90, 25, 20, 90, 1, 0, 10));
        enemyCreaturesList.add(new EnemyCreatures("Defilio", "Earth", 130, 20, 20, 130, 1, 0, 10));
        enemyCreaturesList.add(new EnemyCreatures("Buzzock", "Lightning", 120, 25, 20, 120, 1, 0, 10));
        enemyCreaturesList.add(new EnemyCreatures("Da Windy", "Air", 110, 30, 20, 110, 1, 0, 10));

        myCreaturesList.add(new MyCreatures("Flue", "Fire", 1000, 20, 20, 100, 0, 1, 10));
        myTeam.add(myCreaturesList.get(0));

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean gameIsRunning = true;

        while(gameIsRunning) {

        System.out.println("Welcome To Miscrits\uD83C\uDFAE");
        System.out.println("Press F to start the fight⚔\uFE0F ");
        System.out.println("Press M to see your Miscrits\uD83D\uDCD6 ");
        System.out.println("Press H to heal your Miscrits\uD83D\uDC9B");
        System.out.println("Press X to close the game❌");
        System.out.print("Press here: ");
        Character choseOption = scanner.nextLine().toUpperCase().charAt(0);
        boolean captureUsed = false;

        for(EnemyCreatures creature : enemyCreaturesList){
            creature.hp = creature.maxHp;
        }

        switch (choseOption) {
            case 'F' -> {

                int playerLevel = 0;
                for (MyCreatures c : myTeam) {
                    playerLevel += c.level;
                }
                playerLevel = playerLevel / myTeam.size();
                int minLevel = Math.max(1, playerLevel - 2);
                int maxLevel = playerLevel + 2;
                int enemyLevel = random.nextInt(maxLevel - minLevel + 1) + minLevel;

                EnemyCreatures template = enemyCreaturesList.get(random.nextInt(enemyCreaturesList.size()));
                EnemyCreatures enemy = new EnemyCreatures(
                        template.name,
                        template.element,
                        template.hp + enemyLevel * 5,
                        template.attack + enemyLevel * 5,
                        template.magicAttack,
                        template.heal,
                        enemyLevel,
                        0,
                        template.maxXp
                );

                MyCreatures player = myTeam.get(0);
                System.out.println(player + " lvl:" + myCreaturesList.get(0).level);
                System.out.println(enemy + " lvl:" + enemyLevel);

                fightLoop:
                while (enemy.hp > 0 && player.hp > 0) {
                    System.out.println("Chose your move: ");
                    System.out.println("1) Magic Damage");
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
                            System.out.print("Choose Miscrit: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();

                            if (choice >= 1 && choice <= myTeam.size()) {
                                player = myTeam.get(choice - 1);
                                System.out.println("You chose " + player.name);
                            } else {
                                System.out.println("Invalid choice!");
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
                                    enemy.hp = enemy.heal;
                                    myCreaturesList.add(new MyCreatures(enemy.name, enemy.element, enemy.hp, enemy.attack, enemy.magicAttack, enemy.heal, player.xp, 1, 10));
                                    for (MyCreatures teamMember : myTeam) {
                                        teamMember.gainXpFrom(enemy);
                                    }
                                    break fightLoop;
                                }
                            }

                        }
                        default -> System.out.println("Chose the valid option or you will lose :(");

                    }
                    if (enemy.hp <= 0) {
                        System.out.println(enemy.name + " is defeated!");
                        for (MyCreatures teamMember : myTeam) {
                            teamMember.gainXpFrom(enemy);
                        }
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

                boolean menuIsRunning = true;

                while (menuIsRunning) {
                    System.out.println("Press C for Change your Team");
                    System.out.println("Press S for show your team");
                    System.out.println("Press E for Exit");
                    System.out.print("Type here: ");
                    choseOption = scanner.nextLine().toUpperCase().charAt(0);
                    switch (choseOption) {
                        case 'C' -> {
                            myTeam.clear();

                            // Сколько у нас есть Miscrits (но не больше 4)
                            int count = Math.min(myCreaturesList.size(), 4);

                            for (int i = 0; i < count; i++) {
                                System.out.println("Choose creature for slot " + (i + 1) + ":");
                                int choice = scanner.nextInt();
                                scanner.nextLine();
                                myTeam.add(myCreaturesList.get(choice - 1));
                            }

                            System.out.println("Your Team is:");
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
                        default -> System.out.println("Please chose valid option");
                    }
                }
            }

            case 'H' -> {
                for (MyCreatures creature : myTeam) {
                    creature.hp = creature.heal;
                    System.out.println(creature.name + " healed to full HP! ❤️");
                }

                System.out.println("Your team is fully healed!");
            }

            case 'X' -> {
                System.out.println("Thanks for playing\uD83D\uDE4F ");
                gameIsRunning = false;
            }

            default -> System.out.println("Please chose valid option");
        }
    }
        scanner.close();
    }
}
