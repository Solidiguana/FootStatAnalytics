package repository;

import config.DatabaseConnection;
import entity.Team;
import repository.interfaces.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository implements IRepository<Team> {
    private Connection con = (Connection) DatabaseConnection.getInstance().getConnection();

    @Override
    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                teams.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return teams;
    }

    @Override
    public boolean save(Team team) {
        String sql = "INSERT INTO teams (name, city) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, team.getName());
            pstmt.setString(2, team.getCity());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public Team findById(int id) {
        String sql = "SELECT * FROM teams WHERE id = ?";
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
                rs.getString("city")
        );
    }
}

