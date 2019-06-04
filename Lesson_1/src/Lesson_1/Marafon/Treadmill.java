package Lesson_1.Marafon;

public class Treadmill extends Obstacle {
    int height;

    public Treadmill(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(height);
    }

}
