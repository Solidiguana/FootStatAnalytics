package service.logic;

import service.logic.factory.interfaces.RatingStrategy;

public class AttackerStrategy implements RatingStrategy {
    @Override
    public double calculate(int goals, int assists, int minutes) {
        double score = 6.0 + (goals * 1.0) + (assists * 0.8);

        if (minutes < 20) score -= 1.0;

        return Math.min(score, 10.0);
    }
}