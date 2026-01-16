package repository;

import config.DatabaseConnection;
import entity.Match;
import repository.interfaces.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository implements IRepository<Match> {
    private Connection con = (Connection) DatabaseConnection.getInstance().getConnection();

    @Override
    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT m.id, m.match_date, t1.name as h, t2.name as a " +
                "FROM matches m " +
                "JOIN teams t1 ON m.home_team_id = t1.id " +
                "JOIN teams t2 ON m.away_team_id = t2.id";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                matches.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return matches;
    }

    @Override
    public Match findById(int id) {
        String sql = "SELECT m.id, m.match_date, t1.name as h, t2.name as a " +
                "FROM matches m " +
                "JOIN teams t1 ON m.home_team_id = t1.id " +
                "JOIN teams t2 ON m.away_team_id = t2.id WHERE m.id = ?";
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
    public boolean save(Match match) {
        try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO matches (home_team_id, away_team_id, match_date) VALUES (?, ?, ?)")) {
            pstmt.setInt(1, match.getHomeTeamId());
            pstmt.setInt(2, match.getAwayTeamId());
            pstmt.setDate(3, match.getMatchDate());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }


    public Match mapRow(ResultSet rs) throws SQLException {
        return new Match(
                rs.getInt("id"),
                rs.getString("h"),
                rs.getString("a"),
                rs.getDate("match_date")
        );
    }
}
