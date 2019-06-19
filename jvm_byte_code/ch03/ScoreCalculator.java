public class ScoreCalculator {
    public void record(double score) {
    }

    public double getAverage() {
        return 0;
    }

public static void main(String[] args) {
    ScoreCalculator calculator = new ScoreCalculator();

    int score1 = 1;
    int score2 = 2;

    calculator.record(score1);
    calculator.record(score2);

    double avg = calculator.getAverage();
} 
}
