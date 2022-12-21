package com.teamDecimal.game;

/**
 *
 * Team Decimal 12/4/22
 */

import java.util.*;

public class Game {

    public static Child[] childArray;
    public static Tank[] tankArray;
    public static CommonInfect[] commonInfectArray;
    public static Teacher[] teacherArray;
    public static Soldier[] soldierArray; 

    public static List<Zombie> zombieList = new ArrayList<>();
    public static List<Zombie> zombieGraveyard = new ArrayList();
    public static List<Survivor> survivorList = new ArrayList<>();
    public static List<Survivor> survivorGraveyard = new ArrayList();
    
    public static boolean isRunning = true;
    
    
    public static void main(String[] args){
        //game logic
        System.out.println("Game Start!");
        createCharacters();
        System.out.println("We have " + survivorList.size() + " survivors trying to make it to safety (" + childArray.length + " children, " + teacherArray.length + " teachers, " + soldierArray.length + " soldiers.)");
        System.out.println("There are " + zombieList.size() + " zombies waiting for them (" + commonInfectArray.length + " common infected, " + tankArray.length + " tanks.)");
        while (isRunning){
            survivorsAttack();
            zombiesAttack();
            isRunning = declareWinner();
        }
       // System.out.println("It seems " + survivorList.size() + " have made it to safety.");
    }
    
    public static void createCharacters(){
        int[] randNums = new int[5];
        for (int i = 0; i < 5; i++){
            int randomSize = (int)(Math.random()*10);
            randNums[i] = randomSize;
        }//end of for loop

        //Initialize size of arrays
        childArray = new Child[randNums[0]];
        teacherArray= new Teacher[randNums[1]];
        soldierArray = new Soldier[randNums[2]];
        tankArray= new Tank[randNums[3]];
        commonInfectArray = new CommonInfect[randNums[4]];
      
            for (int i = 0; i < 10; i++){
                if (i < childArray.length){
                    Child child = new Child();
                    child.setID(i);
                    assignWeapon(child);
                    childArray[i] = child; //adds a child into the array
                    survivorList.add(child);
                }
                if(i < teacherArray.length){
                    Teacher teacher = new Teacher();
                    teacher.setID(i);
                    assignWeapon(teacher);
                    teacherArray[i] = teacher;//add in a teacher into array
                    survivorList.add(teacher);
       
                }
                if(i < soldierArray.length){
                    Soldier soldier = new Soldier();
                    soldier.setID(i);
                    assignWeapon(soldier);
                    soldierArray[i] = soldier;//add in a soldier into array 
                    survivorList.add(soldier);
                }
                if(i < commonInfectArray.length){
                    CommonInfect infected = new CommonInfect();
                    infected.setID(i);
                    commonInfectArray[i] = infected; //add in a CommonInfected object array 
                    zombieList.add(infected);
                }
                if(i < tankArray.length){
                    Tank tank = new Tank();
                    tank.setID(i);
                    tankArray[i] = tank; //add in a tank object into array 
                    zombieList.add(tank);
                }
            }//end of for loop i
     
    
    
    }
    public static void assignWeapon(Character survivor){
        //random number to choose weapon
        int randomSize = (int)(Math.random()*7);
        switch(randomSize){
            case 0: 
                ShotGun shotGun = new ShotGun();
                survivor.setWeapon(shotGun);
                break;
            case 1: 
                MachineGun machineGun = new MachineGun();
                survivor.setWeapon(machineGun);
                break;
            case 2: 
                AssaultRifle assaultRifle = new AssaultRifle();
                survivor.setWeapon(assaultRifle);
                break;
            case 3: 
                Pistol pistol = new Pistol();
                survivor.setWeapon(pistol);
                break;
            case 4: 
                Axe axe = new Axe();
                survivor.setWeapon(axe);
                break;
            case 5: 
                CrowBar crowBar = new CrowBar();
                survivor.setWeapon(crowBar);
                break;
            case 6:
                FryingPan fryingPan = new FryingPan();
                survivor.setWeapon(fryingPan);
            default: 
                break;
        }
        //switch to attach to survivor
    }
    
    public static void survivorsAttack(){
        //determine which list is shorter to use for attacks, to prevent out of bounds exception
        if (survivorList.size() <= zombieList.size()){
            for (int i = 0; i < survivorList.size(); i++){
                //if survivor is alive, attack zombie at opposing index in zombieList
                if (survivorList.get(i).isAlive()){
                    zombieList.get(i).setCurrentHealth(zombieList.get(i).getCurrentHealth() - (survivorList.get(i).getAttackDamage() + survivorList.get( i).getWeapon().getDamageScore()));
                    //System.out.println(survivorList.get(i).getCharType() + " dealt " + String.valueOf(survivorList.get(i).getAttackDamage()) + " to " + zombieList.get(i).getCharType());
                }
                if (zombieList.get(i).getCurrentHealth() <= 0){
                    System.out.println(survivorList.get(i).getCharType() + " " + survivorList.get(i).getID() + " Killed " + zombieList.get(i).getCharType() + " " + zombieList.get(i).getID() + " with " + survivorList.get(i).getWeaponString());
                }
                
            }
        }
        else{
            for (int i = 0; i < zombieList.size(); i++){
                if (survivorList.get(i).isAlive()){
                    zombieList.get(i).setCurrentHealth(zombieList.get(i).getCurrentHealth() - (survivorList.get(i).getAttackDamage() + survivorList.get( i).getWeapon().getDamageScore()));
                   // System.out.println(survivorList.get(i).getCharType() + " dealt " + String.valueOf(survivorList.get(i).getAttackDamage()) + " to " + zombieList.get(i).getCharType());
                }
                if (zombieList.get(i).getCurrentHealth() <= 0){
                    System.out.println(survivorList.get(i).getCharType() + " " + survivorList.get(i).getID() + " Killed " + zombieList.get(i).getCharType() + " " + zombieList.get(i).getID()+ " with " + survivorList.get(i).getWeaponString());
                }
            }
        }
        
        //check for characters that are no longer alive and update boolean and add to graveyard ArrayList
        for (Zombie z: zombieList){
            if (z.getCurrentHealth() < 1){
                z.setIsAlive(false);
                //System.out.println(z.getCharType() + " died");
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
                    //System.out.println(zombieList.get(i).getCharType() + " dealt " + String.valueOf(zombieList.get(i).getAttackDamage()) + " to " + survivorList.get(i).getCharType());
                }
                if (survivorList.get(i).getCurrentHealth() <= 0){
                    System.out.println(zombieList.get(i).getCharType() + " " + zombieList.get(i).getID() + " Killed " + survivorList.get(i).getCharType() + " " + survivorList.get(i).getID());
                }

            }
        }
        else{
            for (int i = 0; i < survivorList.size(); i++){
                if (zombieList.get(i).isAlive()){
                    survivorList.get(i).setCurrentHealth(survivorList.get(i).getCurrentHealth() - zombieList.get(i).getAttackDamage());
                    //System.out.println(zombieList.get(i).getCharType() + " dealt " + String.valueOf(zombieList.get(i).getAttackDamage()) + " to " + survivorList.get(i).getCharType());
                }
                if (survivorList.get(i).getCurrentHealth() <= 0){
                    System.out.println(zombieList.get(i).getCharType() + " " + zombieList.get(i).getID() + " Killed " + survivorList.get(i).getCharType() + " " + survivorList.get(i).getID());
                }
            }
        }
        
        //check for characters that are no longer alive and update boolean and add to graveyard ArrayList
        for (Survivor s: survivorList){
            if (s.getCurrentHealth() < 1){
                s.setIsAlive(false);
                //System.out.println(s.getCharType() + " died");
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
