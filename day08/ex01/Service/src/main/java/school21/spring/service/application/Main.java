package school21.spring.service.application;

import school21.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findByEmail("HanSolo"));
        System.out.println(usersRepository.findById(2L));
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findByEmail("HanSolo"));
        System.out.println(usersRepository.findById(2L));
    }
}
