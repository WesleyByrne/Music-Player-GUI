package music;

import java.awt.Graphics;

public class CD
{
	//pick one of these items to be the sort "key"
   private String title;
   private String artist;
   private int year;
   private int count;
   private int rating;
   private Song[] songs;
   
   private int x_loc, y_loc;
   private int image = 0;
   
   private java.awt.Image img0;
   private java.awt.Image img1;
   private gui.DrawImage draw_img;
   
   private MegaState mega_man;
   private MegaLeft ml;
   private MegaRight mr;
   private MegaMid mm;
   private boolean right;
   
   private final gui.ImageLoader il = gui.ImageLoader.getImageLoader();
   private static final int x_off = 100;  //the offset will change during a rotation
   private static final int y_off = 100;
   private static final java.awt.Font verdana_12 = new java.awt.Font("Verdana", java.awt.Font.BOLD, 14);

   public CD (String title, String artist, int year, int rating, int tracks)
   {
	  this.title = title;
	  this.artist = artist;
	  
      this.year = year;
	  String img_file = "resources/art/" + artist + " - " + title + ".jpg";
      count = 0;
      songs = new Song[tracks];
	  
	  img0 = il.getImage(img_file);
	  img1 = il.getHighLightImage(img0);
	  draw_img = draw_img = new gui.DrawImage(img0, title, 200, 200);
	  
	  
      if (rating > 0 && rating <= 10)
      {
         this.rating = rating;
      }
      else
      {
         rating = 5;
      }
	  
	  ml = new MegaLeft();
	  mr = new MegaRight(); 
	  mm = new MegaMid();
	  mega_man = ml;
	  right = false;
   }
   
   public void draw(java.awt.Graphics g)
   {
	   String str = "Rating: " + rating;
	   gui.DrawFont draw_font = new gui.DrawFont("Verdana", "bold", 14, java.awt.Color.darkGray, 20, 30);
	   draw_font.draw(g, str);
	   draw_img.showImage(120, 150);
	   draw_img.draw(g);
	   mega_man.draw(g);
   }
   
   public void mouseClicked(int x, int y)
   {
	   if(draw_img.isSelected(x,y))
	   {
		   draw_img = new gui.DrawImage(img1, title, 200, 200);
		   mega_man = mm;
	   } 
	   else
	   {
		   draw_img = new gui.DrawImage(img0, title, 200, 200);
		   if (mega_man instanceof MegaMid){
			   right^=true;
			   if (right) mega_man = ml;
			   else mega_man = mr;
		   }
	   }
   }
   
   public int getNumTracks()
   {
	   return songs.length;
   }
   
   public String getArtist()
   {
      return artist;
   }
   
   public String getTitle()
   {
      return title;
   }

   public int getYear()
   {
      return year;
   }

   public int getRating()
   {
      return rating;
   }

   public Song getSong(int index)
   {
      if (index >= 0 && index < songs.length)
      {
         return songs[index];
      }
      else
      {
         return null;
      }
   }

   public void addSong(Song song)
   {
      if (song != null && count < songs.length)
      {
         songs[count] = song;
         count++;
      }
   }

   public String toString()
   {
      return title + "  " + year + "  " + rating + "  " + songs.length;
   }

}
