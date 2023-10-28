package domain.playlists;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Abstract class with a skeleton implementation of {@link Playlist} interface's, providing 
 * by default an implementation of all methods of the {@link Playlist}.
 */
public abstract class AbsPlaylist implements Playlist {

	private final List<ISong> songList = new ArrayList<>();
	private final String name;
	private final MusicLibrary library;
	private final Player player = PlayerFactory.INSTANCE.getPlayer();
	private ISong playingSong = null;
	private int indexSelected = -1;

	/**
	 * Constructor for the abstract class {@link AbsPlaylist} using a given name
	 * for the {@link Playlist} and the {@link MusicLibrary} that will contains
	 * all the songs that the playlist will have.
	 * 
	 * @param name			the given name for the playlist.
	 * @param library		the given library of songs.
	 * @requires			{@code name != null && library != null}
	 */
	protected AbsPlaylist(String name, MusicLibrary library) {
		this.name = name;
		this.library = library;
	}

	@Override
	public Iterator<ISong> iterator() {
		return this.songList.iterator();
	}

	@Override
	public int size() {
		return this.songList.size();
	}

	@Override
	public ISong getSelected() {
		return this.songList.get(this.indexSelected);
	}

	@Override
	public boolean someSelected() {
		return this.indexSelected != -1;
	}

	@Override
	public boolean add(ISong song) {
		if(this.songList.contains(song))
			return false;
		this.songList.add(song);
		this.indexSelected = this.songList.size() - 1;
		return true;
	}

	@Override
	public boolean remove() {
		if (someSelected()) {
			this.songList.remove(this.indexSelected);
			this.indexSelected = -1;
			return true;
		}
		return false;
	}

	@Override
	public void select(int i) {
		this.indexSelected = i;
	}

	@Override
	public boolean moveUpSelected(int i) {
		ISong elemSelected = getSelected();
		remove();
		this.songList.add(i, elemSelected);
		select(i);
		return true;
	}

	@Override
	public int getIndexSelected() {
		return this.indexSelected;
	}

	@Override
	public void next() {
		this.indexSelected = this.indexSelected + 1 >= size() ? -1 : this.indexSelected + 1;
	}

	@Override
	public void previous() {
		this.indexSelected = this.indexSelected - 1 < 0 ? -1 : this.indexSelected - 1; 
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean isPlaying() {
		return this.playingSong != null;
	}

	@Override
	public void play() {
		this.player.stop();
		this.playingSong = getSelected();
		this.player.load(this.playingSong.getFilename());
		this.player.play();
	}

	@Override
	public void stop() {
		this.player.stop();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("playingState") && isPlaying()) {
			Player.PlayingState newState = (Player.PlayingState) evt.getNewValue();
			if (newState == Player.PlayingState.STOPED)
				this.playingSong = null;
			else if (newState == Player.PlayingState.ENDED) {
				this.playingSong.incTimesPlayed();
				searchPlayingSong();
			}
		}
	}
	
	/**
	 * Auxiliary method to search the next song for playing.
	 */
	private void searchPlayingSong() {
		int index = this.songList.indexOf(this.playingSong);
		if(index != -1)
			select(index);
		if (someSelected() && getSelected().equals(this.playingSong)) {
			next();
			if (someSelected()) {
				play();
				return;
			}
		}
		this.playingSong = null;
	}

	@Override
	public void processEvent(SongLibraryEvent e) {
		if (e instanceof SongRemovedLibraryEvent) {
			Song removed = e.getSong();
			int index = this.songList.indexOf(removed);
			if (index != -1)
				removeAtIndex(index);
		}
	}
	
	/**
	 * Auxiliary method that will remove the {@link Song} that is selected in 
	 * the given index of the {@link Playlist}, and reselect the song that was
	 * previosly selected before the remove.
	 * 
	 * @param index		the index of the song that will be selected.
	 * @requires 		{@code 0 <= index < size()}
	 */
	protected void removeAtIndex(int index) {
		if (someSelected() && getIndexSelected() == index)
			remove();
		else {
			int selectedIndex = getIndexSelected();
			this.songList.remove(index);
			if (someSelected() && index < selectedIndex)
				select(selectedIndex - 1);
		}
	}

	/**
	 * Protected method that gets the {@link MusicLibrary}.
	 * 
	 * @ensures		{@code \result != null}
	 * @return 		the {@link MusicLibrary}.
	 */
	protected MusicLibrary getLibrary() {
		return library;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n*-- Playlist ")
		  .append(this.name)
		  .append("--*");
		for (int i = 0; i < size(); i++) {
			ISong song = this.songList.get(i);
			sb.append("\n")
			  .append(i)
			  .append(" ")
			  .append(song.toString());
			if (someSelected() && getSelected().equals(song))
				sb.append("->");
		}
		return sb.toString();
	}
}
