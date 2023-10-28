package domain.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.adts.RegExpMatchable;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Record {@link SongMetaInfo} used to represent and store the metainfo from a certain song.
 * 
 * @param title the title of the song.
 * @param genre the genre of the song.
 * @param artists the list of artists names that created this song.
 * @param album the album of this song.
 * @requires {@code title != null && genre != null && artists != null && album != null}
 */
public record SongMetaInfo(String title, String genre, List<String> artists, String album) implements RegExpMatchable {

	@Override
	public boolean matches(String regexp) {
		// Pattern compilado em vez de usar Pattern.matches para evitar multiplas compilações do mesmo pattern
		Pattern pattern = Pattern.compile(regexp);
		Matcher titleMatcher = pattern.matcher(this.title);
		Matcher genreMatcher = pattern.matcher(this.genre);
		Matcher albumMatcher = pattern.matcher(this.album);
		// Lista para o fim, lazy evaluation
		return titleMatcher.find() ||
				genreMatcher.find() ||
				albumMatcher.find() ||
				this.artists.stream().anyMatch(x -> pattern.matcher(x).find());
	}

	/**
	 * Returns a copy of the list of artists names in this metainfo.
	 * 
	 * @ensures {@code \result != null}
	 * @return 	a copy of the list of artists names in this metainfo.
	 */
	@Override
	public List<String> artists() {
		return new ArrayList<>(this.artists);
	}
}
