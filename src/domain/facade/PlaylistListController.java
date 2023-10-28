package domain.facade;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.core.Song;
import domain.playlists.ManualPlaylist;
import domain.playlists.Playlist;
import domain.playlists.PlaylistList;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * The objects of this class respresents a controller of a {@link PlaylistList}
 * and the external interactions with this.
 */
public class PlaylistListController {

	private final PlaylistList playlists;
	private final MusicLibrary library;

	/**
	 * Constructor of the class {@link PlaylistListController}.
	 * 
	 * @param playlists		the given {@link PlaylistList} that will be controlled.
	 * @param library		the given {@link MusicLibrary} for controll the interactions.
	 * @requires 			{@code playlists != null && library != null}
	 */
	public PlaylistListController(PlaylistList playlists, MusicLibrary library) {
		this.playlists = playlists;
		this.library = library;
	}

	/**
	 * Inserts a new {@link ManualPlaylist} with the given name passed on the controller
	 * {@link PlaylistList}, and becomes the selected playlist.
	 * 
	 * @param name		the given name for the new {@link ManualPlaylist} created.
	 * @requires 		{@code name != null}
	 */
	public void createPlaylist(String name) {
		this.playlists.add(new ManualPlaylist(name, this.library));
	}

	/**
	 * Selects the {@link Playlist} on the given index of the {@link PlaylistList},
	 * only if {@code 0 <= i <= size()}, otherwise do nothing.
	 * 
	 * @param i		the given index of the {@link PlaylistList} for select the {@link Playlist}.
	 */
	public void selectPlaylist(int i) {
		if (i >= 0 && i < this.playlists.size())
			this.playlists.select(i);
	}

	/**
	 * Checks if there's some {@link Playlist} selected.
	 * 
	 * @return	true if there's a {@link Playlist} selected, and false otherwise.
	 */
	public boolean somePlaylistSelected() {
		return this.playlists.someSelected();
	}

	/**
	 * Gets the selected {@link Playlist}.
	 * 
	 * @requires	{@code somePlaylistSelected()}
	 * @ensures 	{@code \result != null}
	 * @return		the selected {@link Playlist}.
	 */
	public Playlist getSelectedPlaylist() {
		return this.playlists.getSelected();
	}

	/**
	 * Remove the selected {@link Playlist} in the cotrolled {@link PlaylistList},
	 * if exists, otherwise do nothing.
	 * 
	 * @return the selected {@link Playlist}.
	 */
	public void removePlaylist() {
		this.playlists.remove();
	}

	/**
	 * Returns an iterator structure with the playlists in the controlled {@link PlaylistList}.
	 * 
	 * @ensures			{@code \result != null}
	 * @return			an iterator with the playlits of the controlled {@link PlaylistList}.
	 */
	public Iterator<Playlist> iterator() {
		return this.playlists.iterator();
	}

	/**
	 * Gets the number of songs in the selected {@link Playlist}.
	 *
	 * @requires	{@code somePlaylistSelected()}
	 * @ensures 	{@code \result >= 0}
	 * @return 		the number of songs in the controlled {@link MusicLibrary}.
	 */
	public int numberOfSongs() {
		return getSelectedPlaylist().size();
	}

	/**
	 * Add the selected song in the library of the selected {@link Playlist} (if is possible).
	 * This {@link Song} becomes the selected one. 
	 * If there's no song selected on the library do nothing.
	 *
	 * @requires	{@code somePlaylistSelected()}
	 */
	public void addSong() {
		if (this.library.someSelected())
			getSelectedPlaylist().add(this.library.getSelected());
	}

	/**
	 * Selects the {@link Song} in the given index of the selected {@link Playlist}
	 * only if {@code 0 <= i <= numberOfSongs()}.
	 * 
	 * @param i		the element to be selected in the selected {@link Playlist}.
	 * @requires	{@code somePlaylistSelected()}
	 */
	public void selectSong(int i) {
		if(i >= 0 && i < numberOfSongs())
			getSelectedPlaylist().select(i);
	}

	/**
	 * Checks if there's some {@link Playlist} selected, and if true, also checks 
	 * if there's some {@link Song} selected on this playlist.
	 * 
	 * @return true if there is any playlist selected and if the selected playlist has any song selected, false otherwise.
	 */
	public boolean someSongSelected() {
		return somePlaylistSelected() && getSelectedPlaylist().someSelected();
	}

	/**
	 * Removes the selected {@link Song} on the selected {@link Playlist}.
	 * 
	 * @requires 	{@code someSongSelected()}
	 */
	public void removeSelectedSong() {
		getSelectedPlaylist().remove();
	}

	/**
	 * Sends the request of selection of the next song to the selected {@link Playlist}.
	 * 
	 * @requires 	{@code somePlaylistSelected()}
	 */
	public void nextSong() {
		getSelectedPlaylist().next();
	}

	/**
	 * Sends the request of selection of the previous song to the selected {@link Playlist}.
	 * 
	 * @requires 	{@code somePlaylistSelected()}
	 */
	public void previousSong() {
		getSelectedPlaylist().previous();
	}

	/**
	 * If theres some {@link Song} selected, stop the song that is playing (if that's the case)
	 * and starts playing, in order, all the songs of the current selected {@link Playlis}, 
	 * from the currently selected song.
	 * The counter of the song is incremented always when the song is played until the end.
	 * If no one song is selected, do nothing.
	 */
	public void play() {
		if (someSongSelected())
			this.playlists.play();
	}

	/**
	 * Stops the {@link Song} that is playing, only if this {@link Song} was playing through
	 * the {@link Playlist}, otherwise do nothing.
	 */
	public void stop() {
		this.playlists.stop();
	}
	
	@Override
	public String toString() {
		return this.playlists.toString();
	}
}
