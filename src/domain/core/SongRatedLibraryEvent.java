package domain.core;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Class {@link SongRatedLibraryEvent} that extends {@link SongLibraryEvent},
 * this class is used to represent the event fired when a song
 * rating is changed (increased or decreased).
 */
public class SongRatedLibraryEvent extends SongLibraryEvent {

	private final Rate oldRate;
	private final Rate newRate;

	/**
	 * Constructor used to create a {@link SongRatedLibraryEvent}.
	 * 
	 * @param song 		the {@link Song} that was rated.
	 * @param library 	the {@link MusicLibrary} that holds this song, and emitted this event.
	 * @param oldRate	the {@link Rate} that the song had before getting changed.
	 * @param newRate	the {@link Rate} that the song was changed to.
	 * @requires 		{song != null && library != null && oldRate != null && newRate != null}
	 */
	public SongRatedLibraryEvent(Song song, MusicLibrary library, Rate oldRate, Rate newRate) {
		super(song, library);
		this.oldRate = oldRate;
		this.newRate = newRate;
	}

	/**
	 * Method that returns the rating that the song had before getting changed.
	 * 
	 * @ensures {@code \result != null}
	 * @return 	the rate that the song had before getting changed.
	 */
	public Rate getOldRate() {
		return this.oldRate;
	}

	/**
	 * Method that returns the rating that the song has after getting changed.
	 * 
	 * @ensures {@code \result != null}
	 * @return 	the rate that the song has after getting changed.
	 */
	public Rate getNewRate() {
		return this.newRate;
	}
}
