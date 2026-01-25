package service.logic.interfaces;

public interface RatingStrategy {
    double calculate(int goals, int assists, int minutes);
}