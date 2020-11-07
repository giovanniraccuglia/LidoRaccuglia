package com.raccuglia.servlet.generale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Postazione;
import com.raccuglia.utils.LidoUtil;

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
			String data = request.getParameter("dataPrenotazione");
			if(LidoUtil.checkInput(data)) {
				Date dataPrenotazione = Date.valueOf(data);
				String time = "19:00:00";
				if(dataPrenotazione.compareTo(Date.valueOf(LidoUtil.setDate(time))) == 0 || dataPrenotazione.after(Date.valueOf(LidoUtil.setDate(time)))) {
					List<Postazione> postazioniPrenotate = DBMS.getPostazioniPrenotate(dataPrenotazione);
					List<Postazione> postazioni = DBMS.getPostazioni();
					PrintWriter pr = response.getWriter();
					response.setContentType("application/json");
					ObjectMapper mapper = new ObjectMapper();
					pr.write("[" + mapper.writeValueAsString(postazioni) + "," + mapper.writeValueAsString(postazioniPrenotate) + "]");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
}
