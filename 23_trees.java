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

            // handle null father
        */
        void printNode(treeNode TNode, BufferedWriter outFile){
            try {
                if(isLeaf(TNode)){
                    if(TNode.father != null){
                        outFile.write("("+TNode.key1+","+TNode.key2+","+"null,null,null,"+TNode.father.key1+")\n");   
                    }else{
                        outFile.write("("+TNode.key1+","+TNode.key2+","+"null,null,null,"+"null)\n");    
    
                    }
                }
                if( TNode.child3 != null && !isLeaf(TNode)){
                    if(TNode.father != null){
                        outFile.write("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+","+TNode.child3.key1+","+TNode.father.key1+")\n");    
    
                    }else{
                        outFile.write("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+","+TNode.child3.key1+","+"null)\n");    
    
                    }
                }
                if( !isLeaf(TNode) && TNode.child3 == null){
                    if(TNode.father != null){
                        outFile.write("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+",null,"+TNode.father.key1+")\n");    
    
                    }else{
                        outFile.write("("+TNode.key1+","+TNode.key2+","+TNode.child1.key1+","+TNode.child2.key1+",null,"+"null)\n");    
    
                    }
                   
                }
                
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println(e.toString());
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

        treeNode newNode1 = new treeNode();
        newNode1.key1 = data1;
        newNode1.father = Root;
        treeNode newNode2 = new treeNode();
        newNode2.key1 = data2;
        newNode2.father = Root;

        Root.child1 = newNode1;
        Root.child2 = newNode2;
        Root.key1 = data2;


        Root.printNode(Root, deBugFile);
        
    }

    //
    void preOrder(treeNode node, BufferedWriter out){
        if(node == null){
            return;
        }

        node.printNode(node, out);

        if(node.child1 != null){
            preOrder(node.child1, out); 
        }

        if(node.child2 != null){
            preOrder(node.child2, out);

        }

        if(node.child3 != null){
            preOrder(node.child3, out);
        }

        return;

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

    void makeNewRoot(treeNode s, treeNode sibling){
        treeNode newRoot = new treeNode();
        newRoot.child1 = s;
        newRoot.child2 = sibling;
        newRoot.key1 = findMinSubtree(newRoot.child1);
        newRoot.key2 = findMinSubtree(newRoot.child2);

        s.father = newRoot;
        sibling.father = newRoot;
        Root = newRoot;

    }

    // TODO
    void treeInsert(treeNode Spot, treeNode newNode){

        // Case 1 if spot has two children
        if(Spot.child1 != null && Spot.child2 != null && Spot.child3 == null){
            treeNode tempLowest = null;
            treeNode tempMiddle = null;
            treeNode tempLast = null;

            treeNode[] tempArr = {Spot.child1, Spot.child2, newNode};

            System.out.println("3 before " + Spot.child1.key1 + "," + Spot.child2.key1 + "," + newNode.key1 + ")");

            for(int y = 0; y < 3; y++){
                // set up highest value
                int lowestValue = Integer.MAX_VALUE;

                // find the lowest value
                for(int x = 0; x < tempArr.length; x++){
                    if(tempArr[x] != null && tempArr[x].key1 < lowestValue){
                        lowestValue = tempArr[x].key1;
                    }
                }

                // find node with lowest value
                treeNode found = null;

                for(int x = 0; x < tempArr.length; x++){
                    if(tempArr[x] != null && tempArr[x].key1 == lowestValue){
                        found = tempArr[x];
                        tempArr[x] = null;
                    }
                }


                // set appopriate temp node
                if(tempLowest == null){
                    tempLowest = found;
                }
                else{
                    if(tempMiddle == null){
                        tempMiddle = found;
                    }
                    else{
                        if(tempLast == null){
                            tempLast = found;
                        }
                    }
                }


            }

            

            /*
                arrange nodes
            */

            Spot.child1 = tempLowest;
            Spot.child2 = tempMiddle;
            Spot.child3 = tempLast;

            Spot.key1 = findMinSubtree(Spot.child2);
            Spot.key2 = findMinSubtree(Spot.child3);

            System.out.println("3 after " + Spot.child1.key1 + "," + Spot.child2.key1 + "," + Spot.child3.key1 + ")");


            if(Spot.father != null){
                if(Spot == Spot.father.child2 || Spot == Spot.father.child3){
                    updateFather(Spot.father);
                }
            }
            

            return;
        }

        // three children
        if(Spot.child1 != null && Spot.child2 != null && Spot.child3 != null){
            treeNode tempLow = null;
            treeNode tempMed1 = null;
            treeNode tempMed2 = null;
            treeNode tempHigh = null;

            treeNode[] tempArr = {Spot.child1, Spot.child2, Spot.child3, newNode};

            System.out.println("4 before " + Spot.child1.key1 + "," + Spot.child2.key1 + "," + Spot.child3.key1 + "," + newNode.key1 + ")");


            for(int y = 0; y < 4; y++){
                int lowestValue = Integer.MAX_VALUE;

                for(int x = 0; x < tempArr.length; x++){
                    if(tempArr[x] != null && tempArr[x].key1 < lowestValue){
                        lowestValue = tempArr[x].key1;
                    }
                }

                treeNode found = null;

                for(int x = 0; x < tempArr.length; x++){
                    if(tempArr[x] != null && tempArr[x].key1 == lowestValue){
                        found = tempArr[x];
                        tempArr[x] = null;
                    }
                }

                if(tempLow == null){
                    tempLow = found;
                }
                else{
                    if(tempMed1 == null){
                        tempMed1 = found;
                    }
                    else{
                        if(tempMed2 == null){
                            tempMed2 = found;
                        }
                        else{
                            if(tempHigh == null){
                                tempHigh = found;
                            }
                        }
                    }
                }
            }


            /*
                arrange node child1, child2, child3 and newNode
            */
            treeNode sibling = new treeNode();
            sibling.father = Spot.father;

            Spot.child1 = tempLow;
            Spot.child2 = tempMed1;
            Spot.child3 = null;

            sibling.child1 = tempMed2;
            sibling.child2 = tempHigh;
            sibling.child3 = null;

        
            Spot.key1 = findMinSubtree(Spot.child2);
            Spot.key2 = -1;

            sibling.key1 = findMinSubtree(sibling.child2);
            sibling.key2 = -1;

            System.out.println("4 after " + Spot.child1.key1 + "," + Spot.child2.key1 + "," + sibling.child1.key1 + "," + sibling.child2.key2 + ")");

            if(Spot.father != null){
                if(Spot == Spot.father.child2 || Spot == Spot.father.child3){
                    updateFather(Spot.father);
                }

            }

            if(sibling.father != null){
                if(sibling == sibling.father.child2 || sibling == sibling.father.child3 ){
                    updateFather(sibling.father);
                }
            }

            if(Spot == Root){
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
       
        if(isLeaf(Spot)){
            System.out.println("You are at leaf level, you are too far down the tree");
            return null;
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

        }else{
            return null;
        }
        return null;
    }

    void updateFather(treeNode fatherNode){
        if(fatherNode == null){
            return;
        }

        fatherNode.key1 = findMinSubtree(fatherNode.child2);
        fatherNode.key2 = findMinSubtree(fatherNode.child3);

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
        BufferedWriter treeFile = null;
    
        try{
            debugFile = new BufferedWriter( new FileWriter(args[1]));
            treeFile = new BufferedWriter(new FileWriter(args[2]));
            inFile = new Scanner( new BufferedReader(new FileReader(args[0])));
    
        } catch(Exception e){
            System.out.println(e.toString());
        }
    
    
        myTree.initialTree(inFile, debugFile);
    
        while(inFile.hasNext()){
            int data = inFile.nextInt();
    
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
    
            tree_23.treeNode newNode = myTree.new treeNode();
            newNode.key1 = data;
            newNode.key2 = -1;
            newNode.child1 = null;
            newNode.child2 = null;
            newNode.child3 = null;
            newNode.father = null;
            
            if(spot != null){
                myTree.treeInsert(spot, newNode);
            }
            
    
            myTree.preOrder(myTree.Root, debugFile);
    
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



