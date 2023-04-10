package project3;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Predictor pred;
	private static int visitCount;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        pred = new Predictor("./project3/weather.numeric.txt");
        visitCount=0;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		visitCount++;
		if(request.getParameter("login")!=null) {
			// this "button listener" is an example of dynamically creating
			// partial html code that is forwarded to a jsp page to be displayed
			String user = request.getParameter("un");
			String password = request.getParameter("pw");
			request.setAttribute("username", user);
			request.setAttribute("password", password);
			
			String label1="selectionList";
			String label1Value = "<select name=\"activities\">\r\n";
			ArrayList<String> myActs = pred.getActivities();
			for (String string : myActs) {
				label1Value += "	<option value=\""+string+"\">"+string+"</option>\r\n";
			}

			label1Value += "  </select>";

			request.setAttribute(label1,label1Value);
			
			
			
			RequestDispatcher rd=request.getRequestDispatcher("/using.jsp");
			rd.forward(request,response); 		}
		else if(request.getParameter("doUsing")!=null) {
			String currentActivity = request.getParameter("myact");
			request.setAttribute("activity", currentActivity);
			RequestDispatcher rd=request.getRequestDispatcher("/yourPrediction.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("return")!=null) {
			RequestDispatcher rd=request.getRequestDispatcher("/using.jsp");
			rd.forward(request, response);
		}
		else {
			// if the user runs the servlet directly, they will be forwarded
			// to the starting index page.
			RequestDispatcher rd=request.getRequestDispatcher("/index.html");
			rd.forward(request,response); 			
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
