package Aquarium.content.algae;

import Aquarium.content.LivingEntities;

public class Algae extends LivingEntities {

    /**
     * Constructor of an Algae
     */
    public Algae() {
        super();
    }

    /**
     * Grow +1HP
     */
    public void grow() {
        this.setHp(super.getHp()+1);
    }

    /**
     * Get eaten (-2HP)
     */
    public void getEaten() {
        this.setHp(super.getHp()-2);
    }

    /**
     * Summuarize an algae
     * @return a string summarizing an algae
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
