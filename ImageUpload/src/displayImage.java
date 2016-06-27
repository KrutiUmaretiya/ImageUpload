

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class displayImage
 */
@WebServlet("/displayImage")
public class displayImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        String imageId = request.getParameter("id");
        System.out.println("image:"+imageId);
        InputStream sImage;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
		try{
       	 String driverName = "com.mysql.jdbc.Driver";
			 String url = "jdbc:mysql://localhost:3306/";
			 String dbName = "form";
			 String userName = "root";
			 String password = "qmetry";
			
			
			   Class.forName(driverName);
			   conn = DriverManager.getConnection(url+dbName,userName,password);
			
           stmt = conn.prepareStatement("select * from master where id="+imageId);
           rs = stmt.executeQuery();
           if(rs.next()){
               System.out.println("Inside RS");
               byte[] bytearray = new byte[1048576];
               int size=0;
               sImage = rs.getBinaryStream(3);
               response.reset();
               response.setContentType("image/jpeg");
               while((size = sImage.read(bytearray)) != -1 ){
                   response.getOutputStream().
                   write(bytearray,0,size);
               }
           }

       } catch (Exception e){
           e.printStackTrace();
       }
   
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
