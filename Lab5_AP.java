// Pulkit Madaan
// 2016257
// Lab 5 - Christmas Trees - Generic Classes 
// 31/08/2017 (DD/MM/YYYY)

import java.io.*;
import java.util.*;

class Student
{
	private ArrayList<Object> treeSums;

	Student()
	{
		treeSums = new ArrayList<Object>();
	}

	void add(Object sum)
	{
		treeSums.add(sum);
	}

	ArrayList<Object> getList()
	{
		return treeSums;
	}
}

class Node <T extends Comparable<T>> implements Comparable<Node<T>>
{
	private final T data;
	private int pos;
	private Node<T> left;
	private Node<T> right;

	Node(T data)
	{
		this.data = data;
		this.left = null;
		this.right = null;
		this.pos = -1;
	}

	Node()
	{
		this.data = null;
		this.left = null;
		this.right = null;
		this.pos = -1;
	}

	T getData()
	{	
		return data;
	}

	int getPos()
	{
		return pos;
	}

	Node<T> getLeft()
	{
		return left;
	}

	Node<T> getRight()
	{
		return right;
	}

	void setRoot()
	{
		this.pos = 0;
	}

	void setLeft(Node<T> left)
	{
		this.left = left;
	}

	void setRight(Node<T> right)
	{
		this.right = right;
	}

	@Override
	public int compareTo(Node<T> ptr)
	{
		return data.compareTo(ptr.getData());
	}
}

class Tree <T extends Comparable<T>>
{
	private Node<T> root;
	private ArrayList<Node<T>> In;

	Tree(int n)
	{
		root = null;
		In = new ArrayList<Node<T>>(n);
	}

	void Insert(T data)
	{
		root = driveInsert(root, data);
		root.setRoot();
	}

	private Node<T> driveInsert(Node<T> ball, T data)
	{
		if(ball == null)
		{
			ball = new Node<T>(data);
		}

		else if(data.compareTo(ball.getData()) <= 0)
		{
			ball.setLeft(driveInsert(ball.getLeft(), data));
		}

		else if(data.compareTo(ball.getData()) > 0)
		{
			ball.setRight(driveInsert(ball.getRight(), data));
		}

		return ball;
	}

	int getRoll()
	{
		inOrder(root);

		int ind = 0;

		for(int i=0; i<In.size(); i++)
		{
			if(In.get(i).getPos() == 0)
			{
				ind = i;
				break;
			}
		}

		return ind;
	}

	private void inOrder(Node<T> ball)
	{
		if(ball == null)
		{
			return;
		}

		inOrder(ball.getLeft());
		In.add(ball);
		inOrder(ball.getRight());
	}

	ArrayList<Node<T>> getIn()
	{
		return In;
	}
}

class Lab5_AP
{
	public static void main(String[] args)
	{
       Reader.init(System.in);
		
		try
		{		
			int x = Reader.nextInt();
			int n = Reader.nextInt();

			BSTFilesBuilder builder = new BSTFilesBuilder();

			builder.createBSTFiles(n, x);

			ArrayList<Student> students = new ArrayList<Student>(n);
			// ArrayList<Object> trees = new ArrayList<Object>(x); 

			for(int i=1; i<=x; i++)
			{
				Reader.finit(new FileReader(i + ".txt"));

				String type = Reader.next();
				
				switch(type)
				{
					case "Integer" :
						Tree<Integer> tree1 = new Tree<Integer>(n);

						int number = Reader.nextInt();
						
						for(int j=0; j<x; j++)
						{
							tree1.Insert(Reader.nextInt());
						}

						int roll = tree1.getRoll();

						ArrayList<Node<Integer>> In1 = tree1.getIn();

						int sum1 = 0;

						for(int j=0; j<In1.size(); j++)
						{
							sum1 += In1.get(j).getData();
						}

						Integer s1 = new Integer(sum1);

						Student st1 = new Student();
						st1.add(s1);
						students.add(roll, st1);

						break;

					case "String" :
						Tree<String> tree2 = new Tree<String>(n);
						
						for(int j=0; j<x; j++)
						{
							tree2.Insert(Reader.next());
						}

						roll = tree2.getRoll();

						ArrayList<Node<String>> In2 = tree2.getIn();

						String sum2 = "";

						for(int j=0; j<In2.size(); j++)
						{
							sum2 += In2.get(j).getData();
						}

						Student st2 = new Student();
						st2.add(sum2);
						students.add(roll, st2);

						break;

					case "Float" :
						Tree<Float> tree3 = new Tree<Float>(n);
						
						for(int j=0; j<x; j++)
						{
							tree3.Insert(Reader.nextFloat());
						}

						roll = tree3.getRoll();

						ArrayList<Node<Float>> In3 = tree3.getIn();

						float sum3 = 0;

						for(int j=0; j<In3.size(); j++)
						{
							sum3 += In3.get(j).getData();
						}

						Float s3 = new Float(sum3);

						Student st3 = new Student();
						st3.add(s3);
						students.add(roll, st3);

						break;

				}
			}

			int total = 0;

			PrintWriter wr = new PrintWriter("./src/answer.txt", "UTF-8");

			for(int j=0; j<students.size(); j++)
			{
				if(students.get(j) != null)
				{
					wr.print(j + 1);

					ArrayList<Object> values = students.get(j).getList();

					for(int k=0; k<values.size(); k++)
					{
						wr.print(" " + values.get(k));
					}

					wr.println();

					total ++;
				}
			}

			wr.println(n - total);
			
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
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

    static void finit(FileReader fr)
    {
    	reader = new BufferedReader(fr);
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
	
    static float nextFloat() throws IOException 
    {
        return Float.parseFloat(next());
    }

    static String readLine() throws IOException
    {
    	return reader.readLine();
    }
}

class BSTFilesBuilder {

	public void createBSTFiles(int numStudents, int numTrees) {
		Random rand = new Random();
		for (int i = 1; i <= numTrees; i++) {
		    try {
				PrintWriter w = new PrintWriter("./src/" + i + ".txt", "UTF-8");
				int type = rand.nextInt(3) + 1;
				if(type == 1) {
					w.println("Integer");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextInt(1000));
						w.print(" ");
					}
				}
				else if(type == 2) {
					w.println("Float");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextFloat()*1000);
						w.print(" ");
					}
				}
				else {
					w.println("String");
					w.println(numStudents);
					String alphabet = "abcdefghijklmnopqrstuvwxyz";
					for (int j = 1; j <= numStudents; j++) {
						int len = rand.nextInt(10)+1;
						for (int k = 0; k < len; k++)
							w.print(alphabet.charAt(rand.nextInt(alphabet.length())));
						w.print(" ");
					}
				}
				w.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
