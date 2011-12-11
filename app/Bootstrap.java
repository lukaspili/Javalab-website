import models.users.User;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import seeds.TestSeed;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@OnApplicationStart
public class Bootstrap extends Job {

    @Override
    public void doJob() throws Exception {

        // insert test seed only in dev mode and if they didn't exists yet
        if (Play.configuration.getProperty("application.mode").equals("dev") && User.count() == 0) {
            TestSeed.insert();
        }
    }
}
