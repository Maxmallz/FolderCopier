import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class FolderOpener {
	
	private static String folderPath;
	private String outputPath = "folderFiles.txt";
	private static List<File> allFiles = null;
	private static int result = 0;
	
	
	public FolderOpener(String s) {
		this.folderPath = s;
	}
	
	public static void main(String[] args) throws IOException {
		FolderFileRetriever ffr = new FolderFileRetriever("C:\\Users\\SLIM\\Documents\\MyFlashDrive");
		
		System.out.println(ffr.getAllFiles().size());
		

		BufferedWriter writer = null;
		BufferedOutputStream bos = null;
		try
			{
			//BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
				writer = new BufferedWriter( new FileWriter( "folderFiles.txt"));
				
				//for(File f: ffr.getAllFiles()) {
					//if(f != null) {
					//writer.write(ffr.getFileExtension(f));;
					//writer.newLine();
//					//writer.write(f.getName());
					//}
				//}
				
				
				for(String s: ffr.getFilesWithExtensions().keySet()) {
					
					
					System.out.println(s + ffr.getFilesWithExtensions().get(s));
					break;
				}
				
			}
		catch ( IOException e)
			{
			}
		finally{
			try
			{
				if ( writer != null)
					writer.close( );
					}
					catch (IOException e) {
			System.out.println(e.getClass().getSimpleName());
					}
				}
		System.out.println("Done");
	}

	
	
}
