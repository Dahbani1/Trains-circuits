package train;


/**
 * Représentation d'une gare intermédiaire. C'est une sous-classe de la classe {@link Station}.
 * Une gare est caractérisée par un nom, un nombre de quais (donc de trains
 * qu'elle est susceptible d'accueillir à un instant donné).
 * 
 */
public class StationInter extends Station {
	
	/**
	 * Constructor of the StationInter class. Initializes the Station with a name, size, and number of trains.
	 * @param name The name of the Station.
	 * @param size The size of the Station, representing the number of platforms.
	 * @param trains The number of trains in the Station.
	 * @throws NullPointerException if the name is null or the size is less than or equal to 0.
	 */
	public StationInter(String name, int size, int trains) {
		super(name, size, trains);
		if(name == null || size <=0)
			throw new NullPointerException();
	}
	

}
