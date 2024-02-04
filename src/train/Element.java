package train;

/**
 * This abstract class is a generic representation of a basic element of a circuit.
 * It factors the common functionalities of the two subclasses: 
 * the entry of a train, its exit, and belonging to the circuit.
 * The two subclasses are:
 * <ol>
 *   <li>The representation of a station: {@link Station} class</li>
 *   <li>The representation of a railway section: {@link Section} class</li>
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
	 * Allows a Train to enter the Element if it is not full and the direction is correct.
	 * @param t The Train to be allowed.
	 */
	public synchronized void allowTrain(Train t) {
		Direction trainDirection = t.getPosition().getDirection();
		while(this.isFull() || ((this.railway.railwayDirection != null & this.railway.railwayDirection != trainDirection) || (this.nextStation(t.getPosition()).willBeFull() & (t.getPosition().getElement() instanceof Station)))) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
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
	 * Returns the next Station in the given Position.
	 * @param pos The Position to check.
	 * @return The next Station in the Position.
	 */
	public Station nextStation(Position pos) {
		return this.railway.nextStation(pos);
	}
	
	
	
}
