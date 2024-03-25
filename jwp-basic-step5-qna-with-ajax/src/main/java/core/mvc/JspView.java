package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;
//JSP에 대한 페이지 이동 처리를 담당

public class JspView implements View{
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    private String viewName;

    //param : 이동할 뷰 이름

    public JspView(String viewName) {
        if (viewName == null) {
            throw new NullPointerException("viewName is null. 이동할 URL을 입력하세요.");
        }
        this.viewName = viewName;
    }

    //위 페이지로 이동
    @Override
    public void render(Map<String, ?>model,  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }
        //Map 추가로 이 부분 수정
        Set<String> keys = model.keySet();
        for (String key : keys){
            request.setAttribute(key, model.get(key));
        }
        //
        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}