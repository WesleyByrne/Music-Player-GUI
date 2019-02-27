package music;

public class MegaLeft implements MegaState
{
	private java.awt.Image mm;
	private boolean drawn_once;
	
	public MegaLeft()
	{
		gui.ImageLoader il = gui.ImageLoader.getImageLoader();
		mm = il.getImage("resources/run1.gif");
		drawn_once=false;
	}
	
	public void draw(java.awt.Graphics g)
	{
	   gui.DrawImage draw_img = new gui.DrawImage(mm, "left", 200, 200);
	   draw_img.showImage(128, 154);
	   drawn_once=true;
	   draw_img.draw(g);
	}
}