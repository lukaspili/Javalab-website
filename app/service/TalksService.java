package service;

import models.events.Talk;
import models.users.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AkeT
 * Date: 25/11/12
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class TalksService {

    public List<Talk> getTalksByUser(User user) {
        return Talk.find("speaker = ? order by date desc", user).fetch();
    }
}
