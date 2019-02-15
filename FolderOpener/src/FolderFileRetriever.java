import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class FolderFileRetriever {
	private String sourcePath;
	private String destPath;
	private ArrayList<File> allFiles;
	private HashMap<String, File> fileWithExtension;
	private ArrayList<String> fileExtensions;
	//stores all the files in the folder
	//private Hashtable<FileType, File> fileWithType;	//stores all files with its type
	//private Hashtable<FileType, Integer> fileCount;
	
	public ArrayList<File> getAllFiles() {
		getFiles();
		return allFiles;
	}
	private String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String pth) {
		this.sourcePath = pth;
	}
	private String getDestPath() {
		return destPath;
	}
	public void setOutputPath(String outputPath) {
		this.destPath = outputPath;
	}
	
	public FolderFileRetriever() {}
	public FolderFileRetriever(String src) {
		this.setSourcePath(src);
	}
	public FolderFileRetriever(String src, String dest) {

		this.setOutputPath(src);
		this.setOutputPath(dest);
	}
	
	public File getFiles() {

			if(allFiles == null) {
			allFiles = new ArrayList<File>();
			}
			
			File[] files = new File(sourcePath).listFiles();
			
			for(File f: files) {
				
				if(f.isFile() && f != null) {
					allFiles.add(f);
					}
				else if(f.isDirectory()) {
					
					this.setSourcePath(f.getPath());
					allFiles.add(getFiles());
				}
			}
	
		return null;
	}
	public HashMap<String, File> getFilesWithExtensions() {
		
		if(fileWithExtension == null) {
			fileWithExtension = new HashMap<String, File>();
		}
		
		File[] files = new File(sourcePath).listFiles();
		
		for(File f: files) {
			
			if(f.isFile() && f != null) {
				fileWithExtension.put(this.getFileExtension(f), f);
				//allFiles.add(f);
				}
			else if(f.isDirectory()) {
				this.setSourcePath(f.getPath());
				//allFiles.add(getFiles());
				fileWithExtension.put(this.getFileExtension(f), new File(f.getPath()));
			}
		}
		
		return fileWithExtension;
	}	

	private void AddFileType(File f) {
		//add a new file type to the enum class
	}
	
	private void validatePath() throws FileReaderException{
		
		Path path = Paths.get(sourcePath);
		
		if(sourcePath == null || "".contentEquals(sourcePath)) {
			return;
		}
		
		if(Files.notExists(Paths.get(sourcePath))){
			throw new FileReaderException("File does not exist: " + path);
		}
		
		if(Files.isReadable(Paths.get(sourcePath))) {
			throw new FileReaderException("You do not have read permissions on this file: " + path);
		}
	}
	
	public String getFileExtension(File file) {
		if(file == null) {return null;}
		
		String name = file.getName();
		int lastIndex = name.lastIndexOf(".");
		
		if(lastIndex == -1) {return "";} //empty extension
		
		return name.substring(lastIndex);
	}
	private void setFileExtension(String ext) {
		if(ext == null) {return;}
		if(fileExtensions == null) {fileExtensions = new ArrayList<String>();}
		if(!fileExtensions.contains(ext)) {fileExtensions.add(ext);}
	}
}
