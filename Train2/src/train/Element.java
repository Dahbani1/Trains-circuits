package train;

import java.util.Arrays;
import java.util.List;

/**
 * Cette classe abstraite est la représentation générique d'un élément de base d'un
 * circuit, elle factorise les fonctionnalitÃ©s communes des deux sous-classes :
 * l'entrée d'un train, sa sortie et l'appartenance au circuit.<br/>
 * Les deux sous-classes sont :
 * <ol>
 *   <li>La représentation d'une gare : classe {@link Station}</li>
 *   <li>La représentation d'une section de voie ferrée : classe {@link Section}</li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public abstract class Element {
	private final String name;
	protected Railway railway;
	private int size;
	private int trains;
	
	
	/**
	 * Constructor of the Element class. Initializes the Element with a name, size, and number of trains.
	 * @param name Name of the Element.
	 * @param size Size of the Element.
	 * @param trains Number of trains in the Element.
	 * @throws NullPointerException if the name is null.
	 */
	protected Element(String name, int size, int trains) {
		if(name == null)
			throw new NullPointerException();
		
		this.name = name;
		this.size = size;
		this.trains = trains;
	}
	
	/**
	 * Decrements the number of trains in the Element.
	 */
	public void decrementTrains() {
		this.trains--;
	}
	
	
	/**
	 * Increments the number of trains in the Element.
	 */
	public void incrementTrains() {
		this.trains++;
	}

	/**
	 * Sets the Railway of the Element.
	 * @param r The Railway to be set.
	 * @throws NullPointerException if the Railway is null.
	 */
	public void setRailway(Railway r) {
		if(r == null)
			throw new NullPointerException();
		
		this.railway = r;
	}
	
	
	/**
	 * Returns the next Element in the given Position.
	 * @param pos The Position to check.
	 * @return The next Element in the Position.
	 */
	public Element nextElement(Position pos) {
		return this.railway.nextElement(pos);
	}
	
	
	/**
	 * Checks if the Element is full.
	 * @return True if the Element is full, false otherwise.
	 */
	public boolean isFull() {
		return this.trains == this.size;
	}
	
	
	/**
	 * Notifies the trains in the Element and updates their Position.
	 * @param t The Train to be notified.
	 */
	public synchronized void notifyTrains(Train t) {
		this.decrementTrains();
		t.getPosition().setElement(t.nextElement());
		notifyAll();
	}
	
	
	/**
	 * Allows a Train to enter the Element if it is not full and the direction is correct and the next station won't be full when the current position's element is a Station.
	 * @param t The Train to be allowed.
	 */
	public synchronized void allowTrain(Train t) {
		Direction trainDirection = t.getPosition().getDirection();
		
		List<Station> stationList = Arrays.asList(this.railway.stations);
		int index = stationList.indexOf(this.nextStation(trainDirection));
		
		if(trainDirection == Direction.LR) {
				while(this.isFull() || ((this.railway.railwayDirection[index-1] != null & this.railway.railwayDirection[index-1] != trainDirection) || (this.nextStation(trainDirection).willBeFull() & (t.getPosition().getElement() instanceof Station)))) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			     }
			
		}
		
		if(trainDirection == Direction.RL) {
				while(this.isFull() || ((this.railway.railwayDirection[index] != null & this.railway.railwayDirection[index] != trainDirection) || (this.nextStation(trainDirection).willBeFull() & (t.getPosition().getElement() instanceof Station)))) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
	
			this.incrementTrains();
			notifyAll();
	}

	/**
	 * Returns the name of the Element.
	 * @return The name of the Element.
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
	
	/**
	 * Returns the number of trains in the Element.
	 * @return The number of trains in the Element.
	 */
	public int getTrains() {
		return this.trains;
	}
	
	
	/**
	 * Returns the size of the Element.
	 * @return The size of the Element.
	 */
	public int getSize() {
		return this.size;
	}
	
	
	/**
	 * Returns the next Station in the given Direction.
	 * @param direction The direction to check.
	 * @return The next Station in the Direction given.
	 */
	public Station nextStation(Direction direction) {
		Position pos= new Position(this, direction);
		return this.railway.nextStation(pos);
	}
	
	
	
}
