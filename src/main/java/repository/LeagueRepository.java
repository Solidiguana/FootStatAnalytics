package repository;

import config.DatabaseConnection;
import entity.League;
import repository.interfaces.ILeagueRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeagueRepository implements ILeagueRepository {
    private Connection con = DatabaseConnection.getInstance().getConnection();

    @Override
    public League mapRow(ResultSet rs) throws SQLException {
        return new League(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
    }

    @Override
    public boolean save(League entity) {
        String sql = "INSERT INTO leagues (name, country) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getCountry());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<League> findAll() {
        List<League> list = new ArrayList<>();
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM leagues")) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public League findById(int id) {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM leagues WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}