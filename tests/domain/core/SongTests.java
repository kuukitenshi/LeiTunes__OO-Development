package domain.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SongTests {

	private List<String> artistsList;
	private SongMetaInfo metaInfo;
	private Song song;
	
	@BeforeEach
	void setup() {
        artistsList = Arrays.asList("Alan Walker", "Au/Ra", "Tomine Harket");
        metaInfo = new SongMetaInfo("Darkside", "Pop", artistsList, "Different World");
        song = new Song(metaInfo, "Darkside Song");
    }
	
	@Test
	@DisplayName("Checks the number of times that the song was played by incrementing: the number is 0 or greater")
	public void testIncTimesPlayed() {
		assertEquals(0, song.getTimesPlayed());
		song.incTimesPlayed();
		assertEquals(1, song.getTimesPlayed());
	}
	
	@Test
	@DisplayName("Checks the song rating's by increment: change for a supported rate")
	public void testIncRating() {
		assertEquals(Rate.UNRATED, song.getRating());
		song.incRating();
		assertEquals(Rate.VERY_BAD, song.getRating());
		
		Song song2 = new Song(Rate.OK);
		song2.incRating();
		assertEquals(Rate.GOOD, song2.getRating());
		song2.incRating();
		song2.incRating();
		assertEquals(Rate.VERY_GOOD, song2.getRating());
	}
	
	@Test
	@DisplayName("Checks the song rating's by decrement: change for a supported rate")
	public void testDecRating() {
		assertEquals(Rate.UNRATED, song.getRating());
		song.decRating();
		assertEquals(Rate.UNRATED, song.getRating());
		
		Song song2 = new Song(Rate.BAD);
		assertEquals(Rate.BAD, song2.getRating());
		song2.decRating();
		assertEquals(Rate.VERY_BAD, song2.getRating());
		song2.decRating();
		song2.decRating();
		assertEquals(Rate.UNRATED, song2.getRating());
	}

	@Test
	@DisplayName("Check song title's: the title of the song should be 'Darkside'")
	public void testGetTitle() {
		assertEquals("Darkside", song.getSongTitle());
	}
	
	@Test
	@DisplayName("Check song genre's: the genre of the song should be 'Pop'")
	public void testGetGenre() {
		assertEquals("Pop", song.getGenre());
	}
	
	@Test
	@DisplayName("Check song artists': the artists of the song should be 'Alan Walker, Au/Ra, Tomine Harket'")
	public void testGetArtists() {
		assertEquals(Arrays.asList("Alan Walker", "Au/Ra", "Tomine Harket"), song.getArtists());
	}
	
	
	@Test
	@DisplayName("Check song album's: the album of the song should be 'Different World'")
	public void testGetAlbum() {
		assertEquals("Different World", song.getAlbum());
	}
	
	@Test
	@DisplayName("Check song filename's: the filename of the song should be 'Darkside'")
	public void testGetFilename() {
		assertEquals("Darkside Song", song.getFilename());
	}
	
	@Test
	@DisplayName("Check matches: the metaInfo should match with the given expression")
	public void testMatches() {
		//match list with one element
		List<String> artistsList1 = Arrays.asList("Mariza", "OwO");
		SongMetaInfo metaInfo1 = new SongMetaInfo("Meu Fado", "Pop", artistsList1, "Transparente");
		Song song1 = new Song(metaInfo1, "MeuFado");
		assertTrue(song1.matches(".*iza*")); 

		//match title;
		assertTrue(song.matches(".*ark*")); 
	}
	
	@Test
	@DisplayName("Check textual representation: the textual representation should be the same as the given")
	public void testToString() {
		String expected = "[Darkside, Different World, Pop, [Alan Walker; Au/Ra; Tomine Harket]] --- 0 -- 0";
		assertEquals(expected, song.toString());
	}
	
	@Test
	@DisplayName("Check equals : checks if two song are the same or not")
	public void testEquals() {
		List<String> artistsList2 = Arrays.asList("Alan Walker", "Au/Ra", "Tomine Harket");
		SongMetaInfo metaInfo2 = new SongMetaInfo("Darkside", "Pop", artistsList2, "Different World");
		Song song2 = new Song(metaInfo2, "Darkside Song");
		
		List<String> artistsList3 = Arrays.asList("Mariza", "OwO");
		SongMetaInfo metaInfo3 = new SongMetaInfo("Meu Fado", "Pop", artistsList3, "Transparente");
		Song song3 = new Song(metaInfo3, "MeuFado");
		
		Song song4 = new Song(metaInfo2, "Darkside Song", Rate.GOOD, 0);
		Song song5 = new Song(metaInfo2, "Darkside Song", Rate.UNRATED, 3);
		Song song6 = new Song(metaInfo3, "Darkside Song", Rate.UNRATED, 0);

		assertTrue(song.equals(song)); //o == this
		assertFalse(song.equals(metaInfo3)); //getClass()
		assertTrue(song.equals(song2));//equals
		assertFalse(song.equals(song3)); //fileName
		assertFalse(song.equals(null)); //null
		assertFalse(song.equals(song4)); // rate
		assertFalse(song.equals(song5)); // playCount
		assertFalse(song.equals(song6)); // metaInfo
	}
	
	@Test
	@DisplayName("Check hash code: if two song are equals the hashcode of them shoud be equals too")
	public void testHashCode() {
		List<String> artistsList2 = Arrays.asList("Alan Walker", "Au/Ra", "Tomine Harket");
		SongMetaInfo metaInfo2 = new SongMetaInfo("Darkside", "Pop", artistsList2, "Different World");
		Song song2 = new Song(metaInfo2, "Darkside Song");
		
		List<String> artistsList3 = Arrays.asList("Mariza", "OwO");
		SongMetaInfo metaInfo3 = new SongMetaInfo("Meu Fado", "Pop", artistsList3, "Transparente");
		Song song3 = new Song(metaInfo3, "MeuFado");

		assertEquals(song.hashCode(), song2.hashCode());
		assertNotEquals(song.hashCode(), song3.hashCode());		
	}
}
