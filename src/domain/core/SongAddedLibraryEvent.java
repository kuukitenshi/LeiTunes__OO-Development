package domain.core;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Class {@link SongAddedLibraryEvent} that extends {@link SongLibraryEvent},
 * this class is used to represent the event fired when a song is
 * added to a {@link MusicLibrary}.
 */
public class SongAddedLibraryEvent extends SongLibraryEvent {

	/**
	 * Constructor used to create a {@link SongAddedLibraryEvent}.
	 * 
	 * @param song 		the {@link Song} that was added.
	 * @param library 	the {@link MusicLibrary} that this song was added to.
	 * @requires 		{@code song != null && library != null}
	 */
	public SongAddedLibraryEvent(Song song, MusicLibrary library) {
		super(song, library);
	}
}
