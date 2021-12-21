import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import javax.imageio.ImageIO;

/**
 * Program to output the digits of pi to rgb based on the 
 * decimal value(even/odd)
 */

/**
 * @author Sai Tarun Sathyan
 * @author aarti
 */
public class NumberCounter2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String filename="pi6.txt";
		
		if(args.length!=0)
		{
			filename=args[0];
		}
		
		if(filename.substring(filename.length()-3,filename.length()).equals(".gz"))
		{
			FileOutputStream fos=new FileOutputStream(filename.substring(0,filename.length()-3));
			GZIPInputStream gzip= new GZIPInputStream(new FileInputStream(new File(filename)));
			int len=0;
			byte buff[]=new byte[1500000000];
			while((len=gzip.read(buff))>0)
			{
				fos.write(buff,0,len);
			}
			fos.close();
			gzip.close();
			filename=filename.substring(0,filename.length()-3);
		}
		
		
		//board setup
		int dimension=990;
		BufferedImage img = new BufferedImage(dimension,dimension,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, dimension, dimension);
		
		File file=new File(filename);
		//Scanner sc=new Scanner(file);
		int x=0;
		int y=0;
		BufferedReader br=new BufferedReader(new FileReader(file));
		char c=' ';
		c = (char) br.read();
		c = (char) br.read();
		while(( c = (char) br.read()) != -1)
		{
			System.out.println(c);
			if(Character.getNumericValue(c)%2==0)
			 {
				 g2d.setColor(Color.blue);
				 g2d.drawLine(x,y,x,y);
			 }
			 else
			 {
				 g2d.setColor(Color.red);
				 g2d.drawLine(x,y,x,y);
			 }
			x++;
			if(x==990) {y++;x=0;System.out.println(y); if(y==990) {break;}}
			
			
		}
		//save file
		File f= new File("PiPixelOutput.png");
		ImageIO.write(img, "png", f);
		
	}

}
