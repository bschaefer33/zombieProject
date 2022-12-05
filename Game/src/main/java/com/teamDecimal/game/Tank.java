package com.teamDecimal.game;

/**
 *
 * Team Decimal 12/4/22
 */
public class Tank extends Zombie{
            
    Tank(){
        super.setStartingHealth(150);
        super.setCurrentHealth(150);
        super.setAttackDamage(20);
        super.setCharType("Tank");
    }
}
