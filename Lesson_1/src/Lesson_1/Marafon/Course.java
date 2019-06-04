package Lesson_1.Marafon;

public class Course extends Obstacle {
    Obstacle obstacle[] = new Obstacle[3];
    Team team;


    //Через конструктор создаем массив из трех препятствий
    public Course(Team team) {
        this.team = team; // создаем ссылку на команду

        obstacle[0] = new Wall(20); // Создаем препятствие "Стена" - высоту задаем статически через аргумент

        obstacle[1] = new Pool(150); // Создаем препятствие "Бассейн" - длину дистанции задаем статически
        // через аргумент

        obstacle[2] = new Treadmill(250); // Создаем препятствие "Беговая дорожка" - длину дистанции задаем
        // статически через аргумент
    }

    // Прохождение полосы командой
    @Override
    public void doIt(Competitor competitor) {
       // Будет использоваться перегруженный метод (см. ниже)
    }


    //Метод, используемый при прохождении препятсвий командой
    public void doIt(Team team) {
        for (int i = 0; i < team.members.length; i++) {
            for (int k = 0; k < obstacle.length; k++) {
                obstacle[k].doIt(team.members[i]);
            }
            System.out.println();
        }
    }
}
