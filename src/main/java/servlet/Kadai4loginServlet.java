package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KanninDAO;
import dto.Kannin;
import util.GenerateHashedPw;

/**
 * Servlet implementation class Kadai4loginServlet
 */
@WebServlet("/Kadai4loginServlet")
public class Kadai4loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kadai4loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("UTF-8");
		
		String mail = request.getParameter("mail");
		String pw = request.getParameter("pw");
		
		String salt = KanninDAO.getSalt(mail);
		
		if(salt == null) {
			String view = "WEB-INF/view/Kadai-LoginFrom.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	
		String hashedPw = GenerateHashedPw.getSafetyPassword(pw, salt);
		
		System.out.println("ログイン時のソルト:" + salt);
		System.out.println("ログイン時のハッシュPW:" + hashedPw);
		
		Kannin kannin = KanninDAO.login(mail, hashedPw);
		
		if(kannin == null) {
			String view = "WEB-INF/view/Kadai-LoginFrom.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		} else {
			String view = "WEB-INF/view/Kadai-menu.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
