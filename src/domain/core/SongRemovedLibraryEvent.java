package domain.core;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Class {@link SongRemovedLibraryEvent} that extends {@link SongLibraryEvent},
 * this class is used to represent the event fired when a song
 * is removed from {@link MusicLibrary}.
 */
public class SongRemovedLibraryEvent extends SongLibraryEvent {

	/**
	 * Constructor used to create a {@link SongRemovedLibraryEvent}.
	 * 
	 * @param song 		the {@link Song} that was removed.
	 * @param library 	the {@link MusicLibrary} whose song got removed.
	 * @requires 		{@code song != null && library != null}
	 */
	public SongRemovedLibraryEvent(Song song, MusicLibrary library) {
		super(song, library);
	}
}
