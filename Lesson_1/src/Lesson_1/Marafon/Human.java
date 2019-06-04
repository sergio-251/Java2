package Lesson_1.Marafon;

public class Human implements Competitor {


    String name;

    private int maxRunDistance;
    private int maxJumpHeight;
    private int maxSwimDistance;

    private boolean active = true;
    private  boolean doIt = false; // Флаг участия в соревнованиях

    @Override
    public boolean isOnDistance() {
        return active;
    }

    // Конструктор для всего набора передаваемых аргументов
    public Human(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
    }

    public Human(String name) {
        this.name = name;
        this.maxRunDistance = 5000;
        this.maxJumpHeight = 30;
        this.maxSwimDistance = 200;
        this.active = true;
    }

    @Override
    public void run(int dist) {
        doIt = true;
        if (dist <= maxRunDistance) {
            System.out.println(name + " хорошо справился с кроссом");
        } else {
            System.out.println(name + " не справился с кроссом");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        doIt = true;
        if (height <= maxJumpHeight) {
            System.out.println(name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(name + " не смог перепрыгнуть стену");
            active = false;
        }
    }

    @Override
    public void swim(int dist) {
        doIt = true;
        if (dist <= maxSwimDistance) {
            System.out.println(name + " отлично проплыл");
        } else {
            System.out.println(name + " не смог проплыть");
            active = false;
        }
    }

    @Override
    public void info() {
        if(doIt) {
            String activeResult = (this.active) ? "" : "не";
            System.out.println(name + " - " + activeResult + " прошел(ла) испытания!");
        }
        else {
            System.out.println(name + " пока не участвовал(а) в соревнованиях!");
        }
    }

    @Override
    public void personalInfo() {
        System.out.println(name + " имеет следующие характеристики: плавает на " + maxSwimDistance + " м; прыгает на "
                + maxJumpHeight + " м; бегает на " +  maxRunDistance + " м.");
    }



}