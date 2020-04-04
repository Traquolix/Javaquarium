package Aquarium.content;

public class LivingEntities {

    int hp;
    int age;

    public LivingEntities() {
        this.hp = 10;
        this.age = 0;
    }


    /**
     * Getter for the age.
     * @return the age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter of the age.
     * @param age you want to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter for the actual HP.
     * @return the actual HP.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Add + 1 to the age.
     */
    public void getOlder() {
        this.age++;
    }

    /**
     * Setter for the HP.
     * @param hp is the HP you want to set.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Return a summary of the Object
     * @return a String with all the components.
     */
    @Override
    public String toString() {
        return "LivingEntities{" +
                "hp=" + hp +
                ", age=" + age +
                '}';
    }
}
