package Lesson_1.Marafon;

public class Animal implements Competitor {
    String type;
    String name;

    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;

    boolean onDistance;
    boolean doIt = false;  // Флаг участия в соревнованиях

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    public Animal(String type, String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    @Override
    public void run(int dist) {
        doIt = true;
        if (dist <= maxRunDistance) {
            System.out.println(type + " " + name + " хорошо справился с кроссом");
        } else {
            System.out.println(type + " " + name + " не справился с кроссом");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        doIt = true;
        if (height <= maxJumpHeight) {
            System.out.println(type + " " + name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(type + " " + name + " не смог перепрыгнуть стену");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist) {
        doIt = true;
        if (maxSwimDistance == 0) {
            System.out.println(type + " " + name + " не умеет плавать");
            onDistance = false;
            return;
        }
        if (dist <= maxSwimDistance) {
            System.out.println(type + " " + name + " отлично проплыл");
        } else {
            System.out.println(type + " " + name + " не смог проплыть");
            onDistance = false;
        }
    }

    @Override
    public void info() {
        if(doIt) {
            String activeResult = (this.onDistance) ? "" : "не";
            System.out.println(type + " " + name + " - " + activeResult + " прошел(ла) испытания!");
        }
        else {
            System.out.println(type + " " + name + " пока не участвовал(а) в соревнованиях!");
        }
    }

    @Override
    public void personalInfo() {
        System.out.println(type + " " + name + " имеет следующие характеристики: плавает на " + maxSwimDistance + " м; прыгает на "
                + maxJumpHeight + " м; бегает на " +  maxRunDistance + " м.");
    }
}