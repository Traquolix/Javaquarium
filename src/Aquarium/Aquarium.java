package Aquarium;

import Aquarium.content.algae.Algae;
import Aquarium.content.LivingEntities;
import Aquarium.content.fish.Fish;
import Aquarium.content.fish.FishTypes;
import Aquarium.content.fish.Gender;

import java.util.ArrayList;
import java.util.Random;

public class Aquarium {

    static ArrayList<LivingEntities> targetList;
    static ArrayList<Fish> fishList;
    static ArrayList<Algae> algaeList;
    static int time;

    public Aquarium() {
        fishList = new ArrayList<>();
        algaeList = new ArrayList<>();
        targetList = new ArrayList<>();
    }

    public static ArrayList<LivingEntities> getTargetList() {
        return targetList;
    }
    public static ArrayList<Algae> getAlgaeList() {
        return algaeList;
    }

    public static ArrayList<Fish> getFishList() {
        return fishList;
    }

    public void addFish(Fish fish) {
        fishList.add(fish);
    }

    /**
     * Add a specific fish you build in the arguments to the aquarium
     * @param name is the name of the fish
     * @param sex is the sex of the fish
     * @param race
     */
    public void addFish(String name,Gender sex, FishTypes race) {
        Fish fish = new Fish(name, sex, race);
        fishList.add(fish);
    }

    /**
     * Add an algae to the aquarium
     */
    public void addAlgae() {
        Algae algae = new Algae();
        algaeList.add(algae);
    }

    /**
     * Display the content of the aquarium
     */
    private void display() {
        System.out.println("Algae number in the Aquarium : " + algaeList.size());

        StringBuilder sb = new StringBuilder();

        for (Fish fish : fishList) {
            sb.append(fish.toString());
            sb.append("\n");
        }
        System.out.println("\nFishList : ");
        System.out.println(sb + "\n\n");
    }

    /**
     * Add time and display
     */
    public void addTimeAndDisplay() {

        display();

        this.timeGoesAlong();
    }

    /**
     * Add time and make the mass instructions of living in an aquarium
     */
    private void timeGoesAlong() {
        time++;

        this.removeUncompletedOrDead();
        this.growingAlgae();

        this.removeUncompletedOrDead();
        this.gettingHungry();

        this.removeUncompletedOrDead();
        this.gettingEaten();

        this.removeUncompletedOrDead();
        this.eatingBenefits();

        this.removeUncompletedOrDead();
        this.gettingOld();

        this.removeUncompletedOrDead();
        this.tryToFuck();

        this.removeUncompletedOrDead();
        this.resetTargets();

        this.removeUncompletedOrDead();
    }



// ===========================================================

    /**
     * Clean the board
     */
    private void removeUncompletedOrDead() {

        int sizeFish = fishList.size();
        int sizeAlgae = algaeList.size();


        for (int i = 0; i < sizeFish; i++) {
            if (fishList.get(i).getHp() <= 0 || fishList.get(i).getAge() >= 20) {
                fishList.remove(i);
                sizeFish = fishList.size();
            }
        }
        for (int j = 0; j < sizeAlgae; j++) {
            if (algaeList.get(j).getHp() <= 0 || algaeList.get(j).getAge() >= 20) {
                algaeList.remove(j);
                sizeAlgae = algaeList.size();
            }
        }

    }

// ===========================================================

    /**
     * Growing algae (+1hp)
     */
    private void growingAlgae() {
        for (Algae algae : algaeList) {
            algae.grow();
        }
    }

    /**
     * Losing health by getting hungry + starting to find something to eat if under 5hp or if already chasing something
     */
    private void gettingHungry() {
        for (Fish fish : fishList) {
            fish.setHp(fish.getHp()-1);
            if(!(targetList.contains(fish))) {
                if ((fish.getHp() <= 5 || fish.getRace().eatFish())) {
                    fish.setTarget();
                }
            }
        }
    }

    /**
     * Logic to calculate which entity lose health when getting ate/bitten
     */
    private void gettingEaten() {
        for (LivingEntities target : targetList) {
            if(target.getClass() == Algae.class) {
                ((Algae) target).getEaten();
            } else {
                ((Fish) target).getEaten();
            }
        }
    }

    /**
     * Logic to calculate every eating benefits depending on the Actual Target
     */
    private void eatingBenefits() {
        for (Fish fish : fishList) {
            LivingEntities target = fish.getActualTarget();
            if (!(targetList.contains(fish))) {
                if (target.getClass() == Algae.class) {
                    fish.setHp(fish.getHp() + 3);
                } else if (target.getClass() == Fish.class) {
                    fish.setHp(fish.getHp() + 5);
                }
            }
        }
    }

    /**
     * Get older (change sex & get older)
     * Living entities dies after 20 days
     */
    private void gettingOld() {
        for (Fish fish : fishList) {
            fish.getOlder();
            if (fish.getRace().isHermaWithAge() && fish.getAge() >= 10) {
                fish.setSex(Gender.FEMALE);
            }
        }
        for (Algae algae : algaeList) {
            algae.grow();
        }
    }

    /**
     * Try to make a baby
     */
    private void tryToFuck() {

        int fishsize = fishList.size();
        int algaesize = algaeList.size();

        for (int i = 0; i < algaesize; i++) {
            if (algaeList.get(i).getHp() >= 10) {
                algaeList.get(i).setHp(algaeList.get(i).getHp()/2);
                Algae algae1 = new Algae();
                algae1.setHp(algaeList.get(i).getHp());
                this.addAlgae();
            }
        }

        for (int j = 0; j < fishsize; j++) {
            if (fishList.get(j).getHp() > 5) {
                Random r = new Random();
                int x = r.nextInt(fishsize);

                if (fishList.get(x).getRace().equals(fishList.get(j).getRace())) {
                    if (!(fishList.get(x).getSex().equals(fishList.get(j).getSex()))) {
                        if (fishList.get(x).getRace().isHermaWhenWants()) {
                            fishList.get(x).changeSex();
                        }
                        Random ra = new Random();
                        int y = ra.nextInt(100);
                        if (y < 50) {
                            this.addFish("Newborn" + x, Gender.MALE, fishList.get(j).getRace());
                        } else {
                            this.addFish("Newborn" + x, Gender.FEMALE, fishList.get(j).getRace());
                        }
                    }
                }

            }
        }

    }

    /**
     * Reset the actual target depending of it's diet and hungry"ness"
     */
    private void resetTargets() {
        for (Fish fish : fishList) {
            if (fish.getRace().eatFish()) {
                fish.setLastTarget(fish.getActualTarget());
                fish.setActualTarget(new LivingEntities());
            } else {
                if (fish.getActualTarget().getHp() <= 0) {
                    fish.setLastTarget(fish.getActualTarget());
                    fish.setActualTarget(new LivingEntities());
                }
            }
        }
        targetList.removeIf(target -> target.getClass() == Fish.class);
        targetList.removeIf(target -> target.getHp() <= 0);
    }

// ===========================================================

}
