package servicos;

import domain.core.SongMetaInfo;

/**
 * Interface that represents a factory of {@link SongMetaInfo}.
 * 
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 */
public interface IMetaInfoFactory {

	/**
	 * Method that loads a given path.
	 * 
	 * @param path	the given path.
	 * @requires 	{@code path != null}
	 * @return		the {@link SongMetaInfo} of the path loaded.
	 */
	SongMetaInfo load(String path);
	
	/**
	 * Check if the given path can be loaded.
	 * 
	 * @param path 	the given path to be loaded.
	 * @requires 	{@code path != null}
	 * @return 		true if the path can be loaded, and false otherwise.
	 */
	boolean canLoad(String path);
}
