package domain.playlists;

import java.util.stream.StreamSupport;

import domain.core.MusicLibrary;
import domain.core.Rate;
import domain.core.Song;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 *
 *         Class MostLikedSongsPlaylist that extends {@link SmartPlaylist}, used
 *         to represent a {@link Playlist} of the users most liked songs.
 */
public class MostLikedSongsPlaylist extends SmartPlaylist {

	private static final int AMOUNT_OF_SONGS = 5;

	/**
	 * Constructor that creates a {@link MostLikedSongsPlaylist}.
	 * 
	 * @param library the {@link MusicLibrary} that manages this playlist.
	 */
	public MostLikedSongsPlaylist(MusicLibrary library) {
		super("Most Liked", library);
	}

	@Override
	public void processEvent(SongLibraryEvent e) {
		super.processEvent(e);
		Song song = e.getSong();
		if (e instanceof SongRatedLibraryEvent ratedEvent) {
			addAutomatic(song);
			if (size() > AMOUNT_OF_SONGS)
				removeLowestRate();
			if (ratedEvent.getNewRate() == Rate.UNRATED)
				removeUnrated();
			refillPlaylist();
		} else if (e instanceof SongRemovedLibraryEvent && size() < AMOUNT_OF_SONGS && getLibrary().size() > size())
			refillPlaylist();
	}
	
	/**
	 * Auxiliary method used to remove the UNRATED songs from current playlist.
	 * @ensures 	{@code size() <= \old size()}
	 */
	private void removeUnrated() {
		int index = 0;
		for (ISong playlistSong : this) {
			if (playlistSong.getRating() == Rate.UNRATED)
				break;
			index++;
		}
		if (index < size())
			removeAutomatic(index);
	}
	
	/**
	 * Auxiliary method used to fill the current playlist with library songs', since they are
	 * different of UNRATED.
	 * @ensures 	{@code size() <= AMOUNT_OF_SONGS}
	 */
	private void refillPlaylist() {
		StreamSupport.stream(getLibrary().spliterator(), false)
				.sorted((s1, s2) -> s2.getRating().compareTo(s1.getRating()))
				.filter(x -> x.getRating() != Rate.UNRATED)
				.limit(AMOUNT_OF_SONGS)
				.forEach(this::addAutomatic);
	}

	/**
	 * Auxiliary method used to remove the song with the lowest rate from the playlist.
	 * @requires 	{@code size > 0}
	 * @ensures 	{@code size() < \old size()}
	 */
	private void removeLowestRate() {
		int removeIndex = -1;
		ISong smallest = null;
		int index = 0;
		for (ISong playlistSong : this) {
			if (smallest == null) {
				removeIndex = index;
				smallest = playlistSong;
			} else if (playlistSong.getRating().compareTo(smallest.getRating()) < 0) {
				smallest = playlistSong;
				removeIndex = index;
			}
			index++;
		}
		removeAutomatic(removeIndex);
	}
}