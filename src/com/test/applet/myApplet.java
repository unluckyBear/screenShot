package com.test.applet;

import java.applet.Applet;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class myApplet extends Applet
{
  private static final long serialVersionUID = 1L;

  public void init()
  {
  }

@SuppressWarnings({ "unchecked", "rawtypes" })
public String testWriteFile(final String basePath)
  {
    System.out.println("write..........");
    String path = (String)AccessController.doPrivileged(new PrivilegedAction() {
    	File file = null;
        long currentTime = 0L;
        
		@Override
		public Object run() {
			try { Transferable t = Toolkit.getDefaultToolkit()
	            .getSystemClipboard().getContents(null);
	          if ((t != null) && 
	            (t.isDataFlavorSupported(DataFlavor.imageFlavor))) {
	            Image image = (Image)t
	              .getTransferData(DataFlavor.imageFlavor);

	            this.currentTime = new Date().getTime();
	            this.file = new File("d://My Documents//TMS//" + String.valueOf(this.currentTime) + ".png");
	            if (!this.file.exists()) {
	              this.file.mkdirs();
	            }
	            BufferedImage bufferedImage = new BufferedImage(image
	              .getWidth(null), image.getHeight(null), 
	              2);
	            Graphics2D g = bufferedImage.createGraphics();
	            g.drawImage(image, null, null);
	            ImageIO.write(bufferedImage, "png", 
	              this.file);

	            URL url = new URL(basePath + "getImage");
	            URLConnection con = url.openConnection();
	            System.out.println("call servlet start!");
	            con.setDoOutput(true);
	            con.setUseCaches(false);
	            con.setRequestProperty("Content-Type", "application/octet-stream");

	            Map param = new HashMap();
	            byte[] byteArray = this.getBytesFromFile(this.file);
	            param.put("image", byteArray);
	            param.put("imageName", String.valueOf(this.currentTime));
	            ObjectOutputStream outStream = new ObjectOutputStream(con.getOutputStream());
	            outStream.writeObject(param);
	            outStream.flush();
	            outStream.close();

	            ObjectInputStream inStream = new ObjectInputStream(con.getInputStream());
	            String str = (String)inStream.readObject();
	            System.out.println(str);
	          }
	        } catch (Exception e) {
	          System.out.println("Exception: " + e.getMessage());
	        }
	        return String.valueOf(this.currentTime);
		}
	});
    return path;
  }

  public byte[] getBytesFromFile(File f)
  {
    if (f == null)
      return null;
    try
    {
      FileInputStream stream = new FileInputStream(f);
      ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
      byte[] b = new byte[1000];
      int n;
      while ((n = stream.read(b)) != -1)
      {
        out.write(b, 0, n);
      }
      stream.close();
      out.close();
      return out.toByteArray();
    } catch (IOException localIOException) {
    }
    return null;
  }
}