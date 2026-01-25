package service;

import entity.Player;
import entity.PlayerStat;
import repository.interfaces.IPlayerRepository;
import repository.interfaces.IStatRepository;
import service.logic.StrategyFactory;
import service.logic.interfaces.RatingStrategy;

public class StatService {
    private final IStatRepository statRepo;
    private final IPlayerRepository playerRepo;

    public StatService(IStatRepository statRepo, IPlayerRepository playerRepo) {
        this.statRepo = statRepo;
        this.playerRepo = playerRepo;
    }

    public String processStats(int pid, int mid, int goals, int assists, int minutes) {
        if (minutes < 0 || minutes > 120) return "ERROR: impossible minutes";
        if (goals < 0 || assists < 0) return "ERROR: negative goals or assists";

        Player player = playerRepo.findById(pid);
        if (player == null) return "ERROR: Player not found";

        RatingStrategy strategy = StrategyFactory.getStrategy(player.getPosition());

        double rating = strategy.calculate(goals, assists, minutes);

        PlayerStat stat = new PlayerStat(pid, mid, goals, assists, minutes, rating);
        return statRepo.save(stat) ? String.format("Saved! Rating: %.2f", rating) : "DB error";
    }
}