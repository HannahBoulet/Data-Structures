// Don't Edit this class
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BinaryTree {
    private BinaryNode root;
    private HashMap<String, BinaryNode> nodes;
    private ArrayList<String[]> fileLinesToProcess;
    
    public BinaryTree(){
        this.nodes = new HashMap<>(); 
        Scanner kb = new Scanner(System.in);
        this.fileLinesToProcess = customRead(kb);
        if(this.fileLinesToProcess.size() != 0){
            int i = 0;
            String[] shouldBeRoot = this.fileLinesToProcess.get(0);
            this.root = new BinaryNode(shouldBeRoot[0], null, null, 0, 0);
            this.nodes.put(shouldBeRoot[0], this.root);
            while(this.fileLinesToProcess.size() != i){
                String[] line = this.fileLinesToProcess.get(i);
                BinaryNode tnode = this.nodes.getOrDefault(line[0], null);
                BinaryNode othernode = this.nodes.getOrDefault(line[1], null);

                if(tnode == null){
                    tnode = new BinaryNode(line[0], null, null, 0, 0);
                }

                if(othernode == null){
                    othernode = new BinaryNode(line[1], null, null, 0, 0);
                }

                this.nodes.put(line[0], tnode);
                this.nodes.put(line[1], othernode);

                if(tnode.getLeftChild() == null){
                    tnode.setLeftChild(othernode);
                    tnode.setLeftWeight(Integer.parseInt(line[2]));
                }else{
                    tnode.setRightChild(othernode);
                    tnode.setRightWeight(Integer.parseInt(line[2]));
                }

                i++;
            }
        }
    }

    private ArrayList<String[]> customRead(Scanner fileInp){
        ArrayList<String[]> output = new ArrayList<>();
        while(fileInp.hasNext()){
            String[] nums = fileInp.nextLine().split(" ");
            output.add(nums);
        }
        return output;
    }

    public BinaryNode getRoot(){
        return this.root;
    }

    public static void turnBSTtoString(BinaryNode root){
        if(root == null) return;

        if(root.getLeftChild() != null){
            System.out.println(root.getLabel() + " " + root.getLeftChild().getLabel() + " " + root.getLeftWeight());
        }
        if(root.getRightChild() != null){
            System.out.println(root.getLabel() + " " + root.getRightChild().getLabel() + " " + root.getRightWeight());
        }

        turnBSTtoString(root.getLeftChild());
        turnBSTtoString(root.getRightChild());

        return;
    }
}
