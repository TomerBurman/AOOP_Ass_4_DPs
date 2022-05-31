package plants;

/**
 * @author baroh
 *
 */
public  class Cabbage extends Plant {

	private static Cabbage instance = null;

	/**
	 * Singleton for Cabbage. thread-safe. DCL.
	 * @return instance.
	 */
	public static Cabbage makeInstance(){
		if(instance == null){
			synchronized (Cabbage.class){
				if(instance == null)
					instance = new Cabbage();
			}
		}
		return instance;
	}
	private Cabbage() {
		this.loadImages("cabbage");
	}


}
