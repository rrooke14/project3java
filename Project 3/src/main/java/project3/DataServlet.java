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
	private static String un = "";
	private static String pw = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataServlet() {
		super();
		pred = new Predictor("./project3/weather.numeric.txt");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("login")!=null || request.getParameter("login2")!=null) {
			// this "button listener" is an example of dynamically creating
			// partial html code that is forwarded to a jsp page to be displayed
			un = request.getParameter("username");
			pw = request.getParameter("password");
			request.setAttribute("username", un);
			request.setAttribute("password", pw);
			/*
			String label1="selectionList";
			String label1Value = "<select name=\"activities\">\r\n";
			ArrayList<String> myActs = pred.getActivities();
			for (String string : myActs) {
				label1Value += "	<option value=\""+string+"\">"+string+"</option>\r\n";
			}
			label1Value += "  </select>";
			 */
			String label2="outlooks";
			String label2Value = "<input checked type=\"radio\" id=\"sunny\" name=\"outlook\" value=\"sunny\" required>";
			label2Value += "<label for=\"sunny\">Sunny</label><br>";
			label2Value += "<input type=\"radio\" id=\"overcast\" name=\"outlook\" value=\"overcast\">";
			label2Value += "<label for=\"overcast\">Overcast</label><br>";
			label2Value += "<input type=\"radio\" id=\"rainy\" name=\"outlook\" value=\"rainy\">";
			label2Value += "<label for=\"rainy\">Rainy</label><br>";

			String label3 = "humidity";
			String label3Value = "<label for=\"humidity\">Humidity (between 0 and 100%):</label>\r\n <input value=\"50\" type=\"number\" id=\"humidity\" name=\"humidity\" min=\"0\" max=\"100\">";

			String label4 = "temperature";
			String label4Value = "<label for=\"temperature\">Temperature (between -32 and 140 F):</label>\r\n <input value=\"50\" type=\"number\" id=\"temperature\" name=\"temperature\" min=\"-32\" max=\"140\">";

			String label5 = "windy";
			String label5Value = "  <input type=\"checkbox\" id=\"windy\" name=\"windy\" value=\"Windy\">\r\n <label for=\"windy\">Is it windy?</label><br>";

			int size = pred.size();
			request.setAttribute(label2, label2Value);
			request.setAttribute(label3, label3Value);
			request.setAttribute(label4, label4Value);
			request.setAttribute(label5, label5Value);
			request.setAttribute("size", size);
			if(un.equals("md") && pw.equals("pw")){
				RequestDispatcher rd=request.getRequestDispatcher("/using.jsp");
				rd.forward(request,response);
			}
			else {
				String warning = "	<h2 style=\"color:red\">Password or Username Incorrect</h2>";
				request.setAttribute("warning", warning);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		}
		//find best activity
		else if(request.getParameter("doUsing")!=null) {
			String outlook = (String)request.getParameter("outlooks");
			int humidity = Integer.parseInt(request.getParameter("humidity"));
			int temperature = Integer.parseInt(request.getParameter("temperature"));
			String wind = (String)request.getParameter("windy");
			Instance myInstance = new Instance(outlook, temperature, humidity, wind);
			String myActivity = pred.using(myInstance);
			myInstance = new Instance(outlook, temperature, humidity, wind, myActivity);
			String activity = myInstance.getActivity();
			String prediction = myInstance.toString();
			request.setAttribute("activity", activity);
			request.setAttribute("instance", prediction);

			if(un.equals("md") && pw.equals("pw")) {
				RequestDispatcher rd=request.getRequestDispatcher("/yourPrediction.jsp");
				rd.forward(request, response);
			}
		}
		//deleting most similar instance
		else if(request.getParameter("doUsing2")!=null) {
			Instance myInstance = pred.delete();
			String deleted = myInstance.toString();
			request.setAttribute("deleted", deleted);

			if(un.equals("md") && pw.equals("pw")) {
				RequestDispatcher rd=request.getRequestDispatcher("/Delete.jsp");
				rd.forward(request, response);
			}
		}
		//adds current predicted instance to pred
		else if(request.getParameter("return1")!=null) {
			String label2="outlooks";
			String label2Value = "<input checked type=\"radio\" id=\"sunny\" name=\"outlook\" value=\"sunny\" required>";
			label2Value += "<label for=\"sunny\">Sunny</label><br>";
			label2Value += "<input type=\"radio\" id=\"overcast\" name=\"outlook\" value=\"overcast\">";
			label2Value += "<label for=\"overcast\">Overcast</label><br>";
			label2Value += "<input type=\"radio\" id=\"rainy\" name=\"outlook\" value=\"rainy\">";
			label2Value += "<label for=\"rainy\">Rainy</label><br>";

			String label3 = "humidity";
			String label3Value = "<label for=\"humidity\">Humidity (between 0 and 100%):</label>\r\n <input value=\"50\" type=\"number\" id=\"humidity\" name=\"humidity\" min=\"0\" max=\"100\">";

			String label4 = "temperature";
			String label4Value = "<label for=\"temperature\">Temperature (between -32 and 140 F):</label>\r\n <input value=\"50\" type=\"number\" id=\"temperature\" name=\"temperature\" min=\"-32\" max=\"140\">";

			String label5 = "windy";
			String label5Value = "  <input type=\"checkbox\" id=\"windy\" name=\"windy\" value=\"Windy\">\r\n <label for=\"windy\">Is it windy?</label><br>";

			int size = pred.size();
			request.setAttribute(label2, label2Value);
			request.setAttribute(label3, label3Value);
			request.setAttribute(label4, label4Value);
			request.setAttribute(label5, label5Value);
			pred.add((Instance)request.getAttribute("instance"));
			request.setAttribute("size", size);

			request.setAttribute("username", un);
			request.setAttribute("password", pw);
			if(un.equals("md") && pw.equals("pw")) {
				RequestDispatcher rd=request.getRequestDispatcher("/using.jsp");
				rd.forward(request,response);
			}
		}
		//return to prediction page but do not add instance to pred
		else if(request.getParameter("return2")!=null) {
			String label2="outlooks";
			String label2Value = "<input checked type=\"radio\" id=\"sunny\" name=\"outlook\" value=\"sunny\" required>";
			label2Value += "<label for=\"sunny\">Sunny</label><br>";
			label2Value += "<input type=\"radio\" id=\"overcast\" name=\"outlook\" value=\"overcast\">";
			label2Value += "<label for=\"overcast\">Overcast</label><br>";
			label2Value += "<input type=\"radio\" id=\"rainy\" name=\"outlook\" value=\"rainy\">";
			label2Value += "<label for=\"rainy\">Rainy</label><br>";

			String label3 = "humidity";
			String label3Value = "<label for=\"humidity\">Humidity (between 0 and 100%):</label>\r\n <input value=\"50\" type=\"number\" id=\"humidity\" name=\"humidity\" min=\"0\" max=\"100\">";

			String label4 = "temperature";
			String label4Value = "<label for=\"temperature\">Temperature (between -32 and 140 F):</label>\r\n <input value=\"50\" type=\"number\" id=\"temperature\" name=\"temperature\" min=\"-32\" max=\"140\">";

			String label5 = "windy";
			String label5Value = "  <input type=\"checkbox\" id=\"windy\" name=\"windy\" value=\"Windy\">\r\n <label for=\"windy\">Is it windy?</label><br>";

			int size = pred.size();
			request.setAttribute(label2, label2Value);
			request.setAttribute(label3, label3Value);
			request.setAttribute(label4, label4Value);
			request.setAttribute(label5, label5Value);
			request.setAttribute("size", size);

			request.setAttribute("username", un);
			request.setAttribute("password", pw);
			if(un.equals("md") && pw.equals("pw")) {
				RequestDispatcher rd=request.getRequestDispatcher("/using.jsp");
				rd.forward(request,response);
			}
		}
		else if(request.getParameter("delete")!=null) {
			String label2="outlooks";
			String label2Value = "<input checked type=\"radio\" id=\"sunny\" name=\"outlook\" value=\"sunny\" required>";
			label2Value += "<label for=\"sunny\">Sunny</label><br>";
			label2Value += "<input type=\"radio\" id=\"overcast\" name=\"outlook\" value=\"overcast\">";
			label2Value += "<label for=\"overcast\">Overcast</label><br>";
			label2Value += "<input type=\"radio\" id=\"rainy\" name=\"outlook\" value=\"rainy\">";
			label2Value += "<label for=\"rainy\">Rainy</label><br>";

			String label3 = "humidity";
			String label3Value = "<label for=\"humidity\">Humidity (between 0 and 100%):</label>\r\n <input value=\"50\" type=\"number\" id=\"humidity\" name=\"humidity\" min=\"0\" max=\"100\">";

			String label4 = "temperature";
			String label4Value = "<label for=\"temperature\">Temperature (between -32 and 140 F):</label>\r\n <input value=\"50\" type=\"number\" id=\"temperature\" name=\"temperature\" min=\"-32\" max=\"140\">";

			String label5 = "windy";
			String label5Value = "  <input type=\"checkbox\" id=\"windy\" name=\"windy\" value=\"Windy\">\r\n <label for=\"windy\">Is it windy?</label><br>";

			int size = pred.size();
			request.setAttribute(label2, label2Value);
			request.setAttribute(label3, label3Value);
			request.setAttribute(label4, label4Value);
			request.setAttribute(label5, label5Value);
			request.setAttribute("size", size);

			request.setAttribute("username", un);
			request.setAttribute("password", pw);		
			
			if(un.equals("md") && pw.equals("pw")) {
				RequestDispatcher rd=request.getRequestDispatcher("/using.jsp");
				rd.forward(request,response);
			}
		}
		else {
			// if the user runs the servlet directly, they will be forwarded
			// to the starting index page.
			String warning = "	<h2 style=\"color:red\">Password or Username Incorrect</h2>";
			request.setAttribute("warning", warning);

			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request,response); 			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
