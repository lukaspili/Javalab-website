package controllers.helpers.controller;

import play.i18n.Messages;
import play.mvc.Scope;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class PageHelper {

    private String pageName;
    private Scope.RenderArgs renderArgs;

    public PageHelper(String pageName, Scope.RenderArgs renderArgs) {
        this.pageName = pageName;
        this.renderArgs = renderArgs;

        renderArgs.put("page_title", Messages.get(pageName));
    }

    public void title(String title) {
        renderArgs.put("page_title", Messages.get(pageName + "." + title));
    }

    public void uniqueTitle(String title) {
        renderArgs.put("page_title", Messages.get(title));
    }
}
