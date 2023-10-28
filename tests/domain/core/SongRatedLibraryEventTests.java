package domain.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SongRatedLibraryEventTests {
	
	private MusicLibrary library;
	private Song song;
	private SongRatedLibraryEvent event;
	private Rate oldRate;
	private Rate newRate;

	@BeforeEach
	void setup() {
		library = new MusicLibrary();
		song = new Song(new SongMetaInfo("", "", Arrays.asList(""), ""), "");
		oldRate = Rate.BAD;
		newRate = Rate.VERY_BAD;
		event = new SongRatedLibraryEvent(song, library, oldRate, newRate);
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
	
	@Test
	@DisplayName("Checks get old song rate: the old rate returned must be BAD")
	public void testGetOldRate() {
		assertEquals(oldRate, event.getOldRate());
	}
	
	@Test
	@DisplayName("Checks get new song rate: the new rate returned must be VERY_BAD")
	public void testGetNewRate() {
		assertEquals(newRate, event.getNewRate());
	}

}
