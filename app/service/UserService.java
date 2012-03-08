package service;

import models.users.Campus;
import models.users.Profile;
import models.users.User;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserService {

    public List<User> getCandidatesByCampus(Campus campus) {

        Query query = User.em().createQuery("select u from User u " +
                "where u.profile = :profile and u.campus = :campus");

        query.setParameter("profile", Profile.CANDIDATE);
        query.setParameter("campus", campus);

        return query.getResultList();
    }

//    public User create(User user) {
//
//        User userToCreate = new User();
//        userToCreate.firstName = user.firstName;
//
//        userToCreate.save();
//
//        return userToCreate;
//    }

    public User save(User user) {

        if (null != User.find("byIdBooster", user.idBooster).first()) {
            return null;
        }

        User userToCreate = new User();

        userToCreate.idBooster = user.idBooster;
        userToCreate.firstName = user.firstName;
        userToCreate.lastName = user.lastName;
        userToCreate.campus = user.campus;
        userToCreate.promotion = user.promotion;
        userToCreate.firstLogin = true;

        userToCreate.profile = Profile.CANDIDATE;

        userToCreate.save();

        return userToCreate;
    }

    public List<User> getMembersByCampus(Campus campus) {

        Query query = User.em().createQuery("select u from User u " +
                "where u.profile = :profile and u.campus = :campus");

        query.setParameter("profile", Profile.MEMBER);
        query.setParameter("campus", campus);

        return query.getResultList();
    }

    public List<User> getUsersByCampusAndProfile(Campus campus, Profile profile) {

        return User.em().createQuery("select u from User u " +
                "where u.campus = :campus and u.profile = :profile " +
                "order by u.lastName")
                .setParameter("campus", campus)
                .setParameter("profile", profile)
                .getResultList();
    }

    public User login(String idBooster) {
        return User.find("byIdBooster", idBooster).first();
    }

    public User completeFirstLogin(User existingUser, User dataUser) {

        existingUser.firstName = dataUser.firstName;
        existingUser.lastName = dataUser.lastName;
        existingUser.campus = dataUser.campus;
        existingUser.promotion = dataUser.promotion;
        existingUser.presentation = dataUser.presentation;
        existingUser.picture = dataUser.picture;
        
        existingUser.firstLogin = false;

        existingUser.save();

        return existingUser;
    }

    public void acceptCandidate(User user) {
        user.profile = Profile.MEMBER;
        user.save();
    }

    public void refuseCandidate(User user) {
        user.delete();
    }
}
