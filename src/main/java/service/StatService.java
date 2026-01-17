package service;

import entity.PlayerStat;
import repository.StatRepository;

public class StatService {
    private StatRepository statRepo = new StatRepository();

    public String processStats(int pid, int mid, int goals, int assists, int minutes) {
        if (minutes < 0 || minutes > 120) return "ERROR: impossible minutes";
        if (goals < 0 || assists < 0) return "ERROR: negative goals or assists";

        double rating = 6.0 + (goals * 1.0) + (assists * 0.5);
        if (minutes < 15) rating -= 0.5;
        if (rating > 10.0) rating = 10.0;

        PlayerStat stat = new PlayerStat(pid, mid, goals, assists, minutes, rating);
        return statRepo.save(stat) ? String.format("Saved! Rating: %.2f", rating) : "DB error";
    }
}