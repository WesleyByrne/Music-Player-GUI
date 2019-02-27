package music;

public class Music
{
   private table.TableInterface<Artist, String> artists;
   Music (String file_name)
   {
	   artists = table.TableFactory.createTable(new CompareArtists(true));
	   readMusic(file_name);   
   }
   

   private void readMusic(String file_name)
   {
      util.ReadTextFile rf = new util.ReadTextFile(file_name);
      String art = rf.readLine();
      while (!rf.EOF())
      {
         String title = rf.readLine();
         //System.out.println(title);
         int year = Integer.parseInt(rf.readLine());
         int rating = Integer.parseInt(rf.readLine());
         int numTracks = Integer.parseInt(rf.readLine());
         CD cd = new CD(title, art, year, rating, numTracks);

         int tracks = 1;

         while (tracks <= numTracks)
         {
            String temp = rf.readLine();
            String[] line = temp.split(",");
            String len = line[0];
            String song_title = line[1];
			Song song = new Song(song_title, len, art, cd.getTitle(), tracks);
            cd.addSong(song);
            tracks++;
         }
		 
		 //DO THIS
         //if the artist isn't already present in the table, create a new artist and insert it
		
         Artist tmp_artist = artists.tableRetrieve(art);
		 if (tmp_artist==null) 
		 {
			 tmp_artist = new Artist(art);
			 artists.tableInsert(tmp_artist);
		 }
		 tmp_artist.addCD(cd);

         art = rf.readLine();
      }
	  
	  java.util.Iterator<Artist> it = artists.iterator();
	  while (it.hasNext()){
		  Artist arterino = it.next();
		  System.out.println("Artist: "+arterino);
	      java.util.Iterator iter = arterino.iterator();
		  while (iter.hasNext())
		  {
			  System.out.println("~~~ " + iter.next());
		  }
	  }
	  rf.close();
   }
   
   public table.TableInterface<Artist, String> getArtists()
   {
	   return artists;
   }
	  
   public static void main(String[] args)
   {
      Music mc = new Music("resources/cds.txt");
      //instantiate your GUI here
	  MusicGUI gui = new MusicGUI(mc, "MP3Player", "resources/icon.png");
   }
}