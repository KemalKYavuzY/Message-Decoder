# Message Decoder
Java-based binary tree message decoder that reconstructs archived messages from an encoding scheme. The program decodes a compressed message using a binary tree traversal algorithm.

## Project Overview

This project consists of two main classes:
1. **MsgTree**: This class represents a binary tree where each node contains a character (payload) and links to left and right children. The tree is built from an encoding string.
2. **ArchMsgDecoder**: This class handles the decoding of a coded message using the binary tree constructed by `MsgTree`. The decoder reads an input file containing the encoded message and decodes it by traversing the binary tree.

## How It Works

### 1. MsgTree Construction:
- The `MsgTree` class takes an encoding string and constructs a binary tree from it.
- The tree is built using the characters from the string, and each node either holds a character or a special symbol (`^`) indicating a branch in the tree.
- The left and right subtrees are recursively built based on the encoding string.

### 2. Message Decoding:
- The `ArchMsgDecoder` class reads a file containing the encoded message.
- The first part of the file contains the binary tree encoded as a string, and the last line contains the actual message encoded in binary.
- Using the `MsgTree` structure, the encoded binary message is decoded by traversing the tree, turning left on encountering a '0' and right on encountering a '1'.
- The program outputs the decoded message by printing each character as it is decoded.

## File Structure

### MsgTree.java
- `payloadChar`: The character or symbol stored in the node.
- `left`, `right`: References to the left and right children of the node.
- The tree is constructed recursively from the given encoding string.

### ArchMsgDecoder.java
- Reads a file containing the encoding string and the binary message.
- Builds the `MsgTree` from the encoding string.
- Uses the tree to decode the binary message by traversing the tree according to the binary digits ('0' for left, '1' for right).
