/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamDecimal.game;

/**
 *
 * @author brittanylaptop
 */
public class Weapon {
    int damage;
    double accuracy;
    String type;
    
    public void setDamage(int damage) {
        this.damage = damage;
    } 
    public int getDamage() {
        return damage;
    }   
    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }
    public double getAccuracy() {
        return accuracy;
    } 
    public void setType(String type) {
        this.type = type;
    }   
    public String getType() {
        return type;
    } 
    public int getDamageScore(){
        //get random number between accuracy and 100
        double randomAccuracy = accuracy + Math.random() * (100-accuracy);
        //divide by 100
        //multiply by damage
        //return damageScore
        return (int)((randomAccuracy/100) * damage);
    }
}
