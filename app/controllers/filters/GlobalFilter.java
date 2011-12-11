package controllers.filters;

import controllers.functionnals.FunctionnalController;
import play.mvc.Before;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class GlobalFilter extends FunctionnalController {

//    @Inject
//    private static BuildService buildService;

    private static final String APPLICATION_NAME = "Laboratoires Java de SUPINFO";

    @Before(priority = 0)
    public static void before() {

        renderArgs.put("application_name", APPLICATION_NAME);
//        renderArgs.put("globalBuild", buildService.getLatestBuild());
    }
}
