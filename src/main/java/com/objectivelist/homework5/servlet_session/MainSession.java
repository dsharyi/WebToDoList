package com.objectivelist.homework5.servlet_session;


import com.objectivelist.homework5.entity.ObjectiveEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.objectivelist.homework5.servlet_session.EditSession.OBJECTIVES;


@WebServlet(name = "main",urlPatterns = "/session_main",loadOnStartup = 1)
public class MainSession extends HttpServlet {


    private static final String INDEX_HTML = "/index.html";
    private static final String UTF_8 = "utf-8";
    private static final String PAGE_JSP = "/page.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(UTF_8);

        String title = req.getParameter("title");
        String description = req.getParameter("description");

        HttpSession session = req.getSession(true);

        List<ObjectiveEntity> objectiveEntityList = ((List<ObjectiveEntity>) session.getAttribute(OBJECTIVES));

        if (objectiveEntityList == null) objectiveEntityList = new ArrayList<>();


        if (title == null || title.isEmpty()) resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        else {

            ObjectiveEntity objectiveEntity = new ObjectiveEntity();
            objectiveEntity.setTitle(title);
            objectiveEntity.setDescription(description);
            objectiveEntity.setId(objectiveEntityList.isEmpty()? objectiveEntityList.size()+1: objectiveEntityList.stream().map(objective1 -> objective1.getId()).max(Integer::compareTo).get()+1);

            objectiveEntityList.add(objectiveEntity);
        }

        session.setAttribute(OBJECTIVES, objectiveEntityList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(INDEX_HTML);

        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        List<ObjectiveEntity> allObjectiveEntities = ((List<ObjectiveEntity>) session.getAttribute(OBJECTIVES));
        if (allObjectiveEntities == null) {

            allObjectiveEntities = new ArrayList<>();
            session.setAttribute(OBJECTIVES, allObjectiveEntities);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PAGE_JSP);
        requestDispatcher.forward(req, resp);

    }
}
