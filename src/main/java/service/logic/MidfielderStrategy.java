package service.logic;

import service.logic.factory.interfaces.RatingStrategy;

public class MidfielderStrategy implements RatingStrategy {
    @Override
    public double calculate(int goals, int assists, int minutes) {
        double score = 6.0;

        score += (goals * 0.7);
        score += (assists * 1.0);

        if (minutes > 60) score += 0.5;

        if (minutes < 30) score -= 0.5;

        return Math.min(score, 10.0);
    }
}
