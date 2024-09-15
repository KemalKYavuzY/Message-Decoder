package msgdecoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Kemal Yavuz
 * 
 * This class decodes a message using a binary tree built from a provided file.
 * It takes a filename as input, reads the file, and then decodes a coded message
 * using the constructed binary tree.
 *
 */
public class ArchMsgDecoder {

	// Static index used to traverse the characters in the coded message.
	private static int staticCharIdx = 0;

	public static void main(String[] args) {
	    String fileName;
	    String codeMessage = "";
	
	    Scanner scanner = new Scanner(System.in);
	
	    System.out.println("Please enter filename to decode:  ");
	    fileName = scanner.next();
	
	    ArrayList<String> lines = fileReader(fileName);
	
	    // Concatenate lines to form the code message.
	    for(int i = 0; i < lines.size() - 2; i++) {
	        codeMessage += lines.get(i) + '\n';
	    }
	    
	    codeMessage += lines.get(lines.size() - 2);
	
	    // Build the main message tree.
	    MsgTree mainTree = new MsgTree(codeMessage);
	
	    // Print the codes associated with each character in the message tree.
	    mainTree.printCodes(mainTree, "");
	    
	    // Get the last line which contains the coded message.
	    int firstln = lines.get(lines.size() - 1).length();
	
	    // Decode the message using the constructed tree.
	    while(staticCharIdx < firstln) {
	    	decode(mainTree, lines.get(lines.size() - 1));
	    }
	    
	    scanner.close();
	}

	 /**
      * Reads a given file and returns an ArrayList of strings containing each line.
      *
      * @param fileName The name of the file to be read.
      * @return An ArrayList of strings representing the lines of the file.
	  */
	 static ArrayList fileReader(String fileName) {
	
		 ArrayList<String> lines = new ArrayList<String>();
		 
	     try {
	    	 
	    	 File file = new File(fileName);
	    	 Scanner fileScanner = new Scanner(file);
	
	    	 // Read each line and add it to the ArrayList.
	    	 while(fileScanner.hasNext()) {
	    		 lines.add(fileScanner.nextLine());
	    	 }
	    	 
	    	 fileScanner.close();
	     } catch(Exception e) {
	    	 System.out.println("File not found");
	     }
	     
	    return lines;
	}

	 /**
      * Decodes a message using the provided binary tree.
      *
      * @param codes The binary tree used for decoding.
      * @param msg   The coded message to be decoded.
	  */
	 public static void decode(MsgTree codes, String msg) {

		 // Traverse the tree until a leaf node is reached.
		 while(codes.left != null && codes.right != null) {

		     if(msg.charAt(staticCharIdx) == '0') {
		    	 codes = codes.left;
		     } else {
		    	 codes = codes.right;
		     }
		     
		     staticCharIdx++;
		 }
	
		 // Print the decoded character.
		 if(codes.payloadChar == '\n') {
			 System.out.print("\n");
	     } else {
	    	 System.out.print(codes.payloadChar);
	     }
	}
}