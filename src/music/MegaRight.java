package music;

public class MegaRight implements MegaState
{
	private java.awt.Image mm;
	private boolean drawn_once;
	
	public MegaRight()
	{
		gui.ImageLoader il = gui.ImageLoader.getImageLoader();
		mm = il.getImage("resources/run3.gif");
		drawn_once=false;
	}
	
	public void draw(java.awt.Graphics g)
	{
	   gui.DrawImage draw_img = new gui.DrawImage(mm, "right", 200, 200);
	   draw_img.showImage(120, 154);
	   drawn_once=true;
	   draw_img.draw(g);
	}
}