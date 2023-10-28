package domain.core;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 *
 * Enum {@link Rate} used to represent the different possible values of song ratings.
 */
public enum Rate {

	UNRATED, VERY_BAD, BAD, OK, GOOD, VERY_GOOD;

	/**
	 * This method returns the rating above the current rating, if there is any. 
	 * If the current rating is the greatest one then it returns itself.
	 * 
	 * @ensures {@code \result != null}
	 * @return 	if there is a rating above the current one returns it, otherwise returns the current rating.
	 */
	public Rate inc() {
		int currentIndex = ordinal();
		Rate[] ratings = Rate.values();
		int newIndex = Math.min(currentIndex + 1, ratings.length - 1);
		return ratings[newIndex];
	}

	/**
	 * This method returns the rating bellow the current rating, if there is any. 
	 * If the current rating is the lowest one then it returns itself.
	 * 
	 * @ensures {@code \result != null}
	 * @return 	if there is a rating bellow the current one returns it, otherwise returns the current rating.
	 */
	public Rate dec() {
		int currentIndex = ordinal();
		Rate[] ratings = Rate.values();
		int newIndex = Math.max(currentIndex - 1, 0);
		return ratings[newIndex];
	}
}