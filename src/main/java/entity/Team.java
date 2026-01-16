package entity;

public class Team extends BaseEntity {
    private String name;
    private String city;

    public Team(int id, String name, String city) {
        super(id);
        this.name = name;
        this.city = city;
    }

    public Team(String name, String city) {
        this(0, name, city);
    }

    public String getName() { return name; }

    @Override
    public String toString() { return "ID: " + id + " | " + name + " (" + city + ")"; }

    public String getCity() { return city; }
}
