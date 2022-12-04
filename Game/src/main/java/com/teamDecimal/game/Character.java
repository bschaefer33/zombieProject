/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamDecimal.game;

/**
 *
 * @author brittanydesktop
 */
public class Character {
    /*Attributes*/
    int currenthealth;
    int startingHealth;
    int attackDamage;
    
    /*Constructors*/
    public int getAttackDamage(){
        return attackDamage;
    }
    
    public int getStartingHealth(){
        return startingHealth;
    }
    
    public void setStartingHealth(int startingHealth){
        this.startingHealth = startingHealth;
    }
    
    public void setCurrentHealth(int currentHealth){
        this.currenthealth = currentHealth;
    }
    
    public int getCurrentHealth(){
        return currenthealth;
    }
    
    /*Methods*/
    public boolean isAlive(){
        boolean isAlive;
        if(currenthealth <= 0){
            isAlive = false;
        }else{
            isAlive = true;
        }
        return isAlive;
    }
    
}
