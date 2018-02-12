package simple.javase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListSetMap {
    
	public void listReview() {
		ArrayList<Integer> arrayList=new ArrayList<>();
        LinkedList<Integer> linkedList=new LinkedList<>();
        for(int i=0;i<10;i++) {
        	arrayList.add(i);
        	linkedList.add(i);
        }
        /*
         * ListIterator能从后向前迭代，但是创建迭代器对象时必须把迭代起始位置指定为集合尾部
         * 如： ListIterator<Integer> it1=arrayList.listIterator(arrayList.size());
         * 然后用hasPrevious()方法判断，逆向遍历集合
         * 该迭代器还有add()方法可以向集合中增加元素
         * 改迭代器只能用于List集合
         * */
       ListIterator<Integer> it1=arrayList.listIterator();
        ListIterator<Integer> it2=linkedList.listIterator();
        while(it1.hasNext()) {
        	int arrayCount=it1.next();
        	if(arrayCount==5) {
        		it1.remove();
        	}else {
        		 arrayCount=arrayCount+10;
        		 it1.remove();
        		 it1.add(arrayCount);
        	}
        }
         System.out.println(arrayList);    
	}

    public void setReview() {
    	
    }
	
	public static void main(String[] args) {
        
    }
}