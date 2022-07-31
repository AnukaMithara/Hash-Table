import java.util.*;

/*class for get anode*/
class Node
{
    Node nextAddr;
    String data;
   
    public Node(String data)
    {
        this.data = data;
        nextAddr = null;
    }
}
 
// Class HashTable
class HashTable
{
     Node[] hTable;
     int size ;
	 int maxVal = 0;
     int tableSize;
     String[] personArray;
      
    // Constructor 
    public HashTable(int tableSize)
    {
        this.tableSize = tableSize;
        personArray = new String[tableSize];     //for get persons details
        hTable = new Node[ tableSize ];
        size = 0;
    }
        /*Method to insert a new node in the hash table*/  
    public void insertl(String name,String game)
    {       
        int gameVal = getStringValue(game);
        int personVal = getStringValue(name);        
       
        int p = myhash(gameVal);            //Hashing the game name
        int pV = myhash(personVal);         //Hashing the person name

    /*check the person name is already in the person table
        and if not insert the game details to hashtable*/
        if(personArray[pV] == null)     
        {
            size++;          
            
            Node nptr = new Node(game);   
            personArray[pV] = name;                    

            if (hTable[p] == null)
                hTable[p] = nptr;            
            else
            {
                nptr.nextAddr = hTable[p];
                hTable[p] = nptr;
            }  
        }
        else{
            System.out.println("Person already inserterd data");
        }   
          
    }
   
    /*Method for get the hash value of a value*/
    public int myhash(Integer x )
    {
        int hashVal = x.hashCode( );           
        hashVal %= hTable.length;
        if (hashVal < 0)
            hashVal += hTable.length;
        return hashVal;
    }   
	   
	/*Method for count the maximum time of game 
        & Football count in hash table*/
	public void count()
	{
        String maxSport = " - ";        
        int footBallCount = 0 ;

        for(int i = 0; i < hTable.length; i++)
		{
			int count = 0;
            
            if(hTable[i] != null)
			{
                count++;
                Node start = hTable[i];
                
                /*Count the football playing persons*/
                if(start.data.equals("FOOTBALL"))
                {
                        footBallCount++;
                        while(start.nextAddr != null)
				            {  
                                footBallCount++;
                                
                                start = start.nextAddr;                   
                            }                      
                }

                while(start.nextAddr != null)
				{  
                    count++;
                    start = start.nextAddr;                   
                }
                if(count > maxVal){
                    maxVal = count;
                    maxSport = hTable[i].data;
                }
               
            }
        } 
        /*user output the max sport and football count*/      
         System.out.println("\nMost playing sport is "+ maxSport /*+ " & number of persons are = " + maxVal */  ); 		 
         System.out.println(footBallCount + " person(/s are) playing Football "); 	
    }

  
    /*Method for create a unique value for string*/
    int  getStringValue(String str)
	{
        char[] charArray = str.toCharArray();

        int g = str.length();
        int strValue = 0;
        for(int i = 0; i< charArray.length; i++)
        {
            strValue += charArray[i] * (31^( g-i-1));
        }
        return strValue;
    }


}


public class Q2 {
	
	
	public static void main(String args[] )
	{
        /*create a object in scanner class*/
		 Scanner sc = new Scanner(System.in);

         /*get user input for howmany entries */
        System.out.print("\nNumber of entries : "); 
        int n = sc.nextInt();
       
        /*create a object in hashTable class*/
	    HashTable ht = new HashTable(n);
        System.out.println("Please Input your name's and favorite game's character lengths lessthan 12"); 
       for(int i = 0; i < n; i++)
	   {
            /*get user input for person names and game names*/
            System.out.print("\nPerson name : ");      	
            String name = sc.next();
            name = name.toUpperCase();

            System.out.print("Favourite game  : ");      	
            String game = sc.next();
            game = game.toUpperCase();

            ht.insertl(name , game);        
       }
    /*calculate and user print the max sport and football count*/	   
     ht.count();   
   }

}