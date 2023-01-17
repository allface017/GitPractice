package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Kannin;

/**
 * Servlet implementation class KadaiComnfirm
 */
@WebServlet("/KadaiComnfirm")
public class KadaiComnfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KadaiComnfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	
	String name = request.getParameter("name");
	int age =Integer.parseInt(request.getParameter("age"));
	String gender = request.getParameter("gender");
	String tel = request.getParameter("tel");
	String mail = request.getParameter("mail");
	String password = request.getParameter("password");
	
	Kannin kannin= new Kannin(-1, name, age, gender, tel, mail,null, password, null);
	
	HttpSession session = request.getSession();
	
		session.setAttribute("input_data",kannin);	
		
		String view = "WEB-INF/view/kadai1-confirm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
