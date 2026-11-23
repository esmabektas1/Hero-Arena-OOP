/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gameoop;

public class Main {
    public static void main(String[] args) {
        Team heroTeam = new Team();

        // 1. Geçersiz giriş testleri (Geçersiz seviye ve tekrar eden isim)
        System.out.println("--- Testing Invalid Inputs ---");
        Warrior invalidLevelWarrior = new Warrior("Arthur", 150, 200, 30); // Seviye 1'e ayarlanmalı
        Mage mage = new Mage("Merlin", 15, 120, 80);
        Healer healer = new Healer("Elise", 12, 110, 25);
        Warrior warrior = new Warrior("Leonidas", 18, 220, 40);

        heroTeam.addCharacter(warrior);
        heroTeam.addCharacter(mage);
        heroTeam.addCharacter(healer);
        heroTeam.addCharacter(invalidLevelWarrior);

        // Aynı isimde karakter ekleme testi
        Warrior duplicateWarrior = new Warrior("Leonidas", 10, 150, 20);
        heroTeam.addCharacter(duplicateWarrior);

        System.out.println();
        heroTeam.displayTeam();

        // Güçlü düşman oluşturma
        Warrior boss = new Warrior("Golem", 25, 500, 50);
        System.out.println("\nEnemy Appeared: " + boss.getCharacterInfo() + "\n");

        // 2. Savaş Simülasyonu (En az 2 tur)
        for (int round = 1; round <= 2; round++) {
            System.out.println("*************** ROUND " + round + " ***************");
            
            // Takım düşmana saldırır
            heroTeam.attackAll(boss);
            System.out.println("Boss Status: " + boss.getCharacterInfo());

            // Düşman hayattaysa takım üyelerine saldırır
            if (boss.isAlive()) {
                System.out.println("--- Boss Attacks ---");
                boss.attack(mage);
                boss.attack(warrior);
            }

            // Takım kendini iyileştirir
            heroTeam.healTeam();
            System.out.println();
        }

        // 3. Karakter arama, silme ve son durumu gösterme
        System.out.println("--- Searching & Removing Characters ---");
        GameCharacter searched = heroTeam.findByName("Merlin");
        if (searched != null) {
            System.out.println("Found character: " + searched.getCharacterInfo());
        }

        heroTeam.removeCharacter("Arthur");
        System.out.println();
        heroTeam.displayTeam();
    }
}