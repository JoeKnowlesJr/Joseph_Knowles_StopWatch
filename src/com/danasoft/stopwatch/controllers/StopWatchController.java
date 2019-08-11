package com.danasoft.stopwatch.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.danasoft.stopwatch.models.StopWatch;

/**
 * Servlet implementation class StopWatchController
 */
@WebServlet("/StopWatchController")
public class StopWatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String atr = "currentStopWatch";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StopWatchController() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		final String action = request.getParameter("action");
		final StopWatch currentSw = (StopWatch) session.getAttribute(atr);
		if (action != null ) {
			switch (action) {
			case "START":
				if (currentSw == null) { // no stopwatch running
					StopWatch t = new StopWatch();
					session.setAttribute(atr, t);
				}
				break;
			case "STOP":
				if (currentSw != null) {
					currentSw.stop();
					addToHistory(session, currentSw);
					session.setAttribute(atr, null);
				}
				break;
			case "RESET":
				session.setAttribute("history", null);
				if (currentSw != null) {
					currentSw.stop();
					session.setAttribute(atr, null);
				}
				break;
			}
		}
		response.sendRedirect("/StopWatch");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void addToHistory(HttpSession session, StopWatch sw) {
		@SuppressWarnings("unchecked") ArrayList<StopWatch> history = (ArrayList<StopWatch>) session.getAttribute("history");
		if (history == null) {
			history = new ArrayList<>();
		}
		history.add(sw);
		session.setAttribute("history", history);
		session.setAttribute(atr, null);		
	}

}
