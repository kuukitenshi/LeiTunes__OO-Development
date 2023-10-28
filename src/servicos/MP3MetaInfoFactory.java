package servicos;

import java.io.IOException;
import java.util.Arrays;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import domain.core.SongMetaInfo;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * 
 * Class MP3MetaLoader that implements {@link IFileMetaLoader}, this class
 * is a metainfo loader that loads metainfo from mp3 files.
 */
public class MP3MetaInfoFactory implements IMetaInfoFactory {
	
	// name used when some song does not contains some meta field.
	private static final String UNKNOWN_NAME = "unknown";

	@Override
	public SongMetaInfo load(String path) {	
		try {
			Mp3File mp3 = new Mp3File(path);
			ID3v1 tag = null;
			if (mp3.hasId3v2Tag())
				tag = mp3.getId3v2Tag();
			else if (mp3.hasId3v1Tag())
				tag = mp3.getId3v1Tag();
			if (tag != null) {
				String title = tag.getTitle() != null ? tag.getTitle() : UNKNOWN_NAME;
				String genre = tag.getGenreDescription() != null ? tag.getGenreDescription() : UNKNOWN_NAME;
				String artist = tag.getArtist() != null ? tag.getArtist() : UNKNOWN_NAME;
				String[] artistsSplited = artist.split(";");
				for(int i = 0; i < artistsSplited.length; i++)
					artistsSplited[i] = artistsSplited[i].trim();
				String album = tag.getAlbum() != null ? tag.getAlbum() : UNKNOWN_NAME;
				return new SongMetaInfo(title, genre, Arrays.asList(artistsSplited), album);
			}
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean canLoad(String path) {
		String[] extensionSplit = path.split("\\.");
		String extension = extensionSplit[extensionSplit.length-1].toLowerCase();
		return extension.equals("mp3");
	}
}
