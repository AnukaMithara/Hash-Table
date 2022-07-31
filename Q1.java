import java.util.*;

/*class for get anode*/
class Node
{
    Node nextAddr;
    int data; 
   
    public Node(int x)
    {
        data = x;
        nextAddr = null;
    }
}
 
// Class HashTable 
class HashTable
{
     Node[] hTable;
     int size ;
	 int maxVal = 0;
	 
    
    public HashTable(int tableSize)         
    {
        hTable = new Node[ tableSize ];
        size = 0;
    }
    
  /*Method to insert a new node in the hash table*/  
    public void insert(int val)             
    {
        size++;
        int p = myhash(val);        
        Node nptr = new Node(val);                
        if (hTable[p] == null)
            hTable[p] = nptr;            
        else
        {
            nptr.nextAddr = hTable[p];
            hTable[p] = nptr;
        }    
    }
   
/*Method for get the hash value of a value*/
    private int myhash(Integer x )          
    {
        int hashVal = x.hashCode( );           
        hashVal %= hTable.length;
        if (hashVal < 0)
            hashVal += hTable.length;
        return hashVal;
    }   
	   
/*Method for count the max value in hash table*/
	void count()                   
	{
        int maxChar = 0;        
        
        for(int i = 0; i < hTable.length; i++)
		{
			int count = 0;
            if(hTable[i] != null)      //If the value in hash table is not null
			{
                count++;
                Node start = hTable[i];
                while(start.nextAddr != null)   //Count the number of nodes for a charcter
				{
                    count++;
                    start = start.nextAddr;
                }
                if(count > maxVal){    //For update the max value
                    maxVal = count;
                    maxChar = i;
                }
               
            }
        }       
        //Output the max value and the character
         System.out.println("Maximum occurrence is : "+(char)maxChar + " " + maxVal ); 		 
    }
}


public class Q1 {
	public static void main(String args[] )
	{
	    Scanner sc = new Scanner(System.in); //Create a object of scanner
		HashTable ht = new HashTable(128); //Create a object of hash table
		 
        System.out.print("\nEnter your string : ");      	
	    String str = sc.nextLine();     
       
        char[] c = str.toCharArray();        
	        
       for(int i = 0; i < c.length; i++)
	    {
         //Insert the characters in hash table
           int k = c[i]; 
            ht.insert(k);
        }	   
	    ht.count();       
   }
}