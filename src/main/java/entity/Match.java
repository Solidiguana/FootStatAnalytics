package entity;
import java.sql.Date;

public class Match extends BaseEntity {
    private int homeTeamId;
    private int awayTeamId;
    private Date matchDate;
    private String homeName;
    private String awayName;

    public Match(int id, String homeName, String awayName, Date matchDate) {
        super(id);
        this.homeName = homeName;
        this.awayName = awayName;
        this.matchDate = matchDate;
    }

    public Match(int homeTeamId, int awayTeamId, Date matchDate) {
        super();
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.matchDate = matchDate;
    }

    public int getHomeTeamId() { return homeTeamId; }
    public int getAwayTeamId() { return awayTeamId; }
    public Date getMatchDate() { return matchDate; }

    @Override
    public String toString() {
        if (homeName != null) return "ID: " + id + " | " + homeName + " vs " + awayName + " (" + matchDate + ")";
        return "Match: " + homeTeamId + " vs " + awayTeamId;
    }
}
