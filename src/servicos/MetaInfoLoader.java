package servicos;

import java.util.ArrayList;
import java.util.List;

import domain.core.SongMetaInfo;

/**
 * Class that allows to load {@link SongMetaInfo}.
 * 
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 */
public class MetaInfoLoader {
	
	private final List<IMetaInfoFactory> factories = new ArrayList<>();
	
	/**
	 * Constructor that creats a {@link MetaInfoLoader}.
	 */
	public MetaInfoLoader() {
		factories.add(new MP3MetaInfoFactory());
	}
	
	/**
	 * Method that loads the {@link MetaInfo} of the given path.
	 * 
	 * @param path	the given path to be loaded.
	 * @requires 	{@code path != null}
	 * @return 		the {@link MetaInfoLoader} of the given path, or null if the path
	 * 				can't be loaded.
	 */
	public SongMetaInfo load(String path) {
		for (IMetaInfoFactory factory : factories) {
			if(factory.canLoad(path))
				return factory.load(path);
		}
		return null;
	}
}
