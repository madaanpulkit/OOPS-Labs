// Pulkit Madaan                                                           //
// 2016257                                                                 //
// Lab 7 - Music Application - (i) Serialistion, I/O Streams; (ii) JUnit   //
// 28/09/2017 (DD/MM/YYYY)                                                 // 
/////////////////////////////////////////////////////////////////////////////


// (i) Serialisation, I/O Streams

import java.io.*;
import java.util.*;

class NameNotFoundException extends Exception
{
	NameNotFoundException(String message)
	{
		super(message);
	}
}

class PlaylistEmptyException extends Exception
{
	PlaylistEmptyException(String message)
	{
		super(message);
	}
}

class Song implements Serializable
{
	private final String _title;
	private final String _artist;
	private final int _duration;

	Song(String _title, String _artist, int _duration)
	{
		this._title = _title;
		this._artist = _artist;
		this._duration = _duration;
	}

	Song(String _title)
	{
		this._title = _title;
		this._artist = null;
		this._duration = 0;
	}

	String getTitle()
	{
		return _title;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this.getClass() != o.getClass())
		{
			return false;
		}
		
		Song s = (Song) o;

		return _title.equals(s.getTitle());
	}

	public String toString()
	{
		return "Title : " + _title + "; Artist : " + _artist + " ; Duration : " + _duration;
	}
}

class Playlist implements Serializable
{
	private final String _name;
	private final ArrayList<Song> _myPlayList;
	private int _size;

	Playlist(String _name)
	{
		this._name = _name;
		_myPlayList = new ArrayList<Song>();
		_size = 0;
	}

	void addSong(Song _song)
	{
		_myPlayList.add(_song);
		_size ++;
	}

	void remSong(String _sName) throws NameNotFoundException
	{
		int ind = search(_sName);

		_myPlayList.remove(ind);
		_size --;
	}

	private int search(String _sName) throws NameNotFoundException
	{
		int ind = _myPlayList.indexOf(new Song(_sName));

		if(ind == -1)
		{
			throw new NameNotFoundException(_sName + " doesn't exist in the playlist");
		}

		return ind;
	}

	String searchSong(String _sName) throws NameNotFoundException
	{
		int ind = search(_sName);

		return _myPlayList.get(ind).toString();
	}

	int getSize()
	{
		return _size;
	}

	String getName()
	{
		return _name;
	}

	public String toString()
	{
		return _name;
	} 

	void showPlaylist() throws PlaylistEmptyException
	{
		if(_size <= 0)
		{
			throw new PlaylistEmptyException("Playlist " + _name + " is empty");
		}

		_myPlayList.forEach((Song _song) -> {System.out.println(_song);});
	}

	static void playListOptions()
	{
		System.out.println();
		System.out.println("Playlist options");
		System.out.println("1. Add a song");
		System.out.println("2. Delete a song");
		System.out.println("3. Search for a song");
		System.out.println("4. Show all the songs in the playlist");
		System.out.println("5. Back to menu");
		System.out.println("6. Exit");
	}

}

class MusicApp
{
	private Playlist curPlayList;
	private String curFileName;

	MusicApp()
	{
		curPlayList = null;
	}

	private void serialize() throws IOException
	{
		ObjectOutputStream out = null;	
		
		try	
		{
			out = new ObjectOutputStream (new FileOutputStream("playlists/" + curFileName));	
			out.writeObject(curPlayList);	
		}	

		finally	
		{	
			out.close();	
		}	
	}

	private void deserialize(String _playListName) throws IOException, ClassNotFoundException
	{
		ObjectInputStream in = null;	
			
		try
		{
			in = new ObjectInputStream (new FileInputStream("playlists/" + _playListName));	
			curFileName = _playListName;
			curPlayList = (Playlist) in.readObject();
		}

		finally
		{
			in.close();
		}
	}

	private void Menu(String[] playlists)
	{
		int i = 0;

		System.out.println();
		System.out.println("Playlist Menu");
		
		for(String _playlist : playlists)
		{
			System.out.println(i + ". " + _playlist); 
			i++;
		}
	}

	void runApp() throws ClassNotFoundException
	{	
		int option = 0;
		
		do
		{
			String path = System.getProperty("user.dir");
			String[] playlists = (new File(path + "/playlists")).list();
			
			Menu(playlists);

			Reader.init(System.in);

			try
			{
				int ind = Reader.nextInt();

				if(ind >= playlists.length || ind < 0)
				{
					System.out.println("Please keep the input b/w 0 and " + (playlists.length - 1) + " (inclusive); " + ind + " is out of this range");
					continue;
				}

				deserialize(playlists[ind]);

				do
				{
					Playlist.playListOptions();

					option = Reader.nextInt();

					switch(option)
					{
						case 1:
							
							System.out.print("Enter title of the track : ");
							String _title = Reader.readLine();
							System.out.println();

							System.out.print("Enter artist of the track : ");
							String _artist = Reader.readLine();
							System.out.println();

							System.out.print("Enter Duration of the track : ");
							int _duration = Reader.nextInt();
							System.out.println();

							curPlayList.addSong(new Song(_title, _artist, _duration));

							serialize();
							deserialize(curFileName);

							System.out.println("Current size : " + curPlayList.getSize());

							break;

						case 2:
							
							String _sName = Reader.readLine();

							try
							{
								curPlayList.remSong(_sName);
								
								serialize();
								deserialize(curFileName);

								System.out.println("Current Size : " + curPlayList.getSize());
							}

							catch(NameNotFoundException e)
							{
								System.out.println(e.getMessage());
							}

							break;

						case 3:
							
							String sName = Reader.readLine();

							try
							{
								System.out.println(curPlayList.searchSong(sName));
							}

							catch(NameNotFoundException e)
							{
								System.out.println(e.getMessage());
							}
							
							break;

						case 4:
							
							try
							{
								curPlayList.showPlaylist();
							}

							catch(PlaylistEmptyException e)
							{
								System.out.println(e.getMessage());
							}

							break;

						case 5:

							break;

						case 6:

							break;

						default:

							System.out.println("Please keep the input option b/w 1 and 6 (inclusive); " + option + " is out of this range");

							break;
					}
				}
				while(option != 5 && option != 6);
			}
			
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		while(option != 6);
	}
}

class Lab7_AP
{
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
       
       /*Playlist P1 = new Playlist("Hello");

       P1.addSong(new Song("song1", "artist1", 70));
       P1.addSong(new Song("song2", "artist2", 80));
       P1.addSong(new Song("song3", "artist3", 90));

       ObjectOutputStream out = null;	
		
		try	
		{
			out = new ObjectOutputStream (new FileOutputStream("playlists/P1.ser"));	
			out.writeObject(P1);	
		}	

		finally	
		{	
			out.close();	
		}	*/

		/*String path = System.getProperty("user.dir");
		
		String[] playlists = (new File(path)).list();

		for(int i=0; i<playlists.length; i++)
		{
			System.out.println(i + ". " + playlists[i]); 
			i++;
		}*/


       MusicApp newApp = new MusicApp();

       newApp.runApp();
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
