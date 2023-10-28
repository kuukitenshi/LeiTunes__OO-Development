package domain.facade;

import domain.core.MusicLibrary;
import domain.playlists.PlaylistList;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Class that obtains the inicial object of the system.
 * Contains a {@link MusicLibrary}, a {@link PlaylistList}, a {@link MusicLibraryController} 
 * and a {@link PlaylistListController}.
 */
public class LEITunes {

	private final MusicLibrary library = new MusicLibrary();
	private final PlaylistList playlistList = new PlaylistList(library);
	private final MusicLibraryController musicLibraryController = new MusicLibraryController(library);
	private final PlaylistListController playlistController = new PlaylistListController(playlistList, library);

	/**
	 * Gets the {@link MusicLibraryController}.
	 * 
	 * @ensures {@code \result != null}.
	 * @return 	the {@link MusicLibraryController}.
	 */
	public MusicLibraryController getMusicLibraryController() {
		return musicLibraryController;
	}

	/**
	 * Gets the {@link MusicLibraryController}.
	 * 
	 * @ensures {@code \result != null}.
	 * @return	the {@link PlaylistListController}.
	 */
	public PlaylistListController getPlaylistController() {
		return playlistController;
	}
}
