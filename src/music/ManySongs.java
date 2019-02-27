package music;

import java.awt.dnd.*;

public class ManySongs extends javax.swing.JList<Song> implements DragGestureListener
{
	private static String empty = new String("");
	
	public ManySongs()
	{
		super();
	}
	
	//method to recognize the start of a drag event and track the drag as it proceeds
	public void dragGestureRecognized(DragGestureEvent dge)
	{
		SongTransferable transferable = new SongTransferable((Song) getSelectedValue());
		//start the dragging process
		DragSource dragSource = dge.getDragSource();
		dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transferable, null);
	}
}