package repository;

import config.DatabaseConnection;
import entity.Team;
import repository.interfaces.ITeamRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository implements ITeamRepository {
    private Connection con = DatabaseConnection.getInstance().getConnection();

    @Override
    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT t.*, l.name as league_name FROM teams t LEFT JOIN leagues l ON t.league_id = l.id";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                teams.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return teams;
    }

    @Override
    public boolean save(Team team) {
        String sql = "INSERT INTO teams (name, city, league_id) VALUES (?,?,?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, team.getName());
            pstmt.setString(2, team.getCity());
            pstmt.setInt(3, team.getLeagueID());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public Team findById(int id) {
        String sql = "SELECT t.*, l.name as league_name FROM teams t LEFT JOIN leagues l ON t.league_id = l.id WHERE t.id=?";
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
    public Team mapRow(ResultSet rs) throws SQLException {
        return new Team(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("city"),
                rs.getString("league_name")
        );
    }

    @Override
    public List<Team> findByLeague(int leagueId) {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT t.*, l.name as league_name FROM teams t LEFT JOIN leagues l ON t.league_id = l.id WHERE t.league_id=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, leagueId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) teams.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return teams;
    }
}

