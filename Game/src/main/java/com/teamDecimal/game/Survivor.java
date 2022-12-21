
package com.teamDecimal.game;

/**
 *
 * Team Decimal 12/4/22
 */
public class Survivor extends Character{
    private String charType;
    private Weapon weaponSelected;
    
    public String getCharType(){
        return charType;
    }
    
    public void setCharType(String type){
        this.charType = type;
    }
    public Weapon getWeapon(){
        return this.weaponSelected;
    }
    public void setWeapon(Weapon weaponSelected){
        this.weaponSelected = weaponSelected;
    }
    public String getWeaponString(){
        return weaponSelected.getType();
    }
}
