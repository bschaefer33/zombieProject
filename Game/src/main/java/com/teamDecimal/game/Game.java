package com.teamDecimal.game;

/**
 *
 * Team Decimal 12/4/22
 */

import java.util.*;

public class Game {
    public static List<Zombie> zombieList = new ArrayList<>();
    public static List<Zombie> zombieGraveyard = new ArrayList();
    public static List<Survivor> survivorList = new ArrayList<>();
    public static List<Survivor> survivorGraveyard = new ArrayList();
    
    public static boolean isRunning = true;
    
    
    public static void main(String[] args){
        //game logic
        System.out.println("Game Start!");
        createCharacters();
        while (isRunning){
            survivorsAttack();
            zombiesAttack();
            isRunning = declareWinner();
        }
    }
    
    public static void createCharacters(){
        //Zombies created
        Tank zombie1 = new Tank();
        CommonInfect zombie2 = new CommonInfect();
        CommonInfect zombie3 = new CommonInfect();
        CommonInfect zombie4 = new CommonInfect();
        zombieList.add(zombie1);
        zombieList.add(zombie2);
        zombieList.add(zombie3);
        zombieList.add(zombie4);
        
        //Survivors created
        Child survivor1 = new Child();
        Teacher survivor2 = new Teacher();
        Soldier survivor3 = new Soldier();
        Soldier survivor4 = new Soldier();
        Soldier survivor5 = new Soldier();
        survivorList.add(survivor1);
        survivorList.add(survivor2);
        survivorList.add(survivor3);
        survivorList.add(survivor4);
        survivorList.add(survivor5);

    }
    
    public static void survivorsAttack(){
        //determine which list is shorter to use for attacks, to prevent out of bounds exception
        if (survivorList.size() <= zombieList.size()){
            for (int i = 0; i < survivorList.size(); i++){
                //if survivor is alive, attack zombie at opposing index in zombieList
                if (survivorList.get(i).isAlive()){
                    zombieList.get(i).setCurrentHealth(zombieList.get(i).getCurrentHealth() - survivorList.get(i).getAttackDamage());
                    System.out.println(survivorList.get(i).getCharType() + " dealt " + String.valueOf(survivorList.get(i).getAttackDamage()) + " to " + zombieList.get(i).getCharType());
                }
                
            }
        }
        else{
            for (int i = 0; i < zombieList.size(); i++){
                if (survivorList.get(i).isAlive()){
                    zombieList.get(i).setCurrentHealth(zombieList.get(i).getCurrentHealth() - survivorList.get(i).getAttackDamage());
                    System.out.println(survivorList.get(i).getCharType() + " dealt " + String.valueOf(survivorList.get(i).getAttackDamage()) + " to " + zombieList.get(i).getCharType());
                }
            }
        }
        
        //check for characters that are no longer alive and update boolean and add to graveyard ArrayList
        for (Zombie z: zombieList){
            if (z.getCurrentHealth() < 1){
                z.setIsAlive(false);
                System.out.println(z.getCharType() + " died");
                zombieGraveyard.add(z);
            }
        }
        
        //if characters are now present in graveyard, remove all from corresponding list
        zombieList.removeAll(zombieGraveyard);
    }
    
    public static void zombiesAttack(){
        //determine which list is shorter to use for attacks, to prevent out of bounds exception
        if (zombieList.size() <= survivorList.size()){
            for (int i = 0; i < zombieList.size(); i++){
                //if zombie is alive, attack survivor at opposing index in zombieList
                if (zombieList.get(i).isAlive()){
                    survivorList.get(i).setCurrentHealth(survivorList.get(i).getCurrentHealth() - zombieList.get(i).getAttackDamage());
                    System.out.println(zombieList.get(i).getCharType() + " dealt " + String.valueOf(zombieList.get(i).getAttackDamage()) + " to " + survivorList.get(i).getCharType());
                }
            }
        }
        else{
            for (int i = 0; i < survivorList.size(); i++){
                if (zombieList.get(i).isAlive()){
                    survivorList.get(i).setCurrentHealth(survivorList.get(i).getCurrentHealth() - zombieList.get(i).getAttackDamage());
                    System.out.println(zombieList.get(i).getCharType() + " dealt " + String.valueOf(zombieList.get(i).getAttackDamage()) + " to " + survivorList.get(i).getCharType());
                }
            }
        }
        
        //check for characters that are no longer alive and update boolean and add to graveyard ArrayList
        for (Survivor s: survivorList){
            if (s.getCurrentHealth() < 1){
                s.setIsAlive(false);
                System.out.println(s.getCharType() + " died");
                survivorGraveyard.add(s);
            }
        }
        
        //if characters are now present in graveyard, remove all from corresponding list
        survivorList.removeAll(survivorGraveyard);
    }
    
    //method determines if all members of a list are no longer alive,
    //then returns end game message and boolean value to end while loop
    public static boolean declareWinner(){
        if (survivorList.isEmpty()){
            System.out.println("Survivors wiped out!");
            return false;
        }
        else if(zombieList.isEmpty()){
            System.out.println("Zombies defeated!");
            return false;
        }
        return true;
    }
}
