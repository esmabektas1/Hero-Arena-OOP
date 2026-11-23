/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoop;

public class Healer extends GameCharacter implements Supporter {
    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = Math.max(0, healAmount);
    }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
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
        int totalHeal = healAmount + getLevel();
        int restored = target.restoreHealth(totalHeal);
        System.out.println(getName() + " (Healer) heals " + target.getName() + " for " + restored + " HP!");
        return restored;
    }

    @Override
    public String getCharacterInfo() {
        return String.format("[Healer] Name: %s | Level: %d | HP: %d/%d | HealAmount: %d | Power: %d | Alive: %b",
                getName(), getLevel(), getHealth(), getMaxHealth(), healAmount, calculatePower(), isAlive());
    }
}