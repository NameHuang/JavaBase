package simple.javase;

/**                                                                     
* Comments:  <平衡二叉树，在已平衡的平衡二叉树中或空平衡二叉树中，进行插入、删除操作时，会进行带条件的比较和单或双旋转，
* 进而实现自平衡，该树支持结点查询、前中后层序遍历等操作>       
* JDK version used:      <JDK1.8>                                                              
* Author：        <黄筱>                  
* Create Date：  <2017-12-20>                          
*/   
public class AVLTree<AnyType extends Comparable<? super AnyType>> {
    
	/**
	 * @Description 用私有的静态内部类实现平衡二叉树的结点，每个节点可以是任意任意类型，且每个节点可能有左右孩子节点
	 */
    private static class BinaryNode<AnyType>{
    	
    	 public  AnyType theElement;   //节点数据
    	 public BinaryNode<AnyType> left;   //左孩子
    	 public BinaryNode<AnyType> right;
    	 public int height;  //节点在树中的高度
    	 
    	 /**
    	  * @Description 构造一个任意类型的不带左右孩子结点的结点
    	  * */
         @SuppressWarnings("unused")
		BinaryNode (AnyType theElement){
        	 this(theElement,null,null);
         }   	
         
         /**
          * @Description 构造一个任意类型的带左右孩子节点的节点
          * */
         BinaryNode(AnyType theElement,BinaryNode<AnyType> left,BinaryNode<AnyType> right){
        	 this.theElement=theElement;
        	 this.left=left;
        	 this.right=right;
        	 height=0;
         }
    }
    
    private BinaryNode<AnyType> root;
    
    /**
     * @Description 构造一个空树
     * */
    public AVLTree() {
    	this.root=null;
    }
    
    /**
     * @param t  获取该t节点在树种的高度
     * @return 返回该节点在数中的高度
     * */
    public int getHeight(BinaryNode<AnyType> t) {
    	return t==null?-1:t.height;
    }
    
    /**
     * @param x 将该节点插入树中，插入时会进行自平衡
     * */
    public void insert(AnyType x) {
    	root=this.insert(x,root);
    }
    /**
     * @param x 要插入的节点
     * @param t 根节点
     * @Descrip 该方法在插入节点时首先会与根节点进行比较，小与根节点时与根节点的左子树的节点比较，直到遇见空节点，反之，右子树
     * @return 返回插入平衡后的根节点
     * */
   private  BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){
    	if(t==null) {
    		return new BinaryNode<>(x,null,null);
    	}
    	int compareResult=x.compareTo(t.theElement);
    	if(compareResult<0) {
    		t.left=insert(x,t.left);
    	}
    	else if(compareResult >0) {
    		t.right=insert(x,t.right);
    	}
    	return balance(t);	   // 平衡方法，对插入节点后的树进行平衡操作
    }
     
    private static final int ALLOWED_BALANCE=1; //平衡二叉树的左右子树高度最多相差 1
    
    /**
     * @param t 对t
     * @return 返回平衡后整棵树的根结点
     * */
    private BinaryNode<AnyType> balance(BinaryNode<AnyType> t){
    	if(t==null) {
    		return t;
    	}
    	if(this.getHeight(t.left)-this.getHeight(t.right)>ALLOWED_BALANCE) {
    		if(this.getHeight(t.left.left)>=this.getHeight(t.left.right)) {   //进行LL型调整
    			t=rotateWithLeftChild(t);  //进行单旋转
    		}else {    //进行LR型调整
    			t=this.doubleWithLeftChild(t);  //进行双旋转
    		}
    	}else if(this.getHeight(t.right)-this.getHeight(t.left)>ALLOWED_BALANCE) {
    		if(this.getHeight(t.right.right)>=this.getHeight(t.right.left)) {  //进行RR型调整
    			t=this.rotateWithLeftChild(t);
    		}else {    //进行RL型调整
    			t=this.doubleWithLeftChild(t);
    		}
    	}
    	t.height=Math.max(this.getHeight(t.left),this.getHeight(t.right))+1;   //插入后更新该树的高度
    	return t;
    }
    
    /**
     * @param k2 对K2结点进行单旋转
     * @return 返回单旋转后的根节点
     * */
    private BinaryNode<AnyType> rotateWithLeftChild(BinaryNode<AnyType> k2){
    	BinaryNode<AnyType> k1=k2.left;
    	k2.left=k1.right;
    	k1.right=k2;
    	k2.height=Math.max(this.getHeight(k2.left), this.getHeight(k2.right))+1;
    	k1.height=Math.max(this.getHeight(k1.left), k2.height)+1;
    	return k1;
    }
    
    /**
     * @param k3 对K3结点进行双旋转
     * @return 返回双旋转后的根节点
     * */
    private BinaryNode<AnyType> doubleWithLeftChild(BinaryNode<AnyType> k3){
        k3.left=this.rotateWithLeftChild(k3.left);
        return this.rotateWithLeftChild(k3);
    }
    
    /**
     * @param x 删除x结点
     * */
    public void remove(AnyType x) {
    	root=this.remove(x, root);
    }
    /**
     * @param x 要删除的值与X相等的结点
     * @param t  当前根节点（双亲节点）
     * @Description 删除该结点，并对该树进行再次平衡
     * */
    private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
    	if(t==null) {
    		return t;
    	}
    	int compareResult=x.compareTo(t.theElement);
    	if(compareResult<0) {
    		t.left=this.remove(x, t.left);
    	}else if(compareResult>0) {
    		t.right=this.remove(x, t.right);
    	}else if(t.left!=null&&t.right!=null) { //找到树中结点值与X相等的结点，并且该结点左右子树都不为空
    		t.theElement=findMin(t.right).theElement;   //当前该结点的数据域替换成该结点右子树中最小结点的数据域
    		t.right=this.remove(t.theElement,t.right);   //删除该节点中右子树中最小结点在右子树中原来的结点
    	}else {
    		t=(t.left!=null) ?t.left : t.right;  //删除x结点时，该结点无子树或只有左右子树中的一颗
    	}
    	return this.balance(t);  //删除结点后对整棵树进行平衡操作，并返回新的根节点
    }
    
    /**
     * @param t 双亲结点（根节点）
     * @description 找到该t结点为根节点的树中最小的节点
     * */
    public BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
    	if(t==null) {
    		return null;
    	}else if(t.left==null) {
    		return t;
    	}
    	return findMin(t.left);
    }
    
    /**
     * @param x 查找值为X的节点
     * @return 返回值为X的结点
     * 
     * */
    public BinaryNode<AnyType> searchNode(AnyType x){
    	return this.searchNode(x,root);
    }
    /**
     * @param x 查找值为X的结点
     * @param t 双亲结点（根节点）
     * */
	@SuppressWarnings("unused")
	private BinaryNode<AnyType> searchNode(AnyType x,BinaryNode<AnyType> t){
    	int compareResult=x.compareTo(t.theElement);
    	if(t==null) {
    		return null;
    	}else if(compareResult==0) {
    		return t;
    	}else if(compareResult>0) {
    		return searchNode(x,t.right);
    	}else {
    		return searchNode(x,t.left);
    	}
    }
	
	/**
	 * @description 对当前整棵树进行前序遍历 
	 * */
    public void preorder() {
    	this.preorder(root);
    }
    private void preorder(BinaryNode<AnyType> t) {
    	if(t==null) {
    		return;
    	}else {
    		System.out.print(t.theElement.toString());
    		this.preorder(t.left);
    		this.preorder(t.right);
    	}
    }
    
    /**
     * @description 对当前整棵树进行中序遍历
     * */
    public void inorder() {
    	this.inorder(root);
    }
    private void inorder(BinaryNode<AnyType> t) {
    	if(t==null) {
    		return;
    	}else {
    		this.inorder(t.left);
    		System.out.print(t.theElement.toString());
    		this.inorder(t.right);
    	}
    }
    
    /**
     * @description 对当前整棵树进行后序遍历
     * */
    public void postorder() {
    	this.postorder(root);
    }
    private void postorder(BinaryNode<AnyType> t) {
    	if(t==null) {
    		return;
    	}else {
    		this.postorder(t.left);
    		this.postorder(t.right);
    		System.out.print(t.theElement.toString());
    	}
    }
    
    /**
     * @description 对当前整棵树进行层序遍历
     * */
    public void leverorder() {
    	this.leverorder(root);
    }
	private void leverorder(BinaryNode<AnyType> t) {
    	int front=-1,rear=-1;
    	@SuppressWarnings("unchecked")
		BinaryNode<AnyType>[] q =new BinaryNode[1024]  ;  //初始队列长为100
    	if(t==null) { 
    		return;
    	}
    	rear++;
        q[rear]=t;
    	while(front!=rear) {
    		if(rear==q.length) {   //当尾指针到达队列末位置
    			rear=(rear+1)%q.length;  //对队尾指针进行循环意义下+1
    		}
    		else if(front==q.length) {   //当队头指针到达队列末位置
    			front=(front+1)%q.length;   //对队头尾指针进行循环意义下+1
    		}
    		else if((rear+1)%q.length==front) {  //当队列满
				this.ensureCapacity(q);   //进行队列队长的扩充
			}
    		front++;	
			BinaryNode<AnyType> n=q[front];
    		if(n!=null) {  
    			System.out.print(n.theElement.toString());	
    		    if(t.left!=null) {
    			    rear++;
    			    q[rear]=n.left;
    		    }
    		    if(t.right!=null) {
    		 	    rear++;
    			    q[rear]=n.right;
    		    }
    		}
    	}
    }

	/**
	 * @param q 对原数组的数组最大长度进行扩充
	 * */
	@SuppressWarnings("unchecked")
	private void ensureCapacity(BinaryNode<AnyType>[] q) {
		int newSize=q.length*2+1;
		int oldSize=q.length;
		BinaryNode<AnyType>[] old=q;
		q= new BinaryNode [newSize];
		for(int i=0;i<oldSize;i++) {
			q[i]=old[i];
		}
	}
	
}
