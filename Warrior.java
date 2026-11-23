/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoop;

public class Warrior extends GameCharacter implements Attacker {
    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        this.armor = Math.max(0, armor);
    }

    @Override
    public int calculatePower() {
        return getLevel() * 10 + armor * 2;
    }

    @Override
    public int takeDamage(int damage) {
        if (!isAlive() || damage <= 0) {
            return 0;
        }
        int reducedDamage = Math.max(1, damage - (armor / 2));
        return super.takeDamage(reducedDamage);
    }

    @Override
    public int attack(GameCharacter target) {
        if (!this.isAlive()) {
            System.out.println(getName() + " is defeated and cannot attack.");
            return 0;
        }
        if (target == null || !target.isAlive()) {
            System.out.println("Target is already defeated or invalid.");
            return 0;
        }
        int damage = calculatePower() / 4;
        int dealt = target.takeDamage(damage);
        System.out.println(getName() + " (Warrior) attacks " + target.getName() + " for " + dealt + " damage!");
        return dealt;
    }

    @Override
    public String getCharacterInfo() {
        return String.format("[Warrior] Name: %s | Level: %d | HP: %d/%d | Armor: %d | Power: %d | Alive: %b",
                getName(), getLevel(), getHealth(), getMaxHealth(), armor, calculatePower(), isAlive());
    }
}