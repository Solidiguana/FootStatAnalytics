package service;

import entity.Match;
import entity.Player;
import entity.Team;
import repository.interfaces.IMatchRepository;
import repository.interfaces.IPlayerRepository;
import repository.interfaces.ITeamRepository;

import java.sql.Date;
import java.util.List;

public class AdminService {
    private final ITeamRepository teamRepo;
    private final IPlayerRepository playerRepo;
    private final IMatchRepository matchRepo;

    public AdminService(ITeamRepository t, IPlayerRepository p, IMatchRepository m) {
        this.teamRepo = t;
        this.playerRepo = p;
        this.matchRepo = m;
    }

    public String createTeam(String name, String city, int leagueId) {

        List<Team> existingTeams = teamRepo.findAll();

        boolean exists = existingTeams.stream().anyMatch(t -> t.getName().equalsIgnoreCase(name));

        if (exists) {
            return "Error: Team '" + name + "' already exists.";
        }
        try {
            Team newTeam = new Team(name, city, leagueId);
            return teamRepo.save(newTeam) ? "Team created successfully." : "Error: Database failure.";
        } catch (IllegalArgumentException e) {
            return "Error: Invalid leagueId.";
        }
    }

    public String createPlayer(String name, String pos, int teamId) {

        Team team = teamRepo.findById(teamId);

        if (team == null) {
            return "Error: Team with ID " + teamId + " not found.";
        }
        try {
            Player newPlayer = new Player(name, pos, teamId);
            return playerRepo.save(newPlayer) ? "Player created successfully." : "Error: Database failure.";
        } catch (IllegalArgumentException e) {
            return "Error: Invalid teamId.";
        }
    }

    public String createMatch(int homeId, int awayId, String dateStr) {

        if (homeId == awayId) {
            return "Error: Teams cannot play against themselves.";
        }

        if (teamRepo.findById(homeId) == null) {
            return "Error: Home team (ID " + homeId + ") does not exist.";
        }
        if (teamRepo.findById(awayId) == null) {
            return "Error: Away team (ID " + awayId + ") does not exist.";
        }

        try {
            Date date = Date.valueOf(dateStr);
            Match match = new Match(homeId, awayId, date);
            return matchRepo.save(match) ? "Match scheduled successfully." : "Error: Database failure.";
        } catch (IllegalArgumentException e) {
            return "Error: Invalid Date Format (required: yyyy-mm-dd).";
        }
    }
}