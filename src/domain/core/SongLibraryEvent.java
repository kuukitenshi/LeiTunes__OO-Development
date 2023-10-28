package domain.core;

import util.observer.Event;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Abstract class {@link SongLibraryEvent} that implements {@link Event},
 * this class is used to represent an abstract event that occured
 * to a {@link Song} in a {@link MusicLibrary}
 */
public abstract class SongLibraryEvent implements Event {

	private final Song song;
	private final MusicLibrary library;

	/**
	 * Constructor used to create a {@link SongLibraryEvent}.
	 * 
	 * @param song 		the {@link Song} involved in this event.
	 * @param library 	the {@link MusicLibrary} involved in this event, that emitted it.
	 * @requires 		{@code song != null && library != null}
	 */
	protected SongLibraryEvent(Song song, MusicLibrary library) {
		this.song = song;
		this.library = library;
	}

	/**
	 * Method that returns the {@link MusicLibrary} involved in this event, therefore
	 * the one that emitted this event.
	 * 
	 * @ensures {@code \result != null}
	 * @return 	the {@link MusicLibrary} involved in this event.
	 */
	public MusicLibrary getLibrary() {
		return library;
	}

	/**
	 * Method that returns the {@link Song} involved in this event.
	 * 
	 * @ensures {@code \result != null}
	 * @return 	the {@link Song} involved in this event.
	 */
	public Song getSong() {
		return song;
	}
}
