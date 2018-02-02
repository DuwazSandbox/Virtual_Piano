import java.io.*;

public class piano { 
	public static void main(String[] args) {
		try {
			loadFileAsString("song");
		}
		catch (Exception e){};
	}
	
	public static String loadFileAsString(String file) throws IOException {		
        InputStreamReader f = new InputStreamReader( new FileInputStream(file + ".txt"));        
		BufferedWriter out = new BufferedWriter(new FileWriter(file + ".bat"));
		
		int inputLetter;
		int str = 1;
		int word = 1;
		boolean lock = false;
 	
		out.write("timeout /t 10\n");
		out.write(":: string №1  word №1\n");
		word++;		
		
		inputLetter = f.read();
		
		while (inputLetter != -1) {
			if (inputLetter == 13) {} else
			if (inputLetter == 91) {lock=true;} else
			if (inputLetter == 93) {lock=false; between_letters(out);} else
			if (inputLetter == 124) {if_I(out);} else
			if (lock == true) {
				entering_letters(out,inputLetter);
			} 
			else if (inputLetter == 32 || inputLetter == 10) { 
				if (inputLetter == 10) {
					word = 1;
					str++; 
				}
				out.write(":: string №" + str + " word №" + word++ + "\n"); 
				
				if_space(out);
			} 
			else {
				entering_letters(out,inputLetter);
				between_letters(out);
			}				
			inputLetter=f.read();
		}
		f.close();
		out.close();
 
        return null;		
     }
	 
	 public static void entering_letters(BufferedWriter out, int inputLetter) throws IOException {			
		if (inputLetter >= 'A' && inputLetter <= 'Z')
			out.write("nircmdc sendkey shift down\nnircmdc sendkeypress " + (char)inputLetter + "\nnircmdc sendkey shift up\n");
		else
			out.write("nircmdc sendkeypress " + (char)inputLetter + "\n");
	 }
	 
	 public static void if_I(BufferedWriter out) throws IOException { // если прямой слэш
		out.write("nircmd wait 0\n");
	 }
	 public static void between_letters(BufferedWriter out) throws IOException {
		out.write("nircmd wait 100\n");
	 }
	 public static void if_space(BufferedWriter out) throws IOException {
		out.write("nircmd wait 0\n");
	 }
}