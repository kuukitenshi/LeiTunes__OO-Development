package domain.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.ArrayQListWithSelection;
import util.adts.QListWithSelection;
import util.observer.AbsSubject;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Class {@link MusicLibrary} that extends {@link AbsSubject} and implements {@link QListWithSelection} 
 * and {@link PropertyChangeListener}, used to represent a library (collection) of multiple songs.
 */
public class MusicLibrary extends AbsSubject<SongLibraryEvent> implements QListWithSelection<Song>, PropertyChangeListener {

	/**
	 * @author 58180 Rodrigo Correia
	 * @author 58180 Laura Cunha
	 * 
	 * Internal private class SongsIterator used to crate an {@link Iterator}
	 * of ISong. This iterator is used to create an {@link Iterable} in
	 * the method {@link MusicLibrary#getSongs()}
	 */
	private class SongsIterator implements Iterator<ISong> {
		
		private final Iterator<Song> iterator = iterator();
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}
		
		@Override
		public ISong next() {
			return iterator.next();
		}
	}
	
	private final QListWithSelection<Song> songList = new ArrayQListWithSelection<>();
	private final Player player = PlayerFactory.INSTANCE.getPlayer();
	private ISong playingSong = null;
	
	/**
	 * Constructor that creates a {@link MusicLibrary}
	 */
	public MusicLibrary() {
		this.player.addListener(this);
	}
	
	/**
	 * If there was a song playing before, stops it, then starts playing
	 * the currently selected {@link Song}
	 * 
	 * @requires 	{@code someSelected()}
	 * @ensures 	{@code isPlaying()}
	 */
	public void play() {
		this.player.stop();
		this.playingSong = getSelected();
		this.player.load(this.playingSong.getFilename());
		this.player.play();
	}

	/**
	 * Checks if there is a song playing that was played through this {@link MusicLibrary}
	 * 
	 * @return true if there is a {@link Song} playing that started playing through 
	 *         this {@link MusicLibrary}, false otherwise.
	 */
	public boolean isPlaying() {
		return this.playingSong != null;
	}

	/**
	 * Stops the currently playing {@link Song}
	 * 
	 * @requires 	{@code isPlaying()}
	 * @ensures 	{@code !isPlaying()}
	 */
	public void stop() {
		this.player.stop();
	}

	/**
	 * Increments the {@link Rate} of the currently selected {@link Song}
	 * 
	 * @requires 	{@code someSelected()}
	 * @ensures 	{@code getSelected().getRating().ordinal() >= \old getSelected().getRating().ordinal()}
	 */
	public void incRateSelected() {
		Song selected = getSelected();
		Rate oldRate = selected.getRating();
		selected.incRating();
		Rate newRate = selected.getRating();
		emitEvent(new SongRatedLibraryEvent(selected, this, oldRate, newRate));
	}

	/**
	 * Decrements the {@link Rate} of the currently selected {@link Song}
	 * 
	 * @requires 	{@code someSelected()}
	 * @ensures 	{@code getSelected().getRating().ordinal() <= \old getSelected().getRating().ordinal()}
	 */
	public void decRateSelected() {
		Song selected = getSelected();
		Rate oldRate = selected.getRating();
		selected.decRating();
		Rate newRate = selected.getRating();
		emitEvent(new SongRatedLibraryEvent(selected, this, oldRate, newRate));
	}

	/**
	 * Returns an {@link Iterable} of {@link ISong} that contains all songs that
	 * matches the specified regular expression.
	 * 
	 * @param reexp the regular expression to match each song.
	 * @requires 	{@code reexp != null}.
	 * @ensures 	{@code \result != null &&} every ISong in \result {@link ISong#matches(reexp)}.
	 * @return 		{@link Iterable} of {@link ISong} containing all songs in this {@link MusicLibrary}
	 * 			 	that matches the given regular expression.
	 */
	public Iterable<ISong> getMatches(String reexp) {
		return StreamSupport.stream(getSongs().spliterator(), false)
						.filter(x -> x.matches(reexp))
						.toList();
	}

	/**
	 * Returns an {@link Iterable} of {@link ISong} that contains all songs of this {@link MusicLibrary}.
	 * 
	 * @ensures {@code \result != null}.
	 * @return 	{@link Iterable} of {@link ISong} containing all songs in this {@link MusicLibrary}.
	 */
	public Iterable<ISong> getSongs() {	
		return SongsIterator::new;
	}

	@Override
	public void select(int i) {
		this.songList.select(i);
	}
	
	@Override
	public void add(Song s) {
		this.songList.add(s);
		emitEvent(new SongAddedLibraryEvent(s, this));
	}
	
	@Override
	public boolean someSelected() {
		return this.songList.someSelected();
	}
	
	@Override
	public int getIndexSelected() {
		return this.songList.getIndexSelected();
	}
	
	@Override
	public void next() {
		this.songList.next();
	}
	
	@Override
	public void previous() {
		this.songList.previous();
	}
	
	@Override
	public void remove() {
		if (someSelected()) {		
			Song removed = getSelected();
			this.songList.remove();
			emitEvent(new SongRemovedLibraryEvent(removed, this));
		}
	}
	
	@Override
	public Song getSelected() {
		return this.songList.getSelected();
	}
	
	@Override
	public int size() {
		return this.songList.size();
	}

	@Override
	public Song get(int i) {
		return this.songList.get(i);
	}

	@Override
	public Iterator<Song> iterator() {
		return this.songList.iterator();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("playingState") && isPlaying()) {
			Player.PlayingState newState = (Player.PlayingState) evt.getNewValue();
			if (newState == Player.PlayingState.STOPED)
				this.playingSong = null;
			else if (newState == Player.PlayingState.ENDED) {
				this.playingSong.incTimesPlayed();
				this.playingSong = null;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("****MUSIC LIBRARY*****");
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
