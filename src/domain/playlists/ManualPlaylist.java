package domain.playlists;

import domain.core.MusicLibrary;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * The objects of this class, that extends the class {@link AbsPlaylist},
 * resepresents a {@link Playlist} whose songs was manually added.
 * This type of playlist allows remove and change the posicion of the songs.
 */
public class ManualPlaylist extends AbsPlaylist {

	/**
	 * Constructor for the class {@link ManualPlaylist} that creates a {@link Playlist}
	 * of this type using a given name for the {@link Playlist} and the {@link MusicLibrary}
	 * that will contains all the songs that the playlist will have.
	 * 
	 * @param name			the given name for the playlist.
	 * @param library		the given library of songs.
	 * @requires			{@code name != null && library != null}
	 */
	public ManualPlaylist(String name, MusicLibrary library) {
		super(name, library);
	}
}
