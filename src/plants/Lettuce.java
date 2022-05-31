package plants;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {
	private static Lettuce instance = null;
	/**
	 * Singleton for Lettuce. thread-safe. DCL.
	 * @return instance.
	 */
	public static Lettuce makeInstance(){
		if(instance == null){
			synchronized (Lettuce.class){
				if(instance == null)
					instance = new Lettuce();
			}
		}
		return instance;
	}
	private Lettuce() {
		this.loadImages("lettuce");
	}
}
