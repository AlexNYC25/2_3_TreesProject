/*
    Project 5: 2-3 trees 
    By Alexis Montes

*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;



class tree_23{

    /*
        inner class for tree node to be used as actual tree node for 2-3 tree structure
    */
    class treeNode {
        // key values
        int key1;
        int key2;
        // tree node values
        treeNode child1;
        treeNode child2;
        treeNode child3;
        treeNode father;

        // constructor settting default values
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

            // handle null father
        */
        void printNode(treeNode TNode, BufferedWriter outFile){
            String printString = "";

            printString += "("+TNode.key1+","+TNode.key2+",";
            if(TNode.child1 != null){
                printString += TNode.child1.key1+",";
            }
            else{
                printString += "null,";
            }

            if(TNode.child2 != null){
                printString += TNode.child2.key1+",";
            }
            else{
                printString += "null,";
            }

            if(TNode.child3 != null){
                printString += TNode.child3.key1+",";
            }
            else{
                printString += "null,";
            }

            if(TNode.father != null){
                printString += TNode.father.key1 + ")\n";
            }
            else{
                printString += "null)\n";
            }

            
            try{
                outFile.write(printString);
            } catch( Exception e){
                System.out.println(e.toString());
            }
/*
            try {
                if(isLeaf(TNode)){

                    outFile.write("("+TNode.key1+","+TNode.key2+","+"null,null,null,"+TNode.father.key1+")\n");
                    return;  

                }
                if( TNode.child3 != null && !isLeaf(TNode)){
                    if(TNode.father != null){
                        outFile.write("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+","+TNode.child3.key1+","+TNode.father.key1+")\n");
                        return;    
    
                    }else{
                        outFile.write("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+","+TNode.child3.key1+","+"null)\n");    
                        return;
    
                    }
                }
                if( !isLeaf(TNode) && TNode.child3 == null){
                    if(TNode.father != null){
                        outFile.write("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+",null,"+TNode.father.key1+")\n");   
                        return; 
    
                    }else{
                        outFile.write("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+",null,"+"null)\n"); 
                        return;   
    
                    }
                   
                }
                
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println();
            }
            */
            
        }


    }

    // tree root local to tree object
    public treeNode Root = null;

    // constructor for 2-3 tree
    tree_23(){
        // initialize 
        Root = new treeNode();

    }


    // did swaping in function
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

        // create new data nodes
        treeNode newNode1 = new treeNode();
        newNode1.key1 = data1;
        newNode1.father = this.Root;
        treeNode newNode2 = new treeNode();
        newNode2.key1 = data2;
        newNode2.father = this.Root;

        Root.child1 = newNode1;
        Root.child2 = newNode2;
        Root.key1 = data2;


        Root.printNode(this.Root, deBugFile);
        
    }

    // pre order function to print data
    void preOrder(treeNode node, BufferedWriter out){
        if(node == null){
            return;
        }

        if(isLeaf(node)){
            node.printNode(node, out);
            return;
        }

        if(node.child1 != null && node.child2 != null && node.child3 == null){
            preOrder(node.child1, out);
            node.printNode(node, out);
            preOrder(node.child2, out);
            return;
        }

        if(node.child1 != null && node.child2 != null && node.child3 != null){
            preOrder(node.child1, out);
            node.printNode(node, out);
            preOrder(node.child2, out);
            preOrder(node.child3, out);
            return;
        }       



    }

    /*
        finished needs testing
    */
    boolean isLeaf(treeNode node){
        /*
            if the node has any children then it is not a leaf
        */
        if(node.child1 != null && node.child2 != null && node.child3 != null ){
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

    void makeNewRoot(treeNode s, treeNode sibling){
        // new root 
        treeNode newRoot = new treeNode();

        if(s.key1 < sibling.key1){
            s.father = newRoot;
            sibling.father = newRoot;

            newRoot.child1 = s;
            newRoot.child2 = sibling;
            newRoot.key1 = findMinSubtree(newRoot.child2);
            newRoot.key2 = -1;
        }
        if(s.key1 >= sibling.key1){
            s.father = newRoot;
            sibling.father = newRoot;

            newRoot.child1 = sibling;
            newRoot.child2 = s;

            newRoot.key1 = findMinSubtree(newRoot.child2);
            newRoot.key2 = -1;
        }

        

        
        this.Root = newRoot;

    }

    // bubble sort helper function to sort array of treeNodes from smallest to largest
    void bubbleTreeSort(treeNode[] tree){
        int size = tree.length;

        for (int i = 0; i < size; i++) 
            for (int j = 0; j < size-i-1; j++) 
                if (tree[j].key1 > tree[j+1].key1) 
                { 
                    // swap arr[j+1] and arr[j] 
                    treeNode temp = tree[j]; 
                    tree[j] = tree[j+1]; 
                    tree[j+1] = temp; 
                } 
    }

    // 
    void treeInsert(treeNode Spot, treeNode newNode){

        // Case 1 if spot has two children
        if(Spot.child1 != null && Spot.child2 != null && Spot.child3 == null){

            treeNode[] tempArr = {Spot.child1, Spot.child2, newNode};

            // sort array
            bubbleTreeSort(tempArr);

            //System.out.println("3 before " + Spot.child1.key1 + "," + Spot.child2.key1 + "," + newNode.key1 + ")");

            /*
                arrange nodes
            */

            Spot.child1 = tempArr[0];
            Spot.child2 = tempArr[1];
            Spot.child3 = tempArr[2];

            Spot.child1.father = Spot;
            Spot.child2.father = Spot;
            Spot.child3.father = Spot;

            Spot.key1 = findMinSubtree(Spot.child2);
            Spot.key2 = findMinSubtree(Spot.child3);

            //System.out.println("3 after " + Spot.child1.key1 + "," + Spot.child2.key1 + "," + Spot.child3.key1 + ")");


            if(Spot.father != null){
                if(Spot.key1 == Spot.father.child2.key1 || Spot.key1 == Spot.father.child3.key1){
                    updateFather(Spot.father);
                }
            }
            

            return;
        }

        // three children
        if(Spot.child1 != null && Spot.child2 != null && Spot.child3 != null){
            

            treeNode[] tempArr = {Spot.child1, Spot.child2, Spot.child3, newNode};

            // sort arr
            bubbleTreeSort(tempArr);

            //System.out.println("4 before " + Spot.child1.key1 + "," + Spot.child2.key1 + "," + Spot.child3.key1 + "," + newNode.key1 + ")");

            /*
                arrange node child1, child2, child3 and newNode
            */
            treeNode sibling = new treeNode();
            sibling.father = Spot.father;

            Spot.child1 = tempArr[0];
            Spot.child2 = tempArr[1];
            Spot.child3 = null;

            Spot.child1.father = Spot;
            Spot.child2.father = Spot;

            sibling.child1 = tempArr[2];
            sibling.child2 = tempArr[3];
            sibling.child3 = null;

            sibling.child1.father = sibling;
            sibling.child2.father = sibling;

        
            /*
                set new key values
            */  
            Spot.key1 = findMinSubtree(Spot.child2);
            Spot.key2 = -1;

            sibling.key1 = findMinSubtree(sibling.child2);
            sibling.key2 = -1;

            //System.out.println("4 after " + Spot.child1.key1 + "," + Spot.child2.key1 + "," + sibling.child1.key1 + "," + sibling.child2.key1 + ")");

            if(Spot.father != null){
                if(Spot.key1 == Spot.father.child2.key1 || Spot.key1 == Spot.father.child3.key1){
                    updateFather(Spot.father);
                }

            }

            if(sibling.father != null){
                if(sibling.key1 == sibling.father.child2.key1 || sibling.key1 == sibling.father.child3.key1 ){
                    updateFather(sibling.father);
                }
            }

            if(Spot.key1 == Root.key1){
                // pass old root and new sibling
                makeNewRoot(Spot, sibling);
            }
            else{
                treeInsert(Spot.father, sibling);

            }

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

            if(Spot.key2 != -1 && data >= Spot.key2){
                return findSpot(Spot.child3, data);
            }

        }



       
        if(isLeaf(Spot)){
            System.out.println("You are at leaf level, you are too far down the tree");
            return null;
        }



        return null;
    }

    /*
        function to update key values
    */
    void updateFather(treeNode fatherNode){
        if(fatherNode == null){
            return;
        }

        fatherNode.key1 = findMinSubtree(fatherNode.child2);
        fatherNode.key2 = findMinSubtree(fatherNode.child3);

        if(fatherNode.father != null){
            updateFather(fatherNode.father);
        }
        

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

   

}

class project5{
    public static void main(String[] args){
        

        tree_23 myTree = new tree_23();
        /*
            inFile <- args[0]
            debugFile <- args[1]
            treeFile <- args[2]
        */
        // txt file objects
        Scanner inFile = null;
        BufferedWriter debugFile = null;
        BufferedWriter treeFile = null;
    
        // attempting to open files
        try{
            debugFile = new BufferedWriter( new FileWriter(args[1]));
            treeFile = new BufferedWriter(new FileWriter(args[2]));
            inFile = new Scanner( new BufferedReader(new FileReader(args[0])));
    
        } catch(Exception e){
            //System.out.println(e.toString());
        }
    
    
        myTree.initialTree(inFile, debugFile);
    
        // while integers are still in data file
        while(inFile.hasNext()){
            // get actual data int
            int data = inFile.nextInt();
    
            // find the best spot for insertion
            tree_23.treeNode spot = myTree.findSpot(myTree.Root, data);
    
            if(spot == null){
                System.out.println("data is in the database, no need to insert");
            }
            else{
                try {
                    debugFile.write("\nspot data: \n");
                    spot.printNode(spot, debugFile); 
                } catch (Exception e) {
                    //TODO: handle exception
                }
                
            }
    
            // create new node with appropiate values
            tree_23.treeNode newNode = myTree.new treeNode();
            newNode.key1 = data;
            newNode.key2 = -1;
            newNode.child1 = null;
            newNode.child2 = null;
            newNode.child3 = null;
            newNode.father = null;
            
            // insert value
            if(spot != null){
                myTree.treeInsert(spot, newNode);
                //myTree.preOrder(myTree.Root, debugFile);
            }
            
    
            
    
        }
    
        myTree.preOrder(myTree.Root, treeFile);

        
    
        inFile.close();
        try{
            debugFile.close();
            treeFile.close();
        } catch(Exception e){
    
        }
        
    
    
    }
    
}



