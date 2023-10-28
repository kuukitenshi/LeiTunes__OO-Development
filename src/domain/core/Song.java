package domain.core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import domain.facade.ISong;
import util.adts.RegExpMatchable;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Class {@link Song} used to represent songs stored in the {@link MusicLibrary}
 */
public class Song implements ISong, RegExpMatchable {

	private final SongMetaInfo metaInfo;
	private final String filename;
	private int playCount = 0;
	private Rate rate = Rate.UNRATED;

	/**
	 * Constructor used to create a {@link Song}.
	 * 
	 * @param info 		the metainfo of this song.
	 * @param fileName 	the filename of this song.
	 * @requires 		{@code info != null && fileName != null}
	 */
	public Song(SongMetaInfo info, String fileName) {
		this.metaInfo = info;
		this.filename = fileName;
	}
	
	/**
	 * Constructor used to create a {@link Song} with an empty
	 * metainfo and a specific rate. This constructor is marked
	 * package-protected and is only used in teh JUnit tests.
	 * 
	 * @param rate 	the rate to start this song with.
	 * @requires 	{@code rate != null}
	 */
	Song(Rate rate){
		this(new SongMetaInfo("", "", Arrays.asList(""), ""), "");
		this.rate = rate;
	}
	
	/**
	 * Constructor used to create a {@link Song}.This constructor is marked
	 * package-protected and is only used in teh JUnit tests.
	 * 
	 * @param info 		the metainfo of this song.
	 * @param fileName 	the filename of this song.
	 * @param rate 		the rate to start this song with.
	 * @param playCount the playCount to start this song with.
	 * @requires 		{@code info != null && fileName != null && rate != null && playCount >= 0}
	 */
	Song(SongMetaInfo info, String fileName, Rate rate, int playCount){
		this.metaInfo = info;
		this.filename = fileName;
		this.rate = rate;
		this.playCount = playCount;
	}

	@Override
	public void incTimesPlayed() {
		this.playCount++;
	}

	@Override
	public int getTimesPlayed() {
		return this.playCount;
	}

	@Override
	public Rate getRating() {
		return this.rate;
	}

	@Override
	public void incRating() {
		this.rate = this.rate.inc();
	}

	@Override
	public void decRating() {
		this.rate = this.rate.dec();
	}

	@Override
	public String getSongTitle() {
		return this.metaInfo.title();
	}

	@Override
	public String getGenre() {
		return this.metaInfo.genre();
	}

	@Override
	public List<String> getArtists() {
		return this.metaInfo.artists();
	}

	@Override
	public String getAlbum() {
		return this.metaInfo.album();
	}

	@Override
	public String getFilename() {
		return this.filename;
	}

	@Override
	public boolean matches(String regexp) {
		return this.metaInfo.matches(regexp);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		sb.append(getSongTitle())
		  .append(", ")
		  .append(getAlbum())
		  .append(", ")
		  .append(getGenre())
		  .append(", [")
		  .append(String.join("; ", getArtists()))
		  .append("]] --- ")
		  .append(getRating().ordinal())
		  .append(" -- ")
		  .append(getTimesPlayed());
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Song other = (Song) o;
		return this.filename.equals(other.filename) &&
				this.rate == other.rate &&
				this.playCount == other.playCount &&
				this.metaInfo.equals(other.metaInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.filename, this.rate, this.playCount, this.metaInfo);
	}
}

