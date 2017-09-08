// Pulkit Madaan
// 2016257
// Lab 6 - Knight and Queen - Exception Handling
// 8/09/2017 (DD/MM/YYYY)

import java.io.*;
import java.util.*;

class Coordinate
{
    private final int x;
    private final int y;

    Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y; 
    }

    int getX()
    {
        return x;
    }

    int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return x + " " + y;
    }

    public boolean equals(Coordinate co)
    {
        return x == co.getX() && y == co.getY();
    }
}

class StackEmptyException extends Exception
{
    StackEmptyException(String message)
    {
        super(message);
    }
}

class NonCoordinateException extends Exception
{
    NonCoordinateException(String message)
    {
        super(message);
    }
}

class OverlapException extends Exception
{
    OverlapException(String message)
    {
        super(message);
    }
}

class QueenFoundException extends Exception
{
    QueenFoundException(String message)
    {
        super(message);
    }
}

class BoxCo <T>
{
    private final T Cos;
    private final String Type;

    BoxCo(T Cos, String Type)
    {
        this.Cos = Cos;
        this.Type = Type;
    }

    T getNextCo()
    {
        return Cos;
    }

    String getType()
    {
        return Type;
    }

    @Override
    public String toString()
    {
        return Cos.toString();
    }
}

class Knight implements Comparable<Knight> 
{
    private Coordinate co;
    private final String name;
    private final Stack<BoxCo> magic_box;

    Knight(String name, Coordinate co)
    {
        this.name = name;
        this.co = co;
        magic_box = new Stack<BoxCo>();
    }

    String getName()
    {
        return name;
    }

    void setCo(Coordinate co)
    {
        this.co = co;
    }

    void push(BoxCo co)
    {
        magic_box.push(co);
    }

    @Override
    public int compareTo(Knight k)
    {
        return name.compareTo(k.getName());
    }

    @Override
    public String toString()
    {
        return name + " " + co;
    }

    Coordinate getCurCo()
    {
        return co;
    }

    Coordinate getNextCo() throws StackEmptyException, NonCoordinateException
    {
        if(magic_box.empty())
        {
            throw new StackEmptyException("StackEmptyException: Stack Empty exception");
        }

        BoxCo popCo = magic_box.pop();

        if(!popCo.getType().equals("Coordinate"))
        {
            throw new NonCoordinateException("NonCoordinateException: Not a coordinate Exception " + popCo);
        }

        return (Coordinate)popCo.getNextCo();
    } 
}

class Lab6_AP
{
	public static void main(String[] args)
	{
       Reader.init(System.in);
		
		try
		{		
			int n = Reader.nextInt();
            int iter = Reader.nextInt();
            
            int x = Reader.nextInt();
            int y = Reader.nextInt();
            Coordinate Queen = new Coordinate(x, y);

            ArrayList<Knight> knightList = new ArrayList<Knight>();

            for(int i=1; i<=n; i++)
            {
                Reader.finit(new FileReader(i + ".txt"));

                String name = Reader.readLine();
                Coordinate coordinate = new Coordinate(Reader.nextInt(), Reader.nextInt());
                
                Knight k = new Knight(name, coordinate);
                
                int inp = Reader.nextInt();

                for(int j=0; j<inp; j++)
                {
                    String Type = Reader.next();

                    switch(Type)
                    {
                        case "String":
                            BoxCo<String> strCo = new BoxCo<String>(Reader.next(), Type);
                            k.push(strCo);
                            break;

                        case "Coordinate":
                            Coordinate co = new Coordinate(Reader.nextInt(), Reader.nextInt());
                            BoxCo<Coordinate> coCo = new BoxCo<Coordinate>(co, Type);
                            k.push(coCo);
                            break;

                        case "Integer":
                            BoxCo<Integer> intCo = new BoxCo<Integer>(Reader.nextInt(), Type);
                            k.push(intCo);
                            break;

                        case "Float":
                            BoxCo<Float> floatCo = new BoxCo<Float>(Reader.nextFloat(), Type);
                            k.push(floatCo);
                            break;
                    }
                }

                knightList.add(k);
            }

            Collections.sort(knightList);

            String location = System.getProperty("user.dir");

            PrintWriter wr = new PrintWriter(location + "/" + "answer" + ".txt", "UTF-8");

            boolean found = false;

            for(int i=1; i<=iter; i++)
            {
                for(int j=0; j<knightList.size(); j++)
                {
                    boolean exception = false;

                    // System.out.print("j = " + j);

                    Coordinate c;
                    try
                    {
                        Knight k = knightList.get(j);

                        wr.println(i + " " + k);

                        // System.out.println(" " + k.getName());

                        c = k.getNextCo();
                    }

                    catch(StackEmptyException e)
                    {
                        exception = true;
                        knightList.remove(j);
                        wr.println(e.getMessage());
                        continue;
                    }

                    catch(NonCoordinateException e)
                    {
                        exception = true;
                        wr.println(e.getMessage());
                        continue;
                    }

                    try
                    {
                        knightOverlap(c, knightList);
                    }

                    catch(OverlapException e)
                    {
                        exception = true;
                        wr.println(e.getMessage());
                        knightList.get(j).setCo(c);
                        continue;
                    }

                    try
                    {
                        isQueen(c, Queen);
                    }

                    catch(QueenFoundException e)
                    {
                        wr.println(e.getMessage());
                        found = true;
                        break;
                    }

                    knightList.get(j).setCo(c);

                    if(!exception)
                    {
                        wr.println("No exception " + c);
                    }
                }

                if(found)
                {
                    break;
                }
            }

            wr.close();
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }

	}

    static void knightOverlap(Coordinate co, ArrayList<Knight> knightList) throws OverlapException
    {
        for(int i=0; i<knightList.size(); i++)
        {
            if(knightList.get(i).getCurCo().equals(co))
            {
                String name = knightList.get(i).getName();
                knightList.remove(i);
                throw new OverlapException("OverlapException: Knights Overlap Exception " + name);
            }
        }
    }

    static void isQueen(Coordinate c, Coordinate Queen) throws QueenFoundException
    {
        if(c.equals(Queen))
        {
            throw new QueenFoundException("QueenFoundException: Queen has been Found. Abort!");
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