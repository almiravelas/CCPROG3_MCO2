public class Creatures {
    private String name;
    private String type;
    private String family;
    private String image;
    private int evolutionLevel;
    private int health;

    public Creatures(String name, String type, String family, String image, int evolutionLevel, int health){
        this.name = name;
        this.type = type;
        this.family = family;
        this.image = image;
        this.evolutionLevel = evolutionLevel;
        this.health = health;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getFamily(){
        return family;
    }

    public String getImage(){
        return image;
    }

    public int getEvolutionLevel(){
        return evolutionLevel;
    }

    public int getHealth(){
        return health;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setFamily(String family){
        this.family = family;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void setEvolutionLevel(int evolutionLevel){
        this.evolutionLevel = evolutionLevel;
    }

    public void setHealth(int health){
        this.health = health;
    }

    //add the health also
}
