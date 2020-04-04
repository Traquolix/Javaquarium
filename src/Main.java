import Aquarium.Aquarium;
import Aquarium.content.fish.Fish;
import Aquarium.content.fish.FishTypes;
import Aquarium.content.fish.Gender;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();

        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();
        aquarium.addAlgae();

        Fish fish1 = new Fish("Melina", Gender.FEMALE, FishTypes.carp);
        Fish fish2 = new Fish("Milo", Gender.MALE, FishTypes.carp);
        Fish fish3 = new Fish("Lila", Gender.FEMALE, FishTypes.clownFish);
        Fish fish4 = new Fish("Alexis", Gender.FEMALE, FishTypes.carp);
        Fish fish5 = new Fish("Laura", Gender.FEMALE, FishTypes.carp);
        Fish fish6 = new Fish("Théo", Gender.FEMALE, FishTypes.carp);
        Fish fish7 = new Fish("Weirdo", Gender.FEMALE, FishTypes.carp);
        Fish fish8 = new Fish("Léana", Gender.FEMALE, FishTypes.carp);

        aquarium.addFish(fish1);
        aquarium.addFish(fish2);
        aquarium.addFish(fish3);
        aquarium.addFish(fish4);
        aquarium.addFish(fish5);
        aquarium.addFish(fish6);
        aquarium.addFish(fish7);
        aquarium.addFish(fish8);

        Scanner sc = new Scanner(System.in);
        System.out.println("Starting program... Use 0 to stop, 1 to start the simulation.");
        int value = sc.nextInt();

        while(!(value == 0)){
            aquarium.addTimeAndDisplay();

            System.out.println("Use 0 to stop, 1 to continue.");
            value = sc.nextInt();
        }

        System.out.println("Thanks for using the Javaquarium...");





    }
}
