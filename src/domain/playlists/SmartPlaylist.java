package domain.playlists;

import domain.core.MusicLibrary;
import domain.facade.ISong;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * This class implements a skeleton of a {@link Playlist} that doesn't allow
 * adding and remove songs manually, or change their position.
 */
public abstract class SmartPlaylist extends AbsPlaylist {

	/**
	 * Constructor for the abstract class {@link SmartPlaylist} that using 
	 * a given name for the {@link Playlist} and a given {@link MusicLibrary} 
	 * that will contains all the songs that the playlist will have, creates 
	 * a playlist of this type.
	 * 
	 * @param name			the given name for the playlist.
	 * @param library		the given library of songs.
	 * @requires			{@code name != null && library != null}
	 */
	protected SmartPlaylist(String name, MusicLibrary library) {
		super(name, library);
	}

	/**
	 * Insert automatically the given {@link Song} on a {@link SmartPlaylist}.
	 * This method will be called when an add event is activated.
	 * 
	 * @param song		the song that will be added automatically in the {@link Playlist}.
	 * @requires 		{@code song != null}
	 * @ensures 		{@code size() >= \old size()}
	 */
	protected void addAutomatic(ISong song) {
		super.add(song);
	}

	/**
	 * Removes automatically the given {@link Song} from the {@link SmartPlaylist}.
	 * This method will be called when a remove event is activated.
	 * 
	 * @param song		the index of the {@link Song} that will be removed from the {@link Playlist}.
	 * @requires 		{@code 0 <= index <= size()}
	 * @ensures 		{@code size() < \old size() }
	 */
	protected void removeAutomatic(int index) {
		removeAtIndex(index);
	}

	@Override
	public boolean moveUpSelected(int i) {
		return false;
	}

	@Override
	public boolean add(ISong song) {
		return false;
	}

	@Override
	public boolean remove() {
		return false;
	}
}
