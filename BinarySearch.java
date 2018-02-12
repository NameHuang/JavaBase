package simple.javase;

import org.apache.log4j.Logger;

public class BinarySearch {
	private static Logger log=Logger.getLogger(BinarySearch.class);
	private int mid;
    public int binarySearch(int r[],int low,int high,int k) {
    	if(low>high) {
    		return -1;
    	}else {
    		mid=(low+high)/2;
    		if(k<r[mid]) {
    			return binarySearch(r,low,mid-1,k);
    		}else if(k>r[mid]){
    			return binarySearch(r,mid+1,high,k);
    		}else {
    			return mid;
    		}
    	}
    }
	public static void main(String[] args) {
        int [] a=new int[100];
        for(int i=0;i<100;i++) {
        	a[i]=i;
        }
        int index=new BinarySearch().binarySearch(a,0,99,17);
        log.debug(index);
        System.out.println(index);
	}

}
