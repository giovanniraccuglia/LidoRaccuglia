package com.raccuglia.servlet.generale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Postazione;

/**
 * Servlet implementation class SpiaggiaServlet
 */
@WebServlet(name = "SpiaggiaServlet", urlPatterns = {"/spiaggia"})
public class SpiaggiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		spiaggiaPage(request, response);
	}
	
	private void spiaggiaPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Date dataPrenotazione = Date.valueOf(request.getParameter("dataPrenotazione"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date(System.currentTimeMillis());
			if(dataPrenotazione != null && (dataPrenotazione.compareTo(Date.valueOf(sdf.format(today))) == 0 || dataPrenotazione.after(Date.valueOf(sdf.format(today))))) {
				List<Postazione> postazioniDisabilitate = DBMS.getPostazioniDisabilitate();
				List<Postazione> postazioniPrenotate = DBMS.getPostazioniPrenotate(dataPrenotazione);
				PrintWriter pr = response.getWriter();
				response.setContentType("application/json");
				ObjectMapper mapper = new ObjectMapper();
				pr.write("[" + mapper.writeValueAsString(postazioniDisabilitate) + "," + mapper.writeValueAsString(postazioniPrenotate) + "]");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
}