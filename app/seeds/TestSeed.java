package seeds;

import models.events.News;
import models.events.Project;
import models.events.Talk;
import models.users.Campus;
import models.users.Profile;
import models.users.Promotion;
import models.users.User;
import org.joda.time.LocalDate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class TestSeed {

    public static void insert() {

        User user1 = new User();
        user1.idBooster = "8080";
        user1.firstName = "John";
        user1.lastName = "Doe";
        user1.campus = Campus.PARIS;
        user1.promotion = Promotion.B3;
        user1.profile = Profile.MEMBER;
        user1.save();

        User user2 = new User();
        user2.idBooster = "75054";
        user2.firstName = "Lukasz";
        user2.lastName = "Piliszczuk";
        user2.campus = Campus.PARIS;
        user2.promotion = Promotion.M1;
        user2.profile = Profile.GLM;
        user2.save();

        User user3 = new User();
        user3.idBooster = "7777";
        user3.firstName = "Kevin";
        user3.lastName = "Valfin";
        user3.campus = Campus.PARIS;
        user3.promotion = Promotion.B3;
        user3.profile = Profile.CLM;
        user3.save();

        User user4 = new User();
        user4.idBooster = "123";
        user4.firstName = "Candidate 1";
        user4.lastName = "Foo";
        user4.campus = Campus.PARIS;
        user4.promotion = Promotion.B2;
        user4.profile = Profile.CANDIDATE;
        user4.save();

        User user5 = new User();
        user5.idBooster = "456";
        user5.firstName = "Candidate 2";
        user5.lastName = "Bar";
        user5.campus = Campus.PARIS;
        user5.promotion = Promotion.B1;
        user5.profile = Profile.CANDIDATE;
        user5.save();

        Set<User> users = new HashSet<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        News news1 = new News();
        news1.title = "Foo";
        news1.content = "Bar";
        news1.authors = users;
        news1.save();

        Talk talk1 = new Talk();
        talk1.title = "Google map with Android";
        talk1.description = "Introduction to google map foobar";
        talk1.date = new LocalDate(2011, 12, 23); // december 23, 2011
        talk1.speakers = users;
        talk1.save();

        Project project1 = new Project();
        project1.name = "Java lab. website";
        project1.description = "The new website for the lab";
        project1.members = users;
        project1.save();
    }
}
