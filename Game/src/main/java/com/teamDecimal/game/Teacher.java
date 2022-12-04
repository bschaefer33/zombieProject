package zombiewar;

/**
 *
 * Team Decimal 12/4/22
 */
public class Teacher extends Survivor{
            
    Teacher(){
        super.setStartingHealth(50);
        super.setCurrentHealth(50);
        super.setAttackDamage(5);
        super.setCharType("Teacher");
    }
}
