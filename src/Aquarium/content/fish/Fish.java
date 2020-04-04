package Aquarium.content.fish;

import Aquarium.Aquarium;
import Aquarium.content.LivingEntities;

import java.util.Random;

public class Fish extends LivingEntities {

    FishTypes race;
    String name;
    Gender sex;
    LivingEntities actualTarget;
    LivingEntities lastTarget;

    /**
     *
     * @param name
     * @param sex is the sex of the fish. Have to be "m/M" (Male) or "f/F" (Female), or it won't work.
     */
    public Fish(String name, Gender sex, FishTypes race) {
        super();

        this.race = race;
        this.actualTarget = new LivingEntities();
        this.lastTarget = new LivingEntities();
        this.name = name;
        this.sex = sex;

    }

    /**
     * Get the actual race of the fish
     * @return the race of the fish
     */
    public FishTypes getRace() {
        return race;
    }

    /**
     * Get the sex of the fish
     * @return the sex of the fish
     */
    public Gender getSex() {
        return sex;
    }

    /**
     * Get the actual target of the fish (Algae for herbs, another fish for carnivores)
     * @return the actual target
     */
    public LivingEntities getActualTarget() {
        return actualTarget;
    }

    /**
     * set the actual target of the fish
     * @param actualTarget is the target you want to set
     */
    public void setActualTarget(LivingEntities actualTarget) {
        this.actualTarget = actualTarget;
    }

    /**
     * set the last target of the fish
     * @param lastTarget is the target you want to set
     */
    public void setLastTarget(LivingEntities lastTarget) {
        this.lastTarget = lastTarget;
    }

    /**
     * Set the sex of a fish
     * @param sex 'm' or 'f'
     */
    public void setSex(Gender sex) {
        this.sex = sex;
    }

    /**
     * toString override to summarize a fish
     * @return a string that summarize a fish
     */
    @Override
    public String toString() {
        return super.toString() + "Fish {" +
                "race = '" + race + '\'' +
                ", HP = " + getHp() +
                ", name = '" + name + '\'' +
                ", sex = " + sex +
                '}';
    }


    /**
     * Switch sex. Doesn't take account of non binary and genderfluid weird shit.
     */
    public void changeSex() {
        if (sex.equals(Gender.FEMALE)) {
            sex = Gender.MALE;
        } else {
            sex = Gender.FEMALE;
        }
    }

    /**
     * Fish get biten, loose 4hp.
     */
    public void getEaten() {
        super.setHp(super.getHp()-4);
    }

    /**
     * Set the target of the fish randomly and adequately with its diet
     */
    public void setTarget() { // SET TARGET

        if (race.eatFish()) {
            Random r = new Random();
            int x = r.nextInt(Aquarium.getFishList().size());

            while (x == Aquarium.getFishList().indexOf(this) || Aquarium.getFishList().get(x).getRace().equals(this.getRace()) || Aquarium.getFishList().get(x).equals(lastTarget)) { // While not same race, not ancient target and not itself.
                r = new Random();
                x = r.nextInt(Aquarium.getFishList().size());
            }

            actualTarget = Aquarium.getFishList().get(x);
            Aquarium.getTargetList().add(actualTarget);


        } else {
            Random r = new Random();
            int x = r.nextInt(Aquarium.getAlgaeList().size());

            actualTarget = Aquarium.getAlgaeList().get(x);
            Aquarium.getTargetList().add(actualTarget);
        }
    }
}
