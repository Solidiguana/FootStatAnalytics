package repository;

import config.DatabaseConnection;
import entity.Player;
import repository.interfaces.IPlayerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository implements IPlayerRepository {
    private Connection con = DatabaseConnection.getInstance().getConnection();

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.position, t.name as team_name FROM players p JOIN teams t ON p.team_id = t.id";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                players.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return players;
    }

    @Override
    public boolean save(Player player) {
        try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO players (name, position, team_id) VALUES (?, ?, ?)")) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getPosition());
            pstmt.setInt(3, player.getTeamId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public Player findById(int id) {
        String sql = "SELECT p.id, p.name, p.position, t.name as team_name FROM players p JOIN teams t ON p.team_id = t.id WHERE p.id = ?";
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
    public Player mapRow(ResultSet rs) throws SQLException {
        return new Player(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("position"),
                rs.getString("team_name")
        );
    }

    @Override
    public List<Player> findByTeam(int teamId) {
        List<Player> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.position, t.name as team_name FROM players p JOIN teams t ON p.team_id = t.id WHERE p.team_id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Player> findByPosition(String position) {
        List<Player> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.position, t.name as team_name FROM players p JOIN teams t ON p.team_id = t.id WHERE p.position=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, position);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean updatePlayerTeam(int playerId, int newTeamId) {
        String sql = "UPDATE players SET team_id = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newTeamId);
            ps.setInt(2, playerId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}