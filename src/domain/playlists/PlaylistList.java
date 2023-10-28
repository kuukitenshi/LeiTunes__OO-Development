package domain.playlists;

import java.util.Iterator;

import util.adts.AbsQListWithSelection;
import util.adts.ArrayQListWithSelection;
import util.adts.QListWithSelection;
import util.observer.Listener;
import domain.core.MusicLibrary;
import domain.player.Player;
import domain.player.PlayerFactory;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 *
 * Class PlaylistList that extends {@link AbsQListWithSelection}, used to
 * represent a list of various playlists, offering methods to manipulate de list
 * and play songs.
 */
public class PlaylistList implements QListWithSelection<Playlist> {

	private final QListWithSelection<Playlist> playlists = new ArrayQListWithSelection<>();
	private final MusicLibrary library;
	private final Player player = PlayerFactory.INSTANCE.getPlayer();

	/**
	 * Constructor used to create a {@link PlaylistList}, this also adds 
	 * 2 default playlists {@link MostLikedSongsPlaylist} and {@link MostRecentlyAddedSongsPlaylist}
	 * 
	 * @param library the music library to manage this playlists, used to
	 * 		  get songs and to manage the playlist listener.
	 * @requires {@code library != null}
	 * @ensures {@code size() == 2 && someSelected()}
	 */
	public PlaylistList(MusicLibrary library) {
		this.library = library;
		add(new MostLikedSongsPlaylist(library));
		add(new MostRecentlyAddedSongsPlaylist(library));
	}
	
	
	@Override
	public void select(int i) {
		this.playlists.select(i);	
	}

	/**
	 * Adds a {@link Playlist} to this {@link PlaylistList} and registers it as
	 * {@link Listener} of the {@link MusicLibrary} and {@link Player}.
	 * 
	 * @param e the playlist to be added
	 * @requires {@code e != null}
	 * @ensures  {@code someSelected() && getIndexSelected() == size() - 1}
	 */
	@Override
	public void add(Playlist e) {
		this.playlists.add(e);
		this.library.registerListener(e);
		this.player.addListener(e);
	}
	
	@Override
	public boolean someSelected() {
		return this.playlists.someSelected();
	}
	
	
	@Override
	public int getIndexSelected() {
		return this.playlists.getIndexSelected();
	}
	
	@Override
	public void next() {
		this.playlists.next();
	}
	
	@Override
	public void previous() {
		this.playlists.previous();
	}

	/**
	 * Removes the selected {@link Playlist} from this {@link PlaylistList}, 
	 * and unregisters it from {@link Listener} of {@link MusicLibrary} and {@link Player}.
	 * 
	 * @requires {@code someSelected()}
	 * @ensures  {@code !someSelected() && size() <= \old size()}
	 */
	@Override
	public void remove() {
		if (someSelected()) {
			this.library.unregisterListener(getSelected());
			this.player.removeListener(getSelected());
			this.playlists.remove();
		}
	}
	
	
	@Override
	public Playlist getSelected() {
		return this.playlists.getSelected();
	}
	
	/**
	 * Starts playing the selected playlist, if the playlist has a song selected it will start
	 * playing all songs from the playlist starting from the selected one, otherwise it does nothing.
	 *
	 * @requires {@code someSelected()}
	 */
	public void play() {
		getSelected().play();
	}

	/**
	 * Checks if any of the {@link Playlist} from this {@link PlaylistList} is playing a song.
	 * 
	 * @return true if a song is playing and it started playing from the playlist, false otherwise.
	 */
	public boolean isPlaying() {
		for (Playlist playlist  : this) {
			if(playlist.isPlaying()) 
				return true;
		}
		return false;
	}

	/**
	 * Stops the song that is currently playing.
	 * 
	 * @requires {@code isPlaying()}.
	 * @ensures {@code !isPlaying()}
	 */
	public void stop() {
		for (Playlist playlist  : this) {
			if(playlist.isPlaying()) 
				playlist.stop();
		}
	}
	
	
	/**
	 * Returns a text representation of this {@link PlaylistList}
	 * 
	 * @return string containing the text representation of this {@link PlaylistList}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("**** PLAYLISTS *****");
		for (Playlist playlist : this) {
			sb.append(playlist.toString());
		}
		return sb.toString();
	}


	@Override
	public int size() {
		return this.playlists.size();
	}


	@Override
	public Playlist get(int i) {
		return this.playlists.get(i);
	}


	@Override
	public Iterator<Playlist> iterator() {
		return this.playlists.iterator();
	}
}

