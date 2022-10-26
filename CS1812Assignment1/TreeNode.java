import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TreeNode{


    private String value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode( String value){
        this.value = value;
        this.left = null;
        this.right = null;

    }
    public TreeNode( String value, TreeNode left, TreeNode right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public void setValue(String newValue){

        this.value = newValue;
    }
    public void setLeft(TreeNode newLeft){

        this.left = newLeft;
    }
    public void setRight(TreeNode newRight){

        this.right = newRight;
    }
    public String getValue(){
        return this.value;
    }

    public TreeNode getLeft(){
        return this.left;
    }
    public TreeNode getRight(){
        return this.right;

    }
    public boolean isLeaf(){

        if (this.getLeft() == null){

            return true;

        }
        if(this.getRight() == null)
        {
            return true;
        }
        return false;
    }
    public String print(BufferedWriter writer)throws IOException{
        if(this.isLeaf() != true){
            writer.write(">");
        }
        writer.write((this.getValue()) + "\n");
        if (getLeft() !=null){
            getLeft().print(writer);
        }
        if (getRight() !=  null){
            getRight().print(writer);
        }
        return null;
    }
    
    
        
}