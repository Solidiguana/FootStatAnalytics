package service.logic;

import service.logic.factory.interfaces.RatingStrategy;

public class DefenderStrategy implements RatingStrategy {
    @Override
    public double calculate(int goals, int assists, int minutes) {
        double score = 6.0;

        if (minutes >= 80) score += 1.0;

        score += (goals * 0.5) + (assists * 0.5);

        return Math.min(score, 10.0);
    }
}