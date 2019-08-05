package com.danasoft.timer.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.danasoft.timer.models.Timer;

/**
 * Servlet implementation class TimerController
 */
@WebServlet("/TimerController")
public class TimerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimerController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		final String action = request.getParameter("action");
		final Timer currentTimer = (Timer) session.getAttribute("currentTimer");
		if (action != null ) {
			switch (action) {
			case "START":
				if (currentTimer == null) { // no timer running
					Timer t = new Timer();
					session.setAttribute("currentTimer", t);
				}
				break;
			case "STOP":
				if (currentTimer != null) {
					long elapsedMillis = currentTimer.stop();
				}
				break;
			case "RESET":
				ArrayList<Timer> history = (ArrayList<Timer>) session.getAttribute("history");
				if (history == null) {
					history = new ArrayList<>();
					history.add(currentTimer);
					session.setAttribute("history", history);
					session.setAttribute("currentTimer", null);
				}	
				break;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
