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
import java.util.stream.Collectors;


import static com.objectivelist.homework5.servlet_session.EditSession.OBJECTIVES;
import static com.objectivelist.homework5.servlet_session.EditSession.SESSION_MAIN;


@WebServlet(name = "session_remove", urlPatterns = "/session_remove", loadOnStartup = 1)
public class RemoveSession extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int objectiveID = Integer.parseInt(request.getParameter("objectiveID"));

        HttpSession session = request.getSession(true);

        List<ObjectiveEntity> objectiveEntityList = ((List<ObjectiveEntity>) session.getAttribute("Objectives"));
        List<ObjectiveEntity> collect = objectiveEntityList.stream().filter(objectiveEntity -> objectiveEntity.getId() != objectiveID).collect(Collectors.toList());

        session.setAttribute(OBJECTIVES, collect);

        request.getRequestDispatcher(SESSION_MAIN).forward(request, response);

    }
}
