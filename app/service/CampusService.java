package service;

import models.users.Campus;
import models.users.Profile;
import models.users.User;

import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class CampusService {

    public Campus findCampusByName(String name) {

        Campus.Name campusName;
        try {
            campusName = Campus.Name.valueOf(name);
        } catch (Exception e) {
            return null;
        }

        return Campus.find("byName", campusName).first();
    }

    public List<Campus> getCampuses() {
        return Campus.em().createQuery("select c from Campus c order by c.name").getResultList();
    }
}
