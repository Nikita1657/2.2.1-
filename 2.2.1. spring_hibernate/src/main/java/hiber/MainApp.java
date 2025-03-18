package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User NIK = new User("Никита", "Костин", "nikitakostin@mail.io");
      User PETR = new User("Петр", "Борт", "BORTMACHANIK@mail.io");
      User OLEG = new User("Олег", "Монгол", "nizhnii@mail.io");
      User GLAD = new User("Денис", "Петров", "glad@mail.io");

      Car volvo = new Car("Volvo", 1);
      Car bmw = new Car("BMW", 2);
      Car suzuki = new Car("Sisuki", 3);
      Car lada = new Car("Ladaa", 4);
      Car bentley = new Car("Bentley", 228);
      Car cherry = new Car("Cherry", 14);

      userService.add(NIK.setCar(volvo).setUser(NIK));
      userService.add(PETR.setCar(bmw).setUser(PETR));
      userService.add(OLEG.setCar(suzuki).setUser(OLEG));
      userService.add(GLAD.setCar(bentley).setUser(GLAD));
      userService.add(GLAD.setCar(cherry).setUser(GLAD));

      // poisk user with car
      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      // drop user po seerir and model
      System.out.println(userService.getUserByCar("BMW", 2));

      // net user
      try {
         User notFoundUser = userService.getUserByCar("LUAZ", 42);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();

   }
}
