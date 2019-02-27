package music;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.dnd.*;
import java.util.Iterator;

public class MusicGUI extends gui.CenterFrame implements ActionListener, gui.Drawable
{
	JComboBox arts;
	JComboBox cds;
	ManySongs many_songs;
	SingleSong single_song;
	
	public void draw(java.awt.Graphics g, int width, int height)
	{
		CD tmp = (CD)cds.getSelectedItem();
		tmp.draw(g);
	}

	public void mouseClicked(int x, int y)
	{
		CD tmp = (CD)cds.getSelectedItem();
		tmp.mouseClicked(x,y);
	}

	public void keyPressed(char key){}
	
	public MusicGUI(Music mc, String title, String icon_file_name)
	{
		super (700, 500, title);
        setLayout(new BorderLayout());
        setSize(700, 500);
		
		setResizable(true);
		
		gui.DrawPanel draw = new gui.DrawPanel();
        draw.setDrawable(this);
	  
		GridLayout north_layout = new GridLayout(1,2);
		JPanel north_panel = new JPanel();
		north_panel.setLayout(north_layout);
		
		arts = new JComboBox();
		cds = new JComboBox();
		
		arts.addActionListener(this);
		cds.addActionListener(this);
		arts.setActionCommand("Artists");
		cds.setActionCommand("CD");
		many_songs = new ManySongs();
		single_song = new SingleSong();
		single_song.setPreferredSize(new Dimension(10,17));
		JScrollPane sp = new JScrollPane();
		sp.setPreferredSize(new Dimension(200,1));
		sp.setHorizontalScrollBar(sp.createHorizontalScrollBar());
		sp.getViewport().add(many_songs);
		
		//fill list with music's artist list
		table.TableInterface<Artist, String> artists = mc.getArtists();
		Iterator<Artist> it = artists.iterator();
		//fill the artists combobox
		while (it.hasNext())
			arts.addItem(it.next());
		
		add(north_panel, BorderLayout.NORTH);
		add(sp, BorderLayout.WEST);
		add(single_song, BorderLayout.SOUTH);
		add(draw, BorderLayout.CENTER);
		north_panel.add(arts);
		north_panel.add(cds);
		
		DragSource dragSource = DragSource.getDefaultDragSource();
		DragGestureRecognizer dgr = dragSource.createDefaultDragGestureRecognizer(many_songs, DnDConstants.ACTION_COPY, many_songs);
		DropTarget dropTarget = new DropTarget(single_song, single_song);
		
		javax.swing.ImageIcon img_icon = new javax.swing.ImageIcon(icon_file_name);
	    setIconImage(img_icon.getImage());
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
    {
		String action = e.getActionCommand();
	    if(action.equals("Artists"))
	    {
			//clear artist combobox
			cds.removeActionListener(this);
			cds.removeAllItems();
			Artist artist = (Artist)arts.getSelectedItem();
			//add cds to cd combobox
			Iterator<CD> it = artist.iterator();
			CD tmp_cd = it.next();
			//list songs from first cd
			listCDSongs(tmp_cd);
			cds.addItem(tmp_cd);
			while (it.hasNext())
			{
				tmp_cd=it.next();
				cds.addItem(tmp_cd);
			}
			cds.addActionListener(this);
	    }
	    if (action.equals("CD"))
	    {
			//find cd chosen, list its songs
		   CD cd = (CD)cds.getSelectedItem();
		   listCDSongs(cd);
	    }
    }
	
	public void listCDSongs(CD cd)
	{
	   Song[] songs = new Song[cd.getNumTracks()];
	   for (int i=0; i<cd.getNumTracks(); i++)
	   {
		   songs[i] = cd.getSong(i);
	   }
	   many_songs.setListData(songs);
	}
}