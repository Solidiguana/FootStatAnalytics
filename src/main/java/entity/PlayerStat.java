package entity;

public class PlayerStat extends BaseEntity {
    private int playerId;
    private int matchId;
    private int goals;
    private int assists;
    private int minutes;
    private double rating;

    public PlayerStat(int id, int playerId, int matchId, int goals, int assists, int minutes, double rating) {
        super(id);
        this.playerId = playerId;
        this.matchId = matchId;
        this.goals = goals;
        this.assists = assists;
        this.minutes = minutes;
        this.rating = rating;
    }

    public PlayerStat(int playerId, int matchId,
                      int goals, int assists, int minutes, double rating) {
        this(0, playerId, matchId, goals, assists, minutes, rating);
    }

    @Override
    public String toString() {
        return String.format("Stat ID: %d | Match: %d | Player: %d | Goals: %d | Assists: %d | Rating: %.2f",
                getId(), matchId, playerId, goals, assists, rating);
    }

    public int getPlayerId() { return playerId; }
    public int getMatchId() { return matchId; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
    public int getMinutes() { return minutes; }
    public double getRating() { return rating; }
}
