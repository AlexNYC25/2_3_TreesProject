import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


class tree_23{

    class treeNode {
        int key1;
        int key2;
        treeNode child1;
        treeNode child2;
        treeNode child3;
        treeNode father;

        public treeNode(){
            // initial value of keys
            key1 = -1;
            key2 = -1;

            // initial tree node values
            child1 = null;
            child2 = null;
            child3 = null;

            father = null;
        }

        /*
            finished needs testing
        */
        void printNode(treeNode TNode, BufferedWriter outFile){
            if(TNode.child1 == null && TNode.child2 == null && TNode.child3 == null){
                System.out.println("("+TNode.key1+","+TNode.key2+","+"null,null,null,"+TNode.father.key1);    
            }
            if( (TNode.child1 != null && TNode.child2 != null) && TNode.child3 != null){
                System.out.println("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+","+TNode.child3.key1+","+TNode.father.key1);    
            }
            if((TNode.child1 != null && TNode.child2 != null) && TNode.child3 != null){
                System.out.println("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+",null,"+TNode.father.key1);    
               
            }
        }


    }

    // tree root local to tree object
    public treeNode Root = null;

    // constructor for 2-3 tree
    tree_23(){
        // initialize 
        Root = new treeNode();

    }



    void swap(){

    }

    /*
        Get the first two data items to build the initial 2 nodes tree

        finished needs testing
    */
    void initialTree(Scanner inFile, BufferedWriter deBugFile){
        // Root is already initilized by constructor 
        int data1 = inFile.nextInt();
        int data2 = inFile.nextInt();

        if(data2 < data1){
            // swap data2 and data 1;
            int temp = data1;
            data1 =  data2;
            data2 = temp;
        }

        treeNode newNode1 = new treeNode();
        newNode1.key1 = data1;
        treeNode newNode2 = new treeNode();
        newNode2.key1 = data2;

        Root.child1 = newNode1;
        Root.child2 = newNode2;
        Root.key1 = data2;


        Root.printNode(Root, deBugFile);
        
    }

    //
    void preOrder(BufferedWriter out){

    }

    /*
        finished needs testing
    */
    boolean isLeaf(treeNode node){
        if(node.child1 != null && node.child2 != null && node.child3 != null){
            return false;
        }
        
        return true;
    }

    /*
        finsihed needs testing
    */
    boolean isRoot(treeNode node){
        if(node.father == null){
            return true;
        }

        return false;
    }

    // TODO
    void treeInsert(treeNode Spot, treeNode newNode){
        // Case 1 if spot has two children
        if(Spot.child1 != null && Spot.child2 != null){
            treeNode tempLowest = null;
            treeNode tempMiddle = null;
            treeNode tempLast = null;

            /*
                arrange nodes
            */

            Spot.child1 = tempLowest;
            Spot.child2 = tempMiddle;
            Spot.child3 = tempLast;

            Spot.key1 = findMinSubtree(Spot.child2);
            Spot.key2 = findMinSubtree(Spot.child3);

            if(Spot == Spot.father.child2 || Spot == Spot.father.child3){
                updateFather(Spot.father);
            }

            return;
        }

        if(Spot.child1 != null && Spot.child2 != null && Spot.child3 != null){
            /*
                arrange node child1, child2, child3 and newNode
            */

            /*
                divide nodes into two groups A and B
            */

            /*

            */
        }

    }

    treeNode findSpot(treeNode Spot, int data){

        if(isLeaf(Spot.child1)){
            return Spot;
        }

        if(!isLeaf(Spot)){
            if(data == Spot.key1 || data == Spot.key2){
                return null;
            }

            if(data < Spot.key1){
                return findSpot(Spot.child1, data);
            }

            if(Spot.key2 == -1 || data < Spot.key2){
                return findSpot(Spot.child2, data);
            }

            if(Spot.key2 != -1 && data == Spot.key2){
                return findSpot(Spot.child3, data);
            }
        }

        return null;
    }

    void updateFather(treeNode fatherNode){
        if(fatherNode == Root){
            return;
        }

        fatherNode.key1 = findMinSubtree(fatherNode.child2);
        fatherNode.key2 = findMinSubtree(fatherNode.child1);

        updateFather(fatherNode.father);

    }

    int findMinSubtree(treeNode node){

        if(node == null){
            return -1;
        }

        if(isLeaf(node)){
            return node.key1;
        }
        else{
            return findMinSubtree(node.child1);
        }

    }

    void leafInsert(treeNode Spot, treeNode newNode){

    }


}

class project3{
    public static void main(String[] args){
        

        tree_23 myTree = new tree_23();
        /*
            inFile <- args[0]
            debugFile <- args[1]
            treeFile <- args[2]
        */
        Scanner inFile = null;
        BufferedWriter debugFile = null;
    
        try{
            debugFile = new BufferedWriter( new FileWriter(args[1]));
    
            inFile = new Scanner( new BufferedReader(new FileReader(args[0])));
    
        } catch(Exception e){
            System.out.println(e.toString());
        }
    
        BufferedWriter deBugFile = null;

        /*
    
        Root.initialTree(inFile, deBugFile, Root);
    
        while(inFile.hasNext()){
            int data = inFile.nextInt();
    
            treeNode spot = Root.findSpot(Root, data);
    
            if(spot == null){
                System.out.println("data is in the database, no need to insert");
            }
            else{
                System.out.println("spot data: ");
                spot.printNode(spot, debugFile);
            }
    
            treeNode newNode = new treeNode();
            newNode.key1 = data;
            newNode.key2 = -1;
            newNode.child1 = null;
            newNode.child2 = null;
            newNode.child3 = null;
            
            Root.treeInsert(spot, newNode);
    
            Root.preOrder(deBugFile);
    
        }
    
        Root.preOrder(debugFile);

        */
    
        inFile.close();
        try{
            debugFile.close();
        } catch(Exception e){
    
        }
        
    
    
    }
    
}



