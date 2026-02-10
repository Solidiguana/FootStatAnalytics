package service;

import entity.League;
import entity.Match;
import entity.Player;
import entity.Team;
import repository.interfaces.ILeagueRepository;
import repository.interfaces.IMatchRepository;
import repository.interfaces.IPlayerRepository;
import repository.interfaces.ITeamRepository;

import java.sql.Date;
import java.util.List;

public class AdminService {
    private final ITeamRepository teamRepo;
    private final IPlayerRepository playerRepo;
    private final IMatchRepository matchRepo;
    private final ILeagueRepository leagueRepo;

    public AdminService(ITeamRepository t, IPlayerRepository p, IMatchRepository m, ILeagueRepository l) {
        this.teamRepo = t;
        this.playerRepo = p;
        this.matchRepo = m;
        this.leagueRepo = l;
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

    public String createLeague(String name, String country) {
        // Validation: Check if league exists (simple check by name using Stream)
        boolean exists = leagueRepo.findAll().stream()
                .anyMatch(l -> l.getName().equalsIgnoreCase(name));

        if (exists) return "Error: League already exists.";

        League newLeague = new League(name, country);
        return leagueRepo.save(newLeague) ? "League Created Successfully." : "Error: DB Failure.";
    }

    public String transferPlayer(int playerId, int newTeamId) {
        Player player = playerRepo.findById(playerId);
        Team team = teamRepo.findById(newTeamId);
        if (player == null) {
            return "Error: Player with ID " + playerId + " not found.";
        }
        if (teamRepo.findById(newTeamId) == null) {
            return "Error: Team with ID " + newTeamId + " not found.";
        }
        if (player.getTeamId() == newTeamId) {
            return "Error: Player is already in this team.";
        }
        boolean success = playerRepo.updatePlayerTeam(playerId, newTeamId);
        if (success) {
            return "Success: " + player.getName() + " transferred to " + team.getName() + ".";
        } else {
            return "Error: Database update failed.";
        }
    }
}