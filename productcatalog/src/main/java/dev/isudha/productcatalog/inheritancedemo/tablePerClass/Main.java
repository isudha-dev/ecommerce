package dev.isudha.productcatalog.inheritancedemo.tablePerClass;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class Main implements CommandLineRunner {

    private MentorRepo mentorRepo;
    private UserRepo userRepo;

    public Main(@Qualifier("tpc_mr") MentorRepo mentorRepo, @Qualifier("tpc_ur") UserRepo userRepo){
        // you use qualifier to specify which @Repository notation class to use
        // this is used when you have more than 1 repo with same name
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

        /*
            select u1_0.id,u1_0.clazz_,u1_0.email,u1_0.name,u1_0.average_rating,u1_0.attendance,u1_0.psp
            from
            (select id, email, name, null as average_rating, null as attendance, null as psp, 0 as clazz_ from tpc_user
            union all
            select id, email, name, average_rating, null as attendance, null as psp, 1 as clazz_ from tpc_mentor
            union all
            select id, email, name, null as average_rating, attendance, psp, 2 as clazz_ from tpc_student
            union all
            select id, email, name, average_rating, null as attendance, null as psp, 3 as clazz_ from tpc_ta) u1_0

        */
        // uses union all
        List<User> users = userRepo.findAll();
        for(User user1: users){
            System.out.println(user1);
        }

    }
}
