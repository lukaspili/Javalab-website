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
    
    public User create(User user) {
    	
    	User userToCreate = new User();
    	userToCreate.firstName = user.firstName;
    	
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
    
}
