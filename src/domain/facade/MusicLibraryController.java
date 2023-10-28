package domain.facade;

import java.util.Optional;
import java.util.logging.Logger;

import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongMetaInfo;
import servicos.MetaInfoLoader;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * The objects of this class respresents a controller of the interactions with an
 * other object of the type {@link MusicLibrary}.
 */
public class MusicLibraryController {

	private final Logger logger = Logger.getLogger("LEITunes");
	private final MusicLibrary library;
	private final MetaInfoLoader metaInfoLoader = new MetaInfoLoader();

	/**
	 * Constructor of the class {@link MusicLibraryController}.
	 * 
	 * @param library	the given {@link MusicLibrary} for controll the interactions.
	 * @requires 		{@code library != null}
	 */
	public MusicLibraryController(MusicLibrary library) {
		this.library = library;
	}

	/**
	 * Gets the number of songs in the controlled {@link MusicLibrary}.
	 *
	 * @ensures		{@code \result >= 0}
	 * @return 		the number of songs in the controlled {@link MusicLibrary}.
	 */
	public int numberOfSongs() {
		return this.library.size();
	}

	/**
	 * Add the song in the controlled {@link MusicLibrary} and obtains of the
	 * given file its metadata, if exists.
	 *
	 * @param filename		the filename that will be added to the controlled {@link MusicLibrary}.
	 * @requires 			{@code filename} is an mp3 file && {@code filename != null}
	 */
	public void addSong(String filename) {
		SongMetaInfo meta = this.metaInfoLoader.load(filename);
		if(meta == null) {
			this.logger.severe(() -> "Failed to load file meta-info " + filename);
			return;
		}
		Song song = new Song(meta, filename);
		this.library.add(song);
	}

	/**
	 * Sends the request of selection to the controlled {@link MusicLibrary}, only 
	 * if {@code 0 <= i <= numberOfSongs()}, otherwise don't do nothing.
	 * 
	 * @param i		the element to be selected.
	 */
	public void selectSong(int i) {
		if(i >= 0 && i < numberOfSongs())
			this.library.select(i);
	}

	/**
	 * Gets the song selected in the controlled {@link MusicLibrary}, if exists.
	 * 
	 * @return the song selected in the controlled {@link MusicLibrary}, if exists.
	 */
	public Optional<ISong> getSelectedSong() {
		if(this.library.someSelected())
			return Optional.of(this.library.getSelected());
		return Optional.empty();
	}

	/**
	 * Removes the song selected in the controlled {@link MusicLibrary}, if exists.
	 */
	public void removeSelectedSong() { 
		this.library.remove(); 
	}

	/**
	 * If theres a song selected, stop the song that is playing (if that's the case)
	 * and starts playing the next song selected in the controlled {@link MusicLibrary}.
	 * The counter of the song is incremented always when the song is played until the end.
	 * If no one song is selected, don't do nothing.
	 * 
	 * @return the song selected in the {@link MusicLibrary}, if exists.
	 */
	public void play() {
		if(this.library.someSelected())
			this.library.play();
	}

	/**
	 * Stops the {@link Song} that is playing, only if this {@link Song} was playing through
	 * the {@link MusicLibrary}, otherwise don't do nothing.
	 */
	public void stop() {
		if(this.library.isPlaying())
			this.library.stop();
	}

	/**
	 * Changes the rate of the selected {@link Song} in the {@link MusicLibrary} (if exists)
	 * to the value immediately above to the current one, or don't do nothing if the 
	 * rate was already the maximum.
	 */
	public void incRateSelected() {
		if(this.library.someSelected())
			this.library.incRateSelected();
	}

	/**
	 * Changes the rate of the selected {@link Song} in the {@link MusicLibrary} (if exists)
	 * to the value immediately below to the current one, or don't do nothing if the 
	 * rate was already the minimum.
	 */
	public void decRateSelected() {
		if(this.library.someSelected())
			this.library.decRateSelected();
	}

	/**
	 * Returns an iterator structure with the songs of the controlled {@link MusicLibrary}
	 * that matches whith the given regular expression (regex).
	 * 
	 * @param reexp		the given regular expression (regex).
	 * @requires		{@code reexp != null}
	 * @ensures			{@code \result != null}
	 * @return			an iterator with the songs of the controlled {@link MusicLibrary} that matches
	 * 					with the given expression.
	 */
	public Iterable<ISong> getMatches(String reexp) {
		return this.library.getMatches(reexp);
	}

	/**
	 * Returns an iterator structure with the songs of the controlled {@link MusicLibrary}.
	 * 
	 * @ensures		{@code \result != null}
	 * @return 		an iterator with the songs of the controlled {@link MusicLibrary}.
	 */
	public Iterable<ISong> getSongs() {
		return this.library.getSongs();
	}
	

	@Override
	public String toString() {
		return this.library.toString();
	}
}
