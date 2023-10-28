package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * The objects of this class, that extends the class {@link SmartPlaylist},
 * resepresents a {@link Playlist} where are the N (where N reprensents a 
 * numeric constant) most recently added to a specific playlist.
 */
public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist {

	//constant for the songs
	private static final int AMOUNT_OF_SONGS = 5;
	
	/**
	 * Constructor for the class {@link MostRecentlyAddedSongsPlaylist} that creates 
	 * a {@link SmartPlaylist} of this type using a given {@link MusicLibrary}
	 * that will contains all the songs that the playlist will have.
	 * 
	 * @param library		the given library of songs.
	 * @requires			{@code library != null}
	 */
	public MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
		super("Most Recently Added", library);
	}
	
	@Override
	public void processEvent(SongLibraryEvent e) {
		super.processEvent(e);
		if (e instanceof SongAddedLibraryEvent) {
			Song song = e.getSong();
			addAutomatic(song);
			if (size() > AMOUNT_OF_SONGS)
				removeAutomatic(0);
		}
		if (e instanceof SongRemovedLibraryEvent && size() < AMOUNT_OF_SONGS && getLibrary().size() > size()) {
			// searchs for a song in the MusicLibrary to be added
			int originalSize = size();
			for (int i = getLibrary().size() - 1; i >= 0 && size() == originalSize; i--) {
				ISong song = getLibrary().get(i);
				addAutomatic(song);
			}
		}
	}
}

