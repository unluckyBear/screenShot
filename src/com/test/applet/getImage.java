package com.test.applet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getImage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		   ObjectInputStream in = new ObjectInputStream(req.getInputStream());
		    BufferedOutputStream stream = null;
		    try {
		      Map param = (Map)in.readObject();
		      byte[] b = (byte[])param.get("image");
		      String fileName = (String)param.get("imageName");
		      File file = null;
		      file = new File(req.getSession().getServletContext().getRealPath("/image") + "/" + fileName + ".png");
		      FileOutputStream fstream = new FileOutputStream(file);
		      stream = new BufferedOutputStream(fstream);
		      stream.write(b);

		      ObjectOutputStream outStream = new ObjectOutputStream(resp.getOutputStream());
		      outStream.writeObject("call servlet successed!");
		      outStream.flush();
		      outStream.close();
		    } catch (ClassNotFoundException e) {
		      e.printStackTrace();
		    }
		    finally
		    {
		      if (stream != null)
		        try {
		          stream.close();
		        } catch (IOException e1) {
		          e1.printStackTrace();
		        }
		    }
	}
}
