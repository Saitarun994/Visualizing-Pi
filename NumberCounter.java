import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/**
 * 
 */

/**
 * @author Sai Tarun Sathyan
 * @author Aarti
 */
public class NumberCounter {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String filename="Lottery_Pick_10_Winning_Numbers__Beginning_1987.csv";
		//String filename="Lottery_Pick_10_Winning_Numbers__Beginning_1987.csv.gz";
		if(args.length!=0)
		{
				filename=args[0];
		}
		
		if(filename.substring(filename.length()-3,filename.length()).equals(".gz"))
		{
			byte buff[] = new byte[200000];
			FileOutputStream fos=new FileOutputStream(filename.substring(0,filename.length()-3));
			GZIPInputStream gZipObj= new GZIPInputStream(new FileInputStream(new File(filename)));
			int len=0;
			while((len=gZipObj.read(buff))>0)
			{
				fos.write(buff,0,len);
			}
			fos.close();
			gZipObj.close();
			filename=filename.substring(0,filename.length()-3);
		}
			
		File f=new File(filename);
		Scanner sc= new Scanner(f);
		int frequency[]=new int[81];
		String temp[]=new String[1];
		String numbers[]= new String[20];
		sc.nextLine();
		while(sc.hasNext())
		{
			String s=sc.nextLine();
			temp=s.split(",");
			numbers=temp[1].split(" ");
			for(int i=0 ;i<20;i++)
			{
				frequency[Integer.valueOf(numbers[i])]++;
			}
		}
		
		for(int i=1;i<81;i++)
		{
			System.out.println(i+": "+frequency[i]);
		}
		
		sc.close();
	}

}
