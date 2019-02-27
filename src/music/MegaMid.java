package music;

public class MegaMid implements MegaState
{
	private java.awt.Image mm;
	
	public MegaMid()
	{
		gui.ImageLoader il = gui.ImageLoader.getImageLoader();
		mm = il.getImage("resources/run2.gif");
	}
	
	public void draw(java.awt.Graphics g)
	{
	   gui.DrawImage draw_img = new gui.DrawImage(mm, "left", 200, 200);
	   draw_img.showImage(152, 154);
	   draw_img.draw(g);
	}
}