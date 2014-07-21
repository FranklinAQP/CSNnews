package csn;

import java.io.IOException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.gson.Gson;

import java.util.*;





@SuppressWarnings("serial")
public class CSNServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		Stack<User> list = new Stack<User>();
		/*String nombre = req.getParameter("nombre");
		String otro = req.getParameter("otro");
	    list.push(new User(1, "user1"+nombre));
	    list.push(new User(2, "user2"+nombre));
	    list.push(new User(3, "user3"+otro));*/
	    String json = new Gson().toJson(list);

	   //resp.getWriter().println("alertsss");
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(json);
	}
}
