package com.example.heads_hands;

public class Main {
    public static void main(String[] args) {
        Monster dragon = new Monster("Gold dragon", 25, 20, 10, 5);
        Player vasyaIvanov = new Player("Vasya Ivanov", 20, 25, 12, 4);

        System.out.println("Fight!");
        startFight(dragon, vasyaIvanov);

        if (dragon.isDead()) {
            dragon.getHealed();
        } else if (vasyaIvanov.isDead()) {
            vasyaIvanov.getHealed();
        }

        System.out.println("\nFight!");
        startFight(dragon, vasyaIvanov);
    }

    public static void startFight(ACreature creature1, ACreature creature2) {
        int i = 0;
        while (!creature1.isDead() && !creature2.isDead()) {
            if (i % 2 == 0) {
                creature1.hit(creature2);
            } else {
                creature2.hit(creature1);
            }
            i++;
        }

        if (creature1.isDead()) {
            System.out.printf("%s is winner\n", creature2.getName());
        } else if (creature2.isDead()) {
            System.out.printf("%s is winner\n", creature1.getName());
        }
    }
}

