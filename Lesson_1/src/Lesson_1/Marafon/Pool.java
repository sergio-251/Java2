package Lesson_1.Marafon;

public class Pool extends Obstacle {
    int height;

    public Pool(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(height);
    }
}
