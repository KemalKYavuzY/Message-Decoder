package msgdecoder;

/**
 * 
 * @author Kemal Yavuz
 * 
 * This class represents a binary tree used for decoding messages.
 *
 */
public class MsgTree {

	// The character associated with the tree node.
    public char payloadChar;
    
    // The left and right children of the tree node.
    public MsgTree left;
    public MsgTree right;

    // A static index used for constructing the tree from a string.
    private static int staticCharIdx = 0;

    /**
     * Constructs a message tree from the provided encoding string.
     *
     * @param encodingString The string used to build the message tree.
     */
    public MsgTree(String encodingString) {
        payloadChar = encodingString.charAt(staticCharIdx);

        staticCharIdx++;

        left = new MsgTree(encodingString.charAt(staticCharIdx));

        // Check if the left child is a placeholder '^' and create a subtree.
        if(left.payloadChar == '^') {
        	left = new MsgTree(encodingString);
        }

        staticCharIdx++;

        right = new MsgTree(encodingString.charAt(staticCharIdx));

        // Check if the right child is a placeholder '^' and create a subtree.
        if(right.payloadChar == '^') {
        	right = new MsgTree(encodingString);
        }
    }

    /**
     * Constructs a message tree with a single node and null children.
     *
     * @param payloadChar The character associated with the tree node.
     */
    public MsgTree(char payloadChar){
    	this.payloadChar = payloadChar;
    }

    /**
     * Prints characters and their binary codes using a pre-order traversal of the tree.
     *
     * @param root The root of the message tree.
     * @param code The binary code associated with the current node.
     */
    public static void printCodes(MsgTree root, String code) {

        if(root == null) {
            return;
        }

        if(root.payloadChar != '^' ) {
            if(root.payloadChar == '\n') {
                System.out.println("\\n " + code);
            } else {
            	System.out.println(root.payloadChar + "  " + code);
            }
        }

        // Recursively print codes for left and right children.
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }
}