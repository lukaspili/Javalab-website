package seeds;

import java.util.HashSet;
import java.util.Set;

import models.events.Article;
import models.events.Project;
import models.events.Talk;
import models.users.Campus;
import models.users.Profile;
import models.users.Promotion;
import models.users.User;

import org.joda.time.LocalDate;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class TestSeed {

    public static void insert() {

        Campus paris = new Campus();
        paris.name = Campus.Name.PARIS;
        paris.save();

        Campus grenoble = new Campus();
        grenoble.name = Campus.Name.GRENOBLE;
        grenoble.save();

        Campus toulouse = new Campus();
        toulouse.name = Campus.Name.TOULOUSE;
        toulouse.save();

        Campus clermont = new Campus();
        clermont.name = Campus.Name.CLERMONTFERRAND;
        clermont.save();

        Campus montpellier = new Campus();
        montpellier.name = Campus.Name.MONTPELLIER;
        montpellier.save();

        User user1 = new User();
        user1.idBooster = "8080";
        user1.firstName = "John";
        user1.lastName = "Doe";
        user1.campus = montpellier;
        user1.promotion = Promotion.B3;
        user1.profile = Profile.MEMBER;
        user1.save();

        User user2 = new User();
        user2.idBooster = "75054";
        user2.firstName = "Lukasz";
        user2.lastName = "Piliszczuk";
        user2.campus = montpellier;
        user2.promotion = Promotion.M1;
        user2.profile = Profile.GLM;
        user2.save();

        User user3 = new User();
        user3.idBooster = "92755";
        user3.firstName = "Kevin";
        user3.lastName = "Valfin";
        user3.campus = paris;
        user3.promotion = Promotion.B3;
        user3.profile = Profile.CLM;
        user3.save();

        User user4 = new User();
        user4.idBooster = "123";
        user4.firstName = "Candidate 1";
        user4.lastName = "Foo";
        user4.campus = paris;
        user4.promotion = Promotion.B2;
        user4.profile = Profile.CANDIDATE;
        user4.save();

        User user5 = new User();
        user5.idBooster = "456";
        user5.firstName = "Candidate 2";
        user5.lastName = "Bar";
        user5.campus = paris;
        user5.promotion = Promotion.B1;
        user5.profile = Profile.CANDIDATE;
        user5.save();

        User user51 = new User();
        user51.idBooster = "456";
        user51.firstName = "Candidate 2";
        user51.lastName = "Bar";
        user51.campus = paris;
        user51.promotion = Promotion.B1;
        user51.profile = Profile.CANDIDATE;
        user51.save();

        User user511 = new User();
        user511.idBooster = "45624";
        user511.firstName = "Candidate 3";
        user511.lastName = "Bar";
        user511.campus = montpellier;
        user511.promotion = Promotion.B1;
        user511.profile = Profile.CANDIDATE;
        user511.save();

        User user5111 = new User();
        user5111.idBooster = "456565";
        user5111.firstName = "Candidate 4";
        user5111.lastName = "Bar";
        user5111.campus = montpellier;
        user5111.promotion = Promotion.B1;
        user5111.profile = Profile.MEMBER;
        user5111.save();

        User user51111 = new User();
        user51111.idBooster = "45645";
        user51111.firstName = "Candidate 5";
        user51111.lastName = "Bar";
        user51111.campus = grenoble;
        user51111.promotion = Promotion.B1;
        user51111.profile = Profile.MEMBER;
        user51111.save();

        User user511111 = new User();
        user511111.idBooster = "45612";
        user511111.firstName = "Candidate 6";
        user511111.lastName = "Bar";
        user511111.campus = montpellier;
        user511111.promotion = Promotion.B1;
        user511111.profile = Profile.MEMBER;
        user511111.save();

        User user5111111 = new User();
        user5111111.idBooster = "45634";
        user5111111.firstName = "Candidate 7";
        user5111111.lastName = "Bar";
        user5111111.campus = toulouse;
        user5111111.promotion = Promotion.B1;
        user5111111.profile = Profile.MEMBER;
        user5111111.save();

        User user51111111 = new User();
        user51111111.idBooster = "45667";
        user51111111.firstName = "Candidate 8";
        user51111111.lastName = "Bar";
        user51111111.campus = montpellier;
        user51111111.promotion = Promotion.B1;
        user51111111.profile = Profile.CANDIDATE;
        user51111111.save();

        User user511111111 = new User();
        user511111111.idBooster = "45609";
        user511111111.firstName = "Candidate 9";
        user511111111.lastName = "Bar";
        user511111111.campus = clermont;
        user511111111.promotion = Promotion.B1;
        user511111111.profile = Profile.CANDIDATE;
        user511111111.save();

        User user5111111111 = new User();
        user5111111111.idBooster = "45697";
        user5111111111.firstName = "Candidate 10";
        user5111111111.lastName = "Bar";
        user5111111111.campus = clermont;
        user5111111111.promotion = Promotion.B1;
        user5111111111.profile = Profile.CANDIDATE;
        user5111111111.save();


        Set<User> users = new HashSet<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);


        Talk talk1 = new Talk();
        talk1.title = "Google map with Android";
        talk1.description = "Introduction to google map foobar";
        talk1.date = new LocalDate(2011, 12, 23); // december 23, 2011
        talk1.speakers = user1;
        talk1.save();
        
        Article article211111 = new Article();
        article211111.title = "Foorgeazgrzrr";
        article211111.content = "Baagaergaergaergaeg eraggggggg rgaeeeeeeeeeee geraaaaaaaaar";
        article211111.creationDate = new LocalDate(2011, 12, 23); // december 23, 2011
        article211111.author = user1;
        article211111.save();
        
        Article article2111111 = new Article();
        article2111111.title = "Foorgeazgrzrr";
        article2111111.content = "Baagaergaergaergaeg eraggggggg rgaeeeeeeeeeee geraaaaaaaaar";
        article2111111.creationDate = new LocalDate(2012, 07, 10); // december 23, 2011
        article2111111.author = user1;
        article2111111.save();

        Project project1 = new Project();
        project1.name = "Java lab. website";
        project1.description = "The new website for the lab";
        project1.members = users;
        project1.campus = paris;
        project1.save();
        
        Project SupinfoMarks = new Project(); 
        SupinfoMarks.name = "Supinfo Marks";
        SupinfoMarks.description = "Un projet du Labo java";
        SupinfoMarks.members = users;
        SupinfoMarks.campus = paris;
        SupinfoMarks.save();
    }
}
