package entity;

public class Player extends BaseEntity {
    private String name;
    private String position;
    private int teamId;
    private String teamName;

    public Player(int id, String name, String position, String teamName) {
        super(id);
        this.name = name;
        this.position = position;
        this.teamName = teamName;
    }

    public Player(String name, String position, int teamId) {
        super();
        this.name = name;
        this.position = position;
        this.teamId = teamId;
    }

    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getTeamId() { return teamId; }

    @Override
    public String toString() {
        return String.format("ID: %-3d | %-15s | %-10s | %s", id, name, position, (teamName != null ? teamName : "TeamID: "+teamId));
    }
}
