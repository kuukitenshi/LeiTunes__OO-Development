package domain.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SongMetaInfoTests {
	
	private List<String> artistsList;
	private SongMetaInfo metaInfo;
	
	@BeforeEach
	void setup() {
		artistsList = Arrays.asList("Alan Walker", "Au/Ra", "Tomine Harket");
		metaInfo = new SongMetaInfo("Darkside", "Pop", artistsList, "Different World");
	}
	
	@Test
	@DisplayName("Checks matches: the metaInfo should match with the given expression")
	public void testMatches() {
		assertTrue(metaInfo.matches(".*ark*")); //title
		assertTrue(metaInfo.matches(".*op*"));	//genre
		assertFalse(metaInfo.matches(".*wealk*")); //artist
		assertTrue(metaInfo.matches(".*omine Ha*")); // multiple artists
		assertTrue(metaInfo.matches(".*Differ*"));//album
	}
	
	
	@Test
	@DisplayName("Checks song metadata title's: the title of the song should be 'Darkside'")
	public void testGetTitle() {
		assertEquals("Darkside", metaInfo.title());
	}
	
	@Test
	@DisplayName("Checks song metadata genre's: the genre of the song should be 'Pop'")
	public void testGetGenre() {
		assertEquals("Pop", metaInfo.genre());
	}
	
	@Test
	@DisplayName("Checks song metadata artists': the artists of the song should be 'Alan Walker, Au/Ra, Tomine Harket'")
	public void testGetArtists() {
		assertEquals(Arrays.asList("Alan Walker", "Au/Ra", "Tomine Harket"), metaInfo.artists());
	}
	
	@Test
	@DisplayName("Checks song metadata album's: the album of the song should be 'Different World'")
	public void testGetAlbum() {
		assertEquals("Different World", metaInfo.album());
	}
}
