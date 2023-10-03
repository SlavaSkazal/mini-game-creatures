package com.example.heads_hands;
import java.util.Random;

public abstract class ACreature{
    private int attack;
    private int maxAttack;
    private int protection;
    private int maxProtection;
    private int health;
    private int maxHealth;
    private int damage;
    private String name;
    private boolean dead;
    private int numberOfHealings;
    private int maxHealings;
    private int id;
    private static int countID = 1;

    public ACreature(String name, int attack, int protection, int health, int damage) {
        this.dead = false;
        this.numberOfHealings = 0;
        this.maxHealings = 4;
        this.name = name;
        this.maxAttack = 30;
        this.maxProtection = 30;
        this.id = countID;
        countID++;

        Random rand = new Random();
        if (attack < 1 || attack > this.maxAttack) {
            attack = rand.nextInt(1, 31);
            reportInvalidArgument("attack");
        }
        this.attack = attack;
        if (protection < 1 || protection > this.maxProtection) {
            protection = rand.nextInt(1, 31);
            reportInvalidArgument("protection");
        }
        this.protection = protection;
        if (health < 1) {
            health = rand.nextInt(10, 31);
            reportInvalidArgument("health");
        }
        this.health = health;
        this.maxHealth = health;
        if (damage < 1 || damage > 6) {
            damage = rand.nextInt(1, 7);
            reportInvalidArgument("damage");
        }
        this.damage = damage;
    }

    public boolean hit(ACreature enemy) {
        if (!theseAreDifferentCreatures(this.id, enemy.getID())) {
            reportNotDifferentCreatures();
            return false;
        }
        if (this.isDead()) {
            reportDeadOffside("attacking");
            return false;
        }
        if (enemy.isDead()) {
            reportDeadOffside("attacked");
            return false;
        }

        boolean success = false;
        int modifierAttack = calculateModifierAttack(this.attack, enemy.getProtection());
        Random rand = new Random();

        for (int i = 0; i < modifierAttack; i++) {
            int randRes = rand.nextInt(1, 7);
            if (randRes == 5 || randRes == 6) {
                success = true;
                break;
            }
        }

        if (success) {
            int attackPoints = rand.nextInt(1, this.damage+1);
            enemy.reduceHealth(attackPoints);
        }
        return success;
    }

    private void checkDeath() {
        if (this.health == 0) {
            this.dead = true;
        }
    }

    public void reduceHealth(int attackPoints) {
        if (this.isDead()) {
            reportDeadOffside("attacked");
            return;
        }

        this.health -= attackPoints;
        if (this.health < 0) {
            this.health = 0;
        }
        this.checkDeath();
    }

    public boolean getHealed() {
        if (!this.isDead()) {
            return false;
        }

        boolean success = false;
        if (this.numberOfHealings < this.maxHealings) {
            this.numberOfHealings++;
            this.health = this.maxHealth * 100 / 300;
            this.dead = false;
            success = true;
        }
        return success;
    }

    private static void reportNotDifferentCreatures() {
        System.out.println("These are not different creatures.");
    }

    private static void reportInvalidArgument(String argName) {
        System.out.printf("Invalid argument value %s. The value was chosen randomly.\n", argName);
    }

    private static void reportDeadOffside(String roleCreature) {
        System.out.printf("Creature %s is dead.\n", roleCreature);
    }

    public static boolean theseAreDifferentCreatures(int id1, int id2) {
        if (id1 == id2) {
            return false;
        } else {
            return true;
        }
    }

    private static int calculateModifierAttack(int attack, int protection) {
        int modifierAttack = attack - protection + 1;
        if (modifierAttack < 1) {
            modifierAttack = 1;
        }
        return modifierAttack;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getProtection() {
        return this.protection;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }

    public boolean isDead() {
        return this.dead;
    }
}
