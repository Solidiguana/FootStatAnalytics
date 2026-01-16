package repository;

import config.DatabaseConnection;
import entity.Player;
import repository.interfaces.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository implements IRepository<Player> {
    private Connection con = (Connection) DatabaseConnection.getInstance().getConnection();

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
                rs.getString("team_name") // Берет данные из алиаса JOIN-а
        );
    }
}