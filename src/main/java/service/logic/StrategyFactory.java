package service.logic;

import service.logic.interfaces.RatingStrategy;

public class StrategyFactory {

    public static RatingStrategy getStrategy(String position) {
        if (position == null) return new DefenderStrategy();

        switch (position.trim().toUpperCase()) {
            case "FORWARD":
                return new AttackerStrategy();

            case "MIDFIELDER":
                return new MidfielderStrategy();

            case "DEFENDER":
            case "GOALKEEPER":
                return new DefenderStrategy();

            default:
                return new MidfielderStrategy();
        }
    }
}