package music;

public class Artist 
{
	private table.TableInterface<CD, String> cds;
	private String artist_name;
	
	public Artist(String name)
	{
		CompareCDTitles comp = new CompareCDTitles(true);
		//CompareCDRatings comp = new CompareCDRatings(true);
		cds = table.TableFactory.createTable(comp);
		artist_name = name;
	}
	
	public void addCD(CD cd)
	{
		if (cd == null) return;
		try {
			cds.tableInsert(cd);
		}
		catch (table.TableException e){
			System.err.println(e);
		}	
	}
	
	public CD getSpecificCD(String cd_name)
	{
		return cds.tableRetrieve(cd_name);
	}
	
	public String getArtist()
	{
		return artist_name;
	}
	
	public java.util.Iterator<CD> iterator()
	{
		return cds.iterator();
	}
	
	public String toString()
	{
		return artist_name;
	}
}
