public class Prog
{
    public static HashFunctions h = null;
    public static void main(String[] args)
    {
       if (args.length != 2) {
        System.out.println("Please execute: java Prog <n> <p>");
        System.out.println("n is the input size, and p is the program number.");
        System.exit(0);
    }

    int n = Integer.parseInt(args[0]);
    int p = Integer.parseInt(args[1]);
       
        h = new HashFunctions(n);
        switch (p)
        {
            case 1:
                prog1(n);
                break;
            case 2:
                prog2(n);
                break;
            case 3:
                prog3(n);
                break;
            case 4:
                prog4(n);
                break;
            default:
                System.out.println("Invalid program number. Only 1-4.");
        }
    }

    private static void prog1(int n)
    {
        // TODO: Code to generate n keys that all get hashed to the same index
        // using hash1.
       
        for (int i = 0; i < n; i++)
        {
            System.out.println(n * i);
        }
    }

    private static void prog2(int n)
    {
        // TODO: Code to generate n keys that all get hashed to the same index
        // using hash2.
        for (int i = 0; i < n; i++)
        {
            System.out.println(i);
        }
    }

    private static void prog3(int n)
    {
        // TODO: Code to generate n keys that all get hashed to the same index
        // using hash3.
        HashTable t1 = new HashTable(n);
        int hy = 0;
        int length = n * n;
        int c[] = new int[n];
       for(int i = 0; i < length; i++)
       {
         hy = h.hash3(i);
         t1.insert1(i);
         c[hy]++;
         if(c[hy] == n)
         {
             break;
         }
         

       } 
       t1.print(hy);
        
    }

    private static void prog4(int n)
    {
        // TODO: Code to generate n keys that all get hashed to the same index
        // using hash4.
        int hy = 0;
        int length = n * n;
        int c[] = new int[n];
        HashTable t2 = new HashTable(n);
      
       for(int i = 0; i < length; i++)
       {
           hy = h.hash4(i);
           c[hy]+=1;
           t2.insert2(i);
           if(c[hy] == n)
           {
               break;
           }
       } 
       t2.print(hy);
    }
}
