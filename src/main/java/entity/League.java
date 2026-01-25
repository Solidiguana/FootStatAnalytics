package entity;

public class League extends BaseEntity {
    private String name;
    private String country;

    public League(int id, String name, String country) {
        super(id);
        this.name=name;
        this.country=country;
    }

    public League(String name, String country) {
        this.name=name;
        this.country=country;
    }

    public String getName() { return name; }
    public String getCountry() { return country; }

    @Override
    public String toString() {
        return String.format("ID:%d | %s (%s)", id, name, country);
    }
}