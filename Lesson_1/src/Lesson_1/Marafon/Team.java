package Lesson_1.Marafon;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Team {

    private String name;
    Competitor[] members = new Competitor[4];

    public Team(String name) {
        this.name = name;
        members[0] = new Human("Савелий", 1000, 25, 200);

        members[1] = new Human("Зина", 600, 12, 150);

        members[2] = new Cat("Мурка"); // Параметры оставил по умолчанию в самом классе, хотя можно
        // передавать здесь, чтобы создавать разных кошек...

        members[3] = new Dog("Шарик"); // То же самое


    }

    //Метод выводящий информацию об участниках, кто удачно прошел препятсвия
    public void getMembersInfo() {
        System.out.println("Сводная информация об участниках команды \"" + name + "\":");
        for(int i = 0; i < members.length; i++){
            members[i].info();
        }
     }

}
