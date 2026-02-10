package repository.interfaces;

import entity.Player;
import java.util.List;

public interface IPlayerRepository extends IRepository<Player> {
    List<Player> findByTeam(int teamId);
    List<Player> findByPosition(String position);
    boolean updatePlayerTeam(int playerId, int newTeamId);
}