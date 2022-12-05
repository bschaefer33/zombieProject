package com.teamDecimal.game;

/**
 *
 * Team Decimal 12/4/22
 */
public class Character {
    private int currentHealth;
    private int startingHealth;
    private int attackDamage;
    private boolean isAlive = true;
    
    public int getAttackDamage(){
        return attackDamage;
    }
    
    public void setAttackDamage(int dmg){
        this.attackDamage = dmg;
    }
    
    public int getStartingHealth(){
        return startingHealth;
    }
    
    public void setStartingHealth(int hp){
        this.startingHealth = hp;
    }
    
    public int getCurrentHealth(){
        return currentHealth;
    }
    
    public void setCurrentHealth(int currentHealth){
        this.currentHealth = currentHealth;
    }
    
    public boolean isAlive(){
        return isAlive;
    }
    
    public void setIsAlive(boolean condition){
        this.isAlive = condition;
    }
}
