package dev.isudha.productcatalog.inheritancedemo.joinedTable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class Main implements CommandLineRunner {

    private MentorRepo mentorRepo;
    private UserRepo userRepo;

    public Main(@Qualifier("jt_mr") MentorRepo mentorRepo, @Qualifier("jt_ur") UserRepo userRepo){
        this.mentorRepo = mentorRepo;
        this.userRepo = userRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override public void run(final String... args) throws Exception {
        Mentor mentor = new Mentor();
        mentor.setName("Naman");
        mentor.setAverageRating(9.5);
        mentor.setEmail("naman@gmail.com");
        mentorRepo.save(mentor);

        User user = new User();
        user.setName("Sudha");
        user.setEmail("sudha@gmail.com");
        userRepo.save(user);

    }
}
