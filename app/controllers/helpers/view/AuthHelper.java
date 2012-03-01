package controllers.helpers.view;

import controllers.security.Auth;
import models.users.Profile;
import play.mvc.Before;
import play.mvc.Controller;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class AuthHelper extends Controller {

    public static final String HELPER_NAME = "auth";

    private Profile profile;

    private AuthHelper() {

    }

    @Before
    public static void before() {

        AuthHelper helper;

        if (Auth.isLogged()) {
            helper = new AuthHelper(Auth.getCurrentUser().profile);
        } else {
            helper = new GuestAuthHelper();
        }

        renderArgs.put(HELPER_NAME, helper);
    }

    private AuthHelper(Profile profile) {
        this.profile = checkNotNull(profile);
    }

    public boolean isLogged() {
        return true;
    }

    public boolean isGlm() {
        return profile.equals(Profile.GLM);
    }

    public boolean isClm() {
        return profile.equals(Profile.CLM);
    }

    public boolean isMember() {
        return profile.equals(Profile.MEMBER);
    }

    public boolean isCandidate() {
        return profile.equals(Profile.CANDIDATE);
    }

    public static class GuestAuthHelper extends AuthHelper {

        @Override
        public boolean isLogged() {
            return false;
        }

        @Override
        public boolean isGlm() {
            return false;
        }

        @Override
        public boolean isClm() {
            return false;
        }

        @Override
        public boolean isMember() {
            return false;
        }

        @Override
        public boolean isCandidate() {
            return false;
        }
    }
}
