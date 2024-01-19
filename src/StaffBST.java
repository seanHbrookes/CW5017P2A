public class StaffBST implements IStaffDB{
    private class Node {
        Employee data;
        StaffBST.Node left, right;
        private Node(Employee m) { data = m; left = null; right = null; }
    }
    private StaffBST.Node root; // this is the tree
    private int numEntries;
    private boolean firstInsert;

    public StaffBST() {
        System.out.println("Binary Search Tree");
        clearDB();
    }

    @Override
    public void clearDB() {
        assert root != null && numEntries == 0:"Tree is already empty";
        root = null; // garbage collector will tidy up
        numEntries = 0;
    }

    @Override
    public boolean containsName(String name){
        assert name != null || !(name.isEmpty()):"The string is empty";
        return get(name) != null;
    }

    private Employee retrieve (StaffBST.Node tree, String name) {
        Employee returned;
        if(name.compareTo(tree.data.getName()) == 0){
            System.out.println("Getting " + tree.data.getName());
            return tree.data;
        }
        else if(name.compareTo(tree.data.getName()) < 0){
            System.out.println("Visiting node " + tree.data.getName());
            returned = retrieve(tree.left, name);
        }
        else{
            System.out.println("Visiting node " + tree.data.getName());
            returned = retrieve(tree.right, name);
        }
        return returned;
    }

    @Override
    public Employee get(String name){
        assert name != null || !(name.isEmpty()):"The string is empty";
        return retrieve(root, name);
    }

    @Override
    public int size() {
        return numEntries;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private StaffBST.Node insert(StaffBST.Node tree, Employee employee) {
        if(tree == null){
            StaffBST.Node newNode = new Node(employee);
            System.out.println("Employee added: " + employee.getName());
            numEntries++;
            return newNode;
        }

        if(employee.getName().compareTo(tree.data.getName()) == 0){
            System.out.println("Visiting node " + tree.data.getName());
            tree.data = employee;

        }
        else if(employee.getName().compareTo(tree.data.getName()) < 0){
            System.out.println("Visiting node " + tree.data.getName());
            tree.left = insert(tree.left, employee);

        }

        else if(employee.getName().compareTo(tree.data.getName()) > 0){
            System.out.println("Visiting node 2" + tree.data.getName());
            tree.right = insert(tree.right, employee);

        }
        return tree;
    }

    public Employee put(Employee member){
        assert member != null || (member.getName() != null || !member.getName().isEmpty()):"Member is not valid";
        System.out.println("Trying to insert " + member.getName());
        Employee returned;
        returned = null; // for now
        root = insert(root, member);
        return returned;
    }


    @Override
    public  Employee remove(String name){
    /* based on Object-Oriented Programming in Oberon-2
       Hanspeter Mössenböck Springer-Verlag 1993, page 78
       transcribed into Java by David Lightfoot
    */
        assert name != null || !(name.isEmpty()):"The string is empty";
        System.out.println("Trying to delete " + name);
        StaffBST.Node parent = null, del, p = null, q = null;
        Employee result;
        del = root;
        while (del != null && !del.data.getName().equals(name)) {
            parent = del;
            if (name.compareTo(del.data.getName()) < 0)
                del = del.left;
            else
                del = del.right;
        }// del == null || del.data.getName().equals(name))
        if(del != null) { // del.data.getName().equals(name)
            // find the pointer p to the node to replace del
            if (del.right == null) p = del.left;
            else if (del.right.left == null) {
                p = del.right; p.left = del.left;
            } else {
                p = del.right;
                while (p.left != null) {q = p; p = p.left;}
                q.left = p.right; p.left = del.left; p.right = del.right;
            }
            if(del == root) root = p;
            else if (del.data.getName().compareTo(parent.data.getName()) < 0)
                parent.left = p;
            else parent.right = p;
            numEntries--;
            result = del.data;
        }
        else result = null;
        return result;
    } // delete


    private void traverse(StaffBST.Node tree) {
        if(tree != null){
            traverse(tree.left);
            System.out.println(tree.data.getName());
            traverse(tree.right);
        }
    }


    @Override
    public void displayDB(){
        traverse(root);
    }
}
