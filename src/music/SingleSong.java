package music;

import java.awt.dnd.*;
import java.awt.datatransfer.*;

public class SingleSong extends javax.swing.JList implements MP3Listener, DropTargetListener
{
	private static String empty = new String("Drag a song here");
	private MP3 mp3;
	
	public SingleSong()
	{
		super();
		setObject(empty);
		mp3 = new MP3(this);
	}
	
	public SingleSong(Object obj)
	{
		super();
		setObject(obj);
	}
	
	public void setObject(Object obj)
	{
		Object[] item = {obj};
		setListData(item);
		if (obj instanceof Song)
		   mp3.changeSong((Song) obj);
	}
	
	public void clear()
	{
		setObject(empty);
	}
	
	public void drop(DropTargetDropEvent dtde)
	{
		Transferable transfer = dtde.getTransferable();
		DataFlavor[] flavors = transfer.getTransferDataFlavors();
		Song dropSong;
		try
		{
			dropSong = (Song) transfer.getTransferData(flavors[0]);
		}
		catch(UnsupportedFlavorException ufe)
		{
			dtde.rejectDrop();
			return;
		}
		catch(java.io.IOException ioe)
		{
			dtde.rejectDrop();
			return;
		}
		
		dtde.acceptDrop(DnDConstants.ACTION_COPY);
		dtde.dropComplete(true);
		
		setObject(dropSong);
	}
	
	public void mp3Done()
	{ 
		System.out.println("Song finished"); 
		setObject(empty);
	}
	
	public void dragEnter(DropTargetDragEvent dtde){}
	public void dragExit(DropTargetEvent dte){}
	public void dropActionChanged(DropTargetDragEvent dtde) {}
	public void dragOver(DropTargetDragEvent dtde){}
	
	
}