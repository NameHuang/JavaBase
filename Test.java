package simple.javase;

public class Test {

	public static void main(String[] args) {
        AVLTree<Integer> tree=new AVLTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(3);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(1);
        tree.insert(9);
        
        System.out.print("前序遍历：");
        tree.preorder();
        System.out.println();
        
        System.out.print("中序遍历：");
        tree.inorder();
        System.out.println();
        
        System.out.print("后序遍历：");
        tree.postorder();
        System.out.println();
        
        System.out.print("层序遍历：");
        tree.leverorder();
        System.out.println();
        
        System.out.print("删除值为3的结点后再进行层序遍历:");
        tree.remove(3);
        tree.leverorder();
        
        System.out.println();
        System.out.print("查询值为7的结点：");
        System.out.println(tree.searchNode(7));
        
	}

}
