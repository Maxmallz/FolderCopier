
public class FileReaderException extends Exception{
	private String msg;
	
	public FileReaderException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		if(this.msg == null) {throw new NullPointerException();}
		return this.msg;
	}
}
