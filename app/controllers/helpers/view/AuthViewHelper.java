package controllers.helpers.view;

import controllers.filters.AuthFilter;
import models.users.Profile;
import play.mvc.Before;
import play.mvc.Controller;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class AuthViewHelper extends Controller {

    public static final String HELPER_NAME = "auth";

    private Profile profile;

    private AuthViewHelper() {

    }

    @Before
    public static void before() {

        AuthViewHelper helper;

        if (AuthFilter.isLogged()) {
            helper = new AuthViewHelper(AuthFilter.getCurrentUser().profile);
        } else {
            helper = new GuestAuthViewHelper();
        }

        renderArgs.put(HELPER_NAME, helper);
    }

    private AuthViewHelper(Profile profile) {
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

    public static class GuestAuthViewHelper extends AuthViewHelper {

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
    }
}
