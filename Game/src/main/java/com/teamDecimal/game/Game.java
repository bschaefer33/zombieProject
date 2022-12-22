package com.teamDecimal.game;

/**
 *
 * Team Decimal 12/4/22
 */

import java.util.*;

public class Game {

    public static int childAmount;
    public static int teacherAmount;
    public static int soldierAmount;
    public static int commonInfectAmount;
    public static int tankAmount;
    /*public static Child[] childArray;
    public static Tank[] tankArray;
    public static CommonInfect[] commonInfectArray;
    public static Teacher[] teacherArray;
    public static Soldier[] soldierArray; 
    */
    public static List<Zombie> zombieList = new ArrayList<>();
    public static List<Zombie> zombieGraveyard = new ArrayList();
    public static List<Survivor> survivorList = new ArrayList<>();
    public static List<Survivor> survivorGraveyard = new ArrayList();
    
    public static boolean isRunning = true;
    
    
    public static void main(String[] args){
        //game logic
        System.out.println("Game Start!");
        createCharacters();
        System.out.println("We have " + survivorList.size() + " survivors trying to make it to safety (" + childAmount + " children, " + teacherAmount + " teachers, " + soldierAmount + " soldiers.)");
        System.out.println("There are " + zombieList.size() + " zombies waiting for them (" + commonInfectAmount + " common infected, " + tankAmount + " tanks.)");
        do{
            survivorsAttack();
            removeTheDeadZombies();
            zombiesAttack();
            removeTheDeadSurvivors();
            isRunning = declareWinner();
        }while(isRunning);
        
        
        //System.out.println("It seems " + survivorList.size() + " have made it to safety.");
    }
    
    public static void assignWeapon(Survivor survivor){
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
    
    public static void createCharacters(){
        int[] randNums = new int[5];
        for (int i = 0; i < 5; i++){
            int randomSize = (int)(Math.random()*10);
            randNums[i] = randomSize;
        }//end of for loop

        //Initialize size of each character
        childAmount = randNums[0];
        teacherAmount = randNums[1];
        soldierAmount = randNums[2];
        commonInfectAmount = randNums[3];
        tankAmount = randNums[4];
        
        int characterSize = childAmount + teacherAmount + soldierAmount + commonInfectAmount + tankAmount;
        
        /*childArray = new Child[randNums[0]];
        teacherArray= new Teacher[randNums[1]];
        soldierArray = new Soldier[randNums[2]];
        tankArray= new Tank[randNums[3]];
        commonInfectArray = new CommonInfect[randNums[4]];*/
        for(int x = 0;x < childAmount; x++){
            Child child = new Child();
            child.setID(x);
            assignWeapon(child);
            survivorList.add(child);
        }
        for(int y = 0;y < teacherAmount; y++){
            Teacher teacher = new Teacher();
            teacher.setID(y);
            assignWeapon(teacher);
            survivorList.add(teacher);
        }
        for(int z = 0;z < soldierAmount; z++){        
            Soldier soldier = new Soldier();
            soldier.setID(z);
            assignWeapon(soldier);
            survivorList.add(soldier);
        }
        for(int a = 0;a < commonInfectAmount; a++){
            CommonInfect infected = new CommonInfect();
            infected.setID(a);
            zombieList.add(infected);
        }
        for(int b = 0;b < tankAmount; b++){
            Tank tank = new Tank();
            tank.setID(b);
            zombieList.add(tank);
        }    
        /*for(Survivor survivor: survivorList){
            System.out.println(survivor.getCharType() + survivor.getID() + " has a " + survivor.getWeaponString());
        }
        for(Zombie zombie: zombieList){
            System.out.println(zombie.getCharType() + zombie.getID());
        }*/
    
    }
    
    
    public static void survivorsAttack(){
        int survivorSize = survivorList.size() -1;
        int zombieSize = zombieList.size() -1;
        for(int x = 0;x < survivorSize; x++){
            Survivor survivor;
            survivor = survivorList.get(x);
            for(int y= 0; y < zombieSize; y++){
                Zombie zombie;
                zombie= zombieList.get(y);
                int zombieHealth = zombie.getCurrentHealth();
                int survivorDamage = survivor.getAttackDamage() + survivor.getWeapon().getDamageScore();
                if((zombieHealth - survivorDamage) > 0){
                    zombie.setCurrentHealth(zombieHealth - survivorDamage);
                    System.out.println(survivor.getCharType() + survivor.getID() + " attacked " + zombie.getCharType() + zombie.getID() 
                        + " with a " + survivor.getWeaponString() + " worth " + survivorDamage + " points. "
                        + zombie.getCharType() + zombie.getID() + " now has a health score of " + zombie.getCurrentHealth() + " points.");      
                }else{
                    zombie.setCurrentHealth(0);
                    System.out.println(survivor.getCharType() + survivor.getID() + " attacked " + zombie.getCharType() + zombie.getID() 
                        + " with a " + survivor.getWeaponString() + " worth " + (survivor.getAttackDamage() + survivor.getWeapon().getDamageScore()) 
                        + " points." + zombie.getCharType() + zombie.getID() + " has died.");
                    zombieGraveyard.add(zombie);
                }             
            }
            
        }
        
        
        
        
    }

    
    
    public static void zombiesAttack(){
        int survivorSize = survivorList.size() -1;
        int zombieSize = zombieList.size() -1;
        for(int x = 0;x < zombieSize; x++){
            Zombie zombie;
            zombie = zombieList.get(x);
            for(int y= 0; y < survivorSize; y++){
                Survivor survivor;
                survivor = survivorList.get(y);
                int survivorHealth = survivor.getCurrentHealth();
                int zombieDamage = zombie.getAttackDamage();
                if((survivorHealth - zombieDamage) > 0){
                    survivor.setCurrentHealth(survivorHealth - zombieDamage);
                    System.out.println(zombie.getCharType() + zombie.getID() + " attacked " + survivor.getCharType() + survivor.getID()   
                        + ". " + survivor.getCharType() + survivor.getID() + " now has a health score of " + survivor.getCurrentHealth() + " points.");      
                }else{
                    survivor.setCurrentHealth(0);
                    System.out.println(zombie.getCharType() + zombie.getID() + " attacked " + survivor.getCharType() + survivor.getID()   
                        + ". " + survivor.getCharType() + survivor.getID() + " is dead.");
                    survivorGraveyard.add(survivor);
                }             
            }
            
        }
        
    }
    
    public static void removeTheDeadZombies(){
        for(int x = 0;x < zombieList.size();x++){
            Zombie zombie = zombieList.get(x);
            if(zombie.getCurrentHealth() == 0){
                zombieList.remove(zombie);
                x=0;
            }
        }
    }
    
    public static void removeTheDeadSurvivors(){
        for(int x = 0;x < survivorList.size();x++){
            Survivor survivor = survivorList.get(x);
            if(survivor.getCurrentHealth() == 0){
                survivorList.remove(survivor);
                x=0;
            }
        }
    }
    //method determines if all members of a list are no longer alive,
    //then returns end game message and boolean value to end while loop
    public static boolean declareWinner(){
        if (survivorList.isEmpty()){
            System.out.println("Survivors wiped out!");
            return true;
        }
        else 
            System.out.println("Zombies defeated!");
            return false;
        }
        
    
}
