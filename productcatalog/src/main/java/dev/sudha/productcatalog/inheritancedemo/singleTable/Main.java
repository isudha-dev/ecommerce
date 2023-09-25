package dev.sudha.productcatalog.inheritancedemo.singleTable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Main implements CommandLineRunner {

    private MentorRepo mentorRepo;
    private UserRepo userRepo;

    public Main(@Qualifier("st_mr") MentorRepo mentorRepo, @Qualifier("st_ur") UserRepo userRepo){
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
