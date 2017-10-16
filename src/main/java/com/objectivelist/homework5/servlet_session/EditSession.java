package com.objectivelist.homework5.servlet_session;




import com.objectivelist.homework5.entity.ObjectiveEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "edit", urlPatterns = "/session_edit", loadOnStartup = 1)
public class EditSession extends HttpServlet {


    public static final String SESSION_MAIN = "/session_main";
    public static final String OBJECTIVES = "Objectives";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int objectiveID = Integer.parseInt(request.getParameter("objectiveID"));

        HttpSession session = request.getSession(true);

        List<ObjectiveEntity> objectiveEntityList = ((List<ObjectiveEntity>) session.getAttribute("Objectives"));
        objectiveEntityList.stream().filter(objective -> objective.getId() == objectiveID).forEach(objective -> {
            if (objective.isDone()) objective.setDone(false);
            else objective.setDone(true);
        });

        session.setAttribute(OBJECTIVES, objectiveEntityList);
        request.getRequestDispatcher(SESSION_MAIN).forward(request, response);

    }
}
