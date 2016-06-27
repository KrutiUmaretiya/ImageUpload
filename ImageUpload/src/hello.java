

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.*;

import com.sun.pkg.util.Base64;

/**
 * Servlet implementation class hello
 */
@WebServlet("/hello")
public class hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public hello() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
			 String driverName = "com.mysql.jdbc.Driver";
			 String url = "jdbc:mysql://localhost:3306/";
			 String dbName = "form";
			 String userName = "root";
			 String password = "qmetry";
			 Connection con = null;
			 try{
			   Class.forName(driverName);
			   con = DriverManager.getConnection(url+dbName,userName,password);
			 }catch (Exception e1){
				 System.out.println(e1.getMessage());
			}
	        out.println("<center><h1>Your Profile has been Uploaded</h1></center>");
	        String emp_name="";
	        String emp_c_number="";
	        String emp_emailid="";
	        String address1="";
	        String address2="";	        
	        
	    	if (ServletFileUpload.isMultipartContent(request))
			{final FileItemFactory factory = new DiskFileItemFactory();
			final ServletFileUpload fileUpload = new ServletFileUpload(factory);

			String fileName = null;
			String fileExtension = null;
			long fileSize = 0;
		
			byte[] fileContents = null;

			try
			{
				 
				final List<FileItem> items = fileUpload.parseRequest(request);
				FileItem item = null;

				if (null != items)
				{  
					  Iterator itr = items.iterator();
				        while (itr.hasNext())
				        {

				        item = (FileItem) itr.next();
				        if (item.isFormField())
				        {
				        String name = item.getFieldName();
				        String value = item.getString();
				        if(name.equals("emp_name"))
				        {
				        emp_name=value;
				        System.out.println("employee: "+emp_name);
				        }
				        if(name.equals("address1"))
				        {
				        address1=value;
				        }
				        if(name.equals("address2"))
				        {
				        address2=value;
				        }
				        if(name.equals("contact_number"))
				        {
				        emp_c_number=value;
				        }
				        if(name.equals("email_id"))
				        {
				        emp_emailid=value;
				        }
				        } else
				        {
				        try {

				        	fileContents = item.get();
							fileSize = item.getSize();
							fileName = item.getName();
							 String itemName = item.getName();
							fileExtension = FilenameUtils.getExtension(fileName);
//							 ByteArrayInputStream is = new ByteArrayInputStream(fileContents);
//							 BufferedImage bimage = ImageIO.read(is);
							String imageBase64ForStatus="";
							 PreparedStatement pre =
									   con.prepareStatement("insert into master values(?,?,?)");
									   pre.setString(1,emp_name);
									   pre.setString(2,fileName);
									   pre.setBytes(3, fileContents);;
									   pre.executeUpdate();
									   System.out.println("Successfully inserted the file into the database!");
									  
//									   
//									   int height = bimage.getHeight();
//							    int width =bimage.getWidth();
						
				        out.println("<center>"
				        		+ "<table>"
				        		+ "<tr>"
				        		+ "<td width='210'></td>"+ 
				        		"<td>");
				        String img="displayImage?id='"+emp_name+"'";
				        System.out.println("path: "+img);
				        out.println("<img src= "+img+" width='137' height='140' alt='kruti'>");
				        out.println("</td></tr>");
				        out.println("<tr><td align='left'><b>Name:</td><td><b>"+emp_name+"</td></tr>");
				        out.println("<tr><td align='left'><b>Addresss:</td><td><b>"+address1+"</td></tr>");
				        out.println("<tr><td align='left'><b></td><td><b>"+ address2+"</td></tr>");
				        out.println("<tr><td align='left'><b>Contact No:</td><td><b>"+emp_c_number+"</td></tr>");
				        out.println("<tr><td align='left'><b>Email ID</td><td><b>"+emp_emailid+"</td></tr>");
				        } catch (Exception e) {
				        e.printStackTrace();
				        }
				        }
				          
				        }
				        out.println("</table></center>");
				}
			}
			catch (final FileUploadException e)
			{
				
			}

	}
	    
	      
		

	}

}
