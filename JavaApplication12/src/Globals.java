import java.util.*;

public class Globals
{
	public static int PartitionSize = 0;
	public static int ProcessNumber = 0;
	public static char algorithm;
	public static boolean[] takenPlace;
	public static int[] Partitions;
	public static int[] Presentation;
public static int[] Remaining;
public static int[] xxx;
// Algorithms
  public static boolean FirstFit(int process)
	{
           
		for (int i = 0; i < Partitions.length; i++)
	{
		
			if (Partitions[i] >= process && takenPlace[i] != true)
			{
                                xxx[i]+=ProcessNumber;
                                ProcessNumber++;
				Presentation[i] = process; 
				Remaining[i]=Partitions[i]-process;
				takenPlace[i] = true;
				return true;
                                
			}
                   
	}
	return false;
	}
public static boolean BestFit(int process)
	{
		

	for (int i = 0; i < Presentation.length; i++)
	{
            int idxx=-1;
            for(int j=0;j<Partitions.length;j++)
            {
                if(Partitions[j]>process&&takenPlace[i]!=true)
                {
                    if(idxx==-1)
                        idxx=j;
                    else if(Partitions[idxx]>Partitions[j])
                        idxx=j;
                }
            }
          
            if(idxx!=-1)
            {              
              xxx[idxx]+=ProcessNumber;
              ProcessNumber++;
              Presentation[idxx]=process;
              takenPlace[idxx]=true;
              Remaining[idxx]=Partitions[idxx]-process;     
               return true;
            }
	}
           return false;
        }
	
	public static boolean WorstFit(int process)
	{		

	for (int i = 0; i < Presentation.length; i++)
	{
         int worstidx=-1;   
            for (int j = 0; j < Partitions.length; j++)
            {
		if (Partitions[j] > process && takenPlace[j] != true)
		{
			if(worstidx==-1)
                            worstidx=j;
                        else if(Partitions[worstidx]<Partitions[j])
                            worstidx=j;
		}
            }
                        xxx[worstidx]+=ProcessNumber;
                        ProcessNumber++;
                        Presentation[worstidx]=process;
                        Remaining[worstidx]=Partitions[worstidx]-process;
                        return true;
	}
      
            return false;
	}
      

	public static void zi3()
	{
		System.out.print("\nProcess No.\tProcess Size\tBlock no.\tRemaining\n");
		for (int j = 0; j < Presentation.length; j++)
		{

			if (Presentation[j] != -1)
			{
				System.out.print("");
				System.out.print("P");
				System.out.print(xxx[j]+1);
				System.out.print("               ");
				System.out.print(Presentation[j]);
				System.out.print("               ");
				System.out.print(j + 1);
				System.out.print("               ");
				System.out.print(Remaining[j]);
				System.out.println("");
			}
		}
	}

	public static boolean Full()
	{
           
		for (int i = 0; i < Presentation.length; i++)
		{
			if (Presentation[i] != -1)
			{
				return false;
			}
		}
	return true;
	}
	public static void initializeMemory()
	{
            Presentation = new int[PartitionSize];
		         for(int i=0;i<PartitionSize;i++)
                         {
                             Presentation[i]=-1;
                         }
                Remaining=new int[Presentation.length];
                xxx=new int[Presentation.length];
	}

	public static void main(String[] args)
	{
            int Process, MemorySize = 0;
Scanner sc=new Scanner(System.in);
	System.out.println( "What is the size of memory you want in KB : ");
	 MemorySize=sc.nextInt();

	System.out.println("How many partition you want : ");
	 PartitionSize=sc.nextInt();

	takenPlace = new boolean[PartitionSize];
	Partitions = new int[PartitionSize];
	int validate = 0;
	boolean validatebool = false;

	while (!validatebool)
	{
		validate = 0;
		for (int i = 0; i < PartitionSize; i++)
		{
			takenPlace[i] = false;
			System.out.println("Enter Partition number "+ (i + 1)+ " size : ");
			 Partitions[i]=sc.nextInt();
			validate += Partitions[i];
		}
		if (validate == MemorySize)
		{
			validatebool = true;
		}
		else
		{
			System.out.println( "Put the partitions again since the total size was not equal to memory size");
		}
	}

	initializeMemory();

	char algo;
	System.out.println("Choose one of the 3 Algorithms -- (f)irst fit , (b)est fit , (w)orst fit : ");
	algo=sc.next().charAt(0);

	switch (algo)
	{
	 case 'f':
		algorithm = 'f';
		break;

	 case 'w':
		algorithm = 'w';
		break;

	 case 'b':
		algorithm = 'b';
		break;

	default:
		System.out.println("you entered wrong algorithm!!");
		break;
	}

	do
	{
		System.out.println( "Now put the process to insert in the Memory (KB) : ");
		Process=sc.nextInt();
                switch (algorithm) {
                    case 'f':
                        if (FirstFit(Process) == false)
                        {
                            System.out.println("Error -- No Space avaliable (firstfit) " );
                        }
                        else
                        {
                            zi3();                          
                        }
                        break;
                    case 'w':
                        if (WorstFit(Process) == false)
                        {
                            System.out.println("Error -- No Space avaliable (worstfit) ");
                        }
                        else
                        {                            
                            zi3();
                        }
                        break;
                    case 'b':
                        if (BestFit(Process) == false)
                        {
                          System.out.println( "Error -- No Space avaliable (bestfit) ");
                        }
                        else
                        {
                            zi3();
                        }
                        break;
                    default:
                    {
                        break;
                    }
                }
                
	} while (Full()==false);

        }
}