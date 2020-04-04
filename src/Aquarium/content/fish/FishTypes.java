package Aquarium.content.fish;

public enum FishTypes {

    grouper('c', 1),
    tuna('c', 0),
    clownFish('c', 2),
    sole('h', 2),
    bass('h', 1),
    carp('h', 0);

    private char diet;
    private int sexuality;

    FishTypes(char c, int sexuality) {
        this.diet = c;
        this.sexuality = sexuality; // Monosexué = 0 / Herma âge = 1 / Herma opportuniste = 2 /
    }

    public boolean eatFish() {
        return diet == 'c';
    }

    public boolean isMonosexual() {
        return sexuality == 0;
    }

    public boolean isHermaWithAge() {
        return sexuality == 1;
    }

    public boolean isHermaWhenWants() {
        return sexuality == 2;
    }
}
