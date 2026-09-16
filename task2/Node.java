public class Node{
    String val;
    Node left;
    Node right;

    public Node(String val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public Node(String val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
}