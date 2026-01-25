package repository;

import config.DatabaseConnection;
import entity.PlayerStat;
import repository.interfaces.IStatRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatRepository implements IStatRepository {
    private Connection con = DatabaseConnection.getInstance().getConnection();

    @Override
    public boolean save(PlayerStat stat) {
        String sql = "INSERT INTO player_stats (player_id, match_id, goals, assists, minutes_played, rating) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, stat.getPlayerId());
            pstmt.setInt(2, stat.getMatchId());
            pstmt.setInt(3, stat.getGoals());
            pstmt.setInt(4, stat.getAssists());
            pstmt.setInt(5, stat.getMinutes());
            pstmt.setDouble(6, stat.getRating());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<PlayerStat> findAll() {
        List<PlayerStat> stats = new ArrayList<>();
        String sql = "SELECT ps.*, p.name as player_name, t1.name as home, t2.name as away " +
                "FROM player_stats ps " +
                "JOIN players p ON ps.player_id = p.id " +
                "JOIN matches m ON ps.match_id = m.id " +
                "JOIN teams t1 ON m.home_team_id = t1.id " +
                "JOIN teams t2 ON m.away_team_id = t2.id";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                stats.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return stats;
    }

    @Override
    public PlayerStat findById(int id) {
        String sql = "SELECT ps.*, p.name as player_name, t1.name as home, t2.name as away " +
                "FROM player_stats ps " +
                "JOIN players p ON ps.player_id = p.id " +
                "JOIN matches m ON ps.match_id = m.id " +
                "JOIN teams t1 ON m.home_team_id = t1.id " +
                "JOIN teams t2 ON m.away_team_id = t2.id " +
                "WHERE ps.id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public PlayerStat mapRow(ResultSet rs) throws SQLException {
        PlayerStat ps = new PlayerStat(
                rs.getInt("player_id"),
                rs.getInt("match_id"),
                rs.getInt("goals"),
                rs.getInt("assists"),
                rs.getInt("minutes_played"),
                rs.getDouble("rating")
        );
        ps.setId(rs.getInt("id"));
        return ps;
    }
}