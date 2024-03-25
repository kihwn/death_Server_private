package core.mvc;

public abstract class AbstractController implements Controller{
    //ModelAndView 생성을 돕자!
    protected ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }

}
