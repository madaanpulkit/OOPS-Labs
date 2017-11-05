// Pulkit Madaan                                                           //
// 2016257                                                                 //
// Lab 8 - paralellisation - (i) Threadpool; (ii) Flyweight pattern   //
// 05/11/2017 (DD/MM/YYYY)                                                 // 
/////////////////////////////////////////////////////////////////////////////

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class PascalFly extends RecursiveAction
{	
	private final int n;
	private final int k;
	private volatile long result;

	private volatile static Map<String, PascalFly> instances = new HashMap<String, PascalFly>();	
	
	private PascalFly(int n, int k) 
	{ 
		this.n = n;
		this.k = k; 
		this.result = 0;
	}

	public synchronized static PascalFly getInstance(int n, int k)
	{
		String key = n + " " + k;

		if(!instances.containsKey(key))
		{
			instances.put(key, new PascalFly(n, k));
		}

		return instances.get(key);
	}	

	public synchronized void compute() 
	{	
		if(n==0 || k==0 || n==k)
		{
			this.result = 1;
			return;		
		}

		PascalFly left = PascalFly.getInstance(this.n-1, this.k-1);	
		PascalFly right = PascalFly.getInstance(this.n-1, this.k);	
		
		if(left.result==0 && right.result==0)
		{
			left.fork();
			right.compute();
			left.join();
		}

		else if(left.result == 0)
		{
			left.compute();
		}

		else if(right.result == 0)
		{
			right.compute();
		}

		this.result = left.result + right.result;	
	}

	public long getResult()
	{
		return result;
	}

	public static void newTest()
	{
		instances = new HashMap<String, PascalFly>();
	}
}

class Pascal extends RecursiveAction
{	
	private final int n;
	private final int k;
	private volatile long result;

	public Pascal(int n, int k) 
	{ 
		this.n = n;
		this.k = k; 
		this.result = 0;
	}

	public synchronized void compute() 
	{	
		if(n==0 || k==0 || n==k)
		{
			this.result = 1;
			return;		
		}

		Pascal left = new Pascal(this.n-1, this.k-1);	
		Pascal right = new Pascal(this.n-1, this.k);	
		
		if(left.result==0 && right.result==0)
		{
			left.fork();
			right.compute();
			left.join();
		}

		else if(left.result == 0)
		{
			left.compute();
		}

		else if(right.result == 0)
		{
			right.compute();
		}

		this.result = left.result + right.result;	
	}

	public long getResult()
	{
		return result;
	}
}

public class Lab8_AP
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Flyweight");
		for(int i=1; i<=3; i++)
		{
			PascalFly task = PascalFly.getInstance(50, 25);
			ForkJoinPool pool = new ForkJoinPool(i);
			long start = System.currentTimeMillis();
			pool.invoke(task);
			long end = System.currentTimeMillis();
			float time = (end - start);
			System.out.println("result : " +task.getResult() + " ; threads : " + i + " ; time : " + time);
			PascalFly.newTest();
		}

		System.out.println("Non-Flyweight");
		Pascal task2 = new Pascal (24, 12);
		ForkJoinPool pool2 = new ForkJoinPool(2);
		long start2 = System.currentTimeMillis();
		pool2.invoke(task2);
		long end2 = System.currentTimeMillis();
		float time2 = (end2 - start2);		
		System.out.println("result : " +task2.getResult() + " ; time : " + time2);
	}
}

/** Class for buffered reading int and double values */
class Reader 
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) 
    {
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException 
    {
        while (!tokenizer.hasMoreTokens()) 
        {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(reader.readLine());
        }
        
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException 
    {	
        return Integer.parseInt(next());
    }

    static String readLine() throws IOException
    {
    	return reader.readLine();
    }
}
