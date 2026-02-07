package service;

import entity.Match;
import entity.Player;
import entity.PlayerStat;
import entity.Team;
import repository.interfaces.IMatchRepository;
import repository.interfaces.IPlayerRepository;
import repository.interfaces.IStatRepository;
import repository.interfaces.ITeamRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PublicService {
    private final IPlayerRepository playerRepo;
    private final ITeamRepository teamRepo;
    private final IMatchRepository matchRepo;
    private final IStatRepository statRepo;

    public PublicService(IPlayerRepository p, ITeamRepository t, IMatchRepository m, IStatRepository s) {
        this.playerRepo = p;
        this.teamRepo = t;
        this.matchRepo = m;
        this.statRepo = s;
    }

    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }

    public List<PlayerStat> getAllStats() {
        return statRepo.findAll();
    }

    public List<Player> getPlayersByTeamFiltered(String teamName) {

        List<Player> allPlayers = playerRepo.findAll();

        List<Player> filteredList = new ArrayList<>();

        for (Player p : allPlayers) {
            if (p.getTeamName() != null &&
                    p.getTeamName().equalsIgnoreCase(teamName)) {
                filteredList.add(p);
            }
        }

        return filteredList;
    }

    public Player getPlayerById(int id) {
        return playerRepo.findById(id);
    }

    public Team getTeamById(int id) {
        return teamRepo.findById(id);
    }

    public Match getMatchById(int id) {
        return matchRepo.findById(id);
    }

    public PlayerStat getStatById(int id) {
        return statRepo.findById(id);
    }

    public List<Player> getPlayersByPosition(String position) {
        List<Player> allPlayers = playerRepo.findAll();
        List<Player> filteredList = new ArrayList<>();

        for (Player p : allPlayers) {
            if (p.getPosition() != null &&
                    p.getPosition().equalsIgnoreCase(position)) {
                filteredList.add(p);
            }
        }
        return filteredList;
    }

    public Map<String, List<Team>> getTeamsGroupedByLeague() {
        List<Team> allTeams = teamRepo.findAll();

        return allTeams.stream()
                .collect(Collectors.groupingBy(team ->
                        team.getLeagueName() != null ? team.getLeagueName() : "No League"
                ));
    }

    public List<Player> getTopScorers(int topN) {
        List<PlayerStat> allStats = statRepo.findAll();
        Map<Integer, Integer> goalsMap = new HashMap<>();

        for (PlayerStat stat : allStats) {
            goalsMap.put(stat.getPlayerId(),
                    goalsMap.getOrDefault(stat.getPlayerId(), 0) + stat.getGoals());
        }

        List<Integer> topPlayerIds = goalsMap.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(topN)
                .map(Map.Entry::getKey)
                .toList();

        List<Player> topPlayers = new ArrayList<>();
        for (int pid : topPlayerIds) {
            Player player = playerRepo.findById(pid);
            if (player != null) topPlayers.add(player);
        }

        return topPlayers;
    }

    public List<PlayerStat> getStatsByPlayer(int playerId) {
        return statRepo.findAll().stream()
                .filter(stat -> stat.getPlayerId() == playerId)
                .collect(Collectors.toList());
    }
}
