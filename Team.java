/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoop;

import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members;

    public Team() {
        this.members = new ArrayList<>();
    }

    public boolean addCharacter(GameCharacter character) {
        if (character == null) return false;
        if (findByName(character.getName()) != null) {
            System.out.println("Error: A character named '" + character.getName() + "' already exists in the team!");
            return false;
        }
        members.add(character);
        System.out.println(character.getName() + " joined the team.");
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter found = findByName(name);
        if (found != null) {
            members.remove(found);
            System.out.println(name + " has been removed from the team.");
            return true;
        }
        System.out.println("Character '" + name + "' not found in the team.");
        return false;
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public void attackAll(GameCharacter target) {
        System.out.println("--- Team Attacks " + target.getName() + " ---");
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Attacker) {
                ((Attacker) c).attack(target);
            }
        }
    }

    public void healTeam() {
        System.out.println("--- Team Healing Phase ---");
        for (GameCharacter supporter : members) {
            if (supporter.isAlive() && supporter instanceof Supporter) {
                GameCharacter lowestRatioMember = getLowestHealthRatioMember();
                if (lowestRatioMember != null) {
                    ((Supporter) supporter).heal(lowestRatioMember);
                }
            }
        }
    }

    private GameCharacter getLowestHealthRatioMember() {
        GameCharacter target = null;
        double minRatio = Double.MAX_VALUE;

        for (GameCharacter c : members) {
            if (c.isAlive()) {
                double ratio = (double) c.getHealth() / c.getMaxHealth();
                if (ratio < minRatio) {
                    minRatio = ratio;
                    target = c;
                }
            }
        }
        return target;
    }

    public int getTotalPower() {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive()) {
                total += c.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("=== TEAM ROSTER (Total Alive Power: " + getTotalPower() + ") ===");
        for (GameCharacter c : members) {
            System.out.println(c.getCharacterInfo());
        }
        System.out.println("==========================================");
    }
}