package Lesson_1.Marafon;

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Наша команда");
        Course course = new Course(team);
        team.getMembersInfo(); // Выводим информацию об участниках - если вызвать до соревнований,
        // то будут соответсвующие сообщения
        course.doIt(team); // Инициализипуем прохождение препятсвий
        team.getMembersInfo(); // Выводим информацию об участниках, которые удачно прошли испытания

        team.members[0].personalInfo(); // Так можно обратиться за персональной информацией каждого участника соревнований
        team.members[3].personalInfo();


        //Obstacle[] course = {new Cross(80), new Wall(2), new Wall(1), new Cross(120)};

    }
}