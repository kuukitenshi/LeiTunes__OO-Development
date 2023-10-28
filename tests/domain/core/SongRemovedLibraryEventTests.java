package domain.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SongRemovedLibraryEventTests {

	private MusicLibrary library;
	private Song song;
	private SongRemovedLibraryEvent event;

	@BeforeEach
	void setup() {
		library = new MusicLibrary();
		song = new Song(new SongMetaInfo("", "", Arrays.asList(""), ""), "");
		event = new SongRemovedLibraryEvent(song, library);
	}
	
	@Test
	@DisplayName("Checks get library: the library returned must be the same as the created")
	public void testGetLibrary() {
		assertEquals(library, event.getLibrary());
	}
	
	@Test
	@DisplayName("Checks get song: the song returned must be the same as the created")
	public void testGetSong() {
		assertEquals(song, event.getSong());
	}

}
