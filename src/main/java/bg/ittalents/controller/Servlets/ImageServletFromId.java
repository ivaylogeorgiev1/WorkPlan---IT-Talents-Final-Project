package bg.ittalents.controller.Servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ittalents.model.DAO.INFO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;

/**
 * Servlet implementation class IamageServlet
 */
@WebServlet("/ImageServletFromID")
public class ImageServletFromId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServletFromId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		
		response.setContentType("image/jpeg");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		User user=null;
		
		try {
			user=IUserDAO.getDAO("db").getUserById(userId);
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		
		String pathToWeb = INFO.IMAGES_PATH+File.separator+user.getAvatarPath();
		
		
		File f = new File(pathToWeb);
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static boolean isLogged(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User checkUser=(User)(request.getSession().getAttribute("user"));
		
		
		if (request.getSession(false) == null||checkUser==null||checkUser.getPassword()==null||checkUser.getEmail()==null) {
			
			response.sendRedirect("./normalLogin");
			return false;
//			RequestDispatcher dispatcher = request.getRequestDispatcher("./normalLogin");
//			
//			try {
//				dispatcher.(request, response);
//			} catch (ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		
		return true;
		
		
	}

}
