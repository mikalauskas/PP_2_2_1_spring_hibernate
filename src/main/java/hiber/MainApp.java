package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",
              new Car("BMW", 2436)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",
              new Car("Chuwi", 345345)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",
              new Car("Lexus", 232534)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",
              new Car("BMW", 26456)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      Car car = new Car("Ford", 3252);
      User user = new User("Ruslan", "M", "ruslan@mail.ru", car);

      userService.add(user);

      System.out.println(userService.getUserByCar(car).getId());
      System.out.println(userService.getUserByCar("Ford", 3252).getId());

      context.close();
   }
}
