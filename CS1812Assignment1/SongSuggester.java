import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class SongSuggester{


    public static boolean askYesNo(String question){
        
        Scanner yesNo = new Scanner(System.in);
        System.out.println(question + "[y/n]");
        String answer = yesNo.next();

        if (answer.equals("y")){ 
            return true;

        }
        return false;
    

    }


    public static void main(String[] args )throws IOException {

        System.out.println("Welcome! Let's get started!");

        TreeNode root;
        try (BufferedReader in = new BufferedReader(new FileReader("suggestions.txt"))){
            root = loadPreorder(in);
        }
        catch(IOException e){
            // create tree contents        
            root = new TreeNode("Do you like classical music? ",
                            new TreeNode ("Are you a fan of Mozart? ",
                                new TreeNode("Eine Kleine Nachtmusik"),
                                new TreeNode("\"3 Romances\" by Clara Schumann")),
                            new TreeNode ("\"Take Five\" by Dave Brubeck"));

        }
        TreeNode current = root;
        boolean again = true;
        while (again){ //while loop for playing the game

            while(current.isLeaf() != true){ // while loop to go through the tree 

                if (askYesNo(current.getValue()) == true){
                current = current.getLeft();
                }
                else{
                    current = current.getRight();
                }
            
            }
            System.out.println(current.getValue()); // outputs last leaf with suggestion
        

            if(askYesNo("Was the game satisfactory? ") != true){

                Scanner song = new Scanner(System.in);
                System.out.println("What song would you prefer instead? ");
                String prefer = song.nextLine();

                System.out.println("Tell me a question that distinguishes "+ current.getValue() + " from "+ prefer);
                String newQ = song.nextLine();
                
                TreeNode newLeaf = new TreeNode (prefer); // adds the new song to the left branch
                TreeNode newRLeaf = new TreeNode(current.getValue()); //adds old song to right branch
                current.setLeft(newLeaf);
                current.setRight(newRLeaf);
                current.setValue(newQ); // replaces old node with the new question
                
            }   

            if(askYesNo("Play again? ")== true){
                current = root;  //replaces the old tree with the new tree + new song
                continue; 
            }
            else{
                break;
            }
        }
            
        if (askYesNo("Do you want to save? ") == true){
                current = root;
                BufferedWriter writer = new BufferedWriter(new FileWriter("suggestions.txt"));
                root.print(writer);
                writer.close();
        }
    
    }
    public static TreeNode loadPreorder(BufferedReader reader)throws IOException{

        String line = reader.readLine();
        TreeNode head = new TreeNode(line.substring(0));
    
        if (line.contains(">")){
          TreeNode left = loadPreorder(reader);
          TreeNode right = loadPreorder(reader);
          head.setLeft(left);
          head.setRight(right);
        }
        return head;
    }    

}