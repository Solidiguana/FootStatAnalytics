package entity;

public class Team extends BaseEntity {
    private String name;
    private String city;
    private String leagueName;
    private int leagueId;

    public Team(int id, String name, String city, String leagueName) {
        super(id);
        this.name = name;
        this.city = city;
        this.leagueName = leagueName;
    }

    public Team(String name, String city,  String leagueID) {
        this(0, name, city, leagueID);
    }

    public String getName() { return name; }

    @Override
    public String toString() { return "ID: " + id + " | " + name + " (" + city + ")"; }

    public String getCity() { return city; }

    public int getLeagueID() { return leagueId; }
}
