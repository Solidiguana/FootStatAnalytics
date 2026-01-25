package repository.interfaces;

import entity.Team;
import java.util.List;

public interface ITeamRepository extends IRepository<Team> {
    List<Team> findByLeague(int leagueId);
}