/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoop;

public class Mage extends GameCharacter implements Attacker, Supporter {
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = Math.max(0, mana);
    }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
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
        if (mana < 20) {
            System.out.println(getName() + " does not have enough mana to attack! (Current mana: " + mana + ")");
            return 0;
        }

        // Güç, mana harcanmadan önce hesaplanır
        int damage = calculatePower() / 3;
        mana -= 20;

        int dealt = target.takeDamage(damage);
        System.out.println(getName() + " (Mage) casts a spell on " + target.getName() + " for " + dealt + " damage! (Mana left: " + mana + ")");
        return dealt;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!this.isAlive()) {
            System.out.println(getName() + " is defeated and cannot heal.");
            return 0;
        }
        if (target == null || !target.isAlive()) {
            System.out.println("Target is defeated and cannot be healed.");
            return 0;
        }
        if (mana < 15) {
            System.out.println(getName() + " does not have enough mana to heal! (Current mana: " + mana + ")");
            return 0;
        }

        mana -= 15;
        int healAmount = getLevel() * 2;
        int restored = target.restoreHealth(healAmount);
        System.out.println(getName() + " (Mage) heals " + target.getName() + " for " + restored + " HP! (Mana left: " + mana + ")");
        return restored;
    }

    @Override
    public String getCharacterInfo() {
        return String.format("[Mage] Name: %s | Level: %d | HP: %d/%d | Mana: %d | Power: %d | Alive: %b",
                getName(), getLevel(), getHealth(), getMaxHealth(), mana, calculatePower(), isAlive());
    }
}