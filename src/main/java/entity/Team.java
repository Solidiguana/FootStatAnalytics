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

    public Team(String name, String city,  int leagueId) {
        this.name = name;
        this.city = city;
        this.leagueId = leagueId;
    }

    public String getName() { return name; }

    @Override
    public String toString() { String lName = (leagueName != null) ? leagueName : "Unknown League";
        return String.format("ID:%-3d | %-15s | %-10s | %s", id, name, city, lName);
    }

    public String getCity() { return city; }

    public int getLeagueID() { return leagueId; }

    public String getLeagueName() { return leagueName; }
}
