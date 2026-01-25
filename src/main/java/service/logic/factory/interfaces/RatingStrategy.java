package service.logic.factory.interfaces;

public interface RatingStrategy {
    double calculate(int goals, int assists, int minutes);
}