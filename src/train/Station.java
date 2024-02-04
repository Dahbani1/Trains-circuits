package train;

/**
 * The Station class represents a station. It is a subclass of the {@link Element} class.
 * A Station is characterized by a name and a number of platforms (thus the number of trains it can accommodate at a given time).
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Station extends Element {

	/**
	 * Constructor of the Station class. Initializes the Station with a name, size, and number of trains.
	 * @param name The name of the Station.
	 * @param size The size of the Station, representing the number of platforms.
	 * @param trains The number of trains in the Station.
	 * @throws NullPointerException if the name is null or the size is less than or equal to 0.
	 */
	public Station(String name, int size, int trains) {
		super(name, size, trains);
		if(name == null || size <=0)
			throw new NullPointerException();
	}

	/**
	 * Checks if the Station is full. For the Station class, this method always returns false.
	 * @return False, indicating that the Station is not full.
	 */
	@Override
	public boolean isFull() {
		return false;
	}

	/**
	 * Checks if the Station will be full after adding one more train.
	 * @return True if the Station will be full, false otherwise.
	 */
	public boolean willBeFull() {
		return this.getTrains() == this.getSize();
	}

	/**
	 * Notifies the trains in the Station and updates their Position.
	 * If the railway direction is not null and not the same as the train direction, or the next station will be full, it waits.
	 * @param t The Train to be notified.
	 */
	@Override
	public synchronized void notifyTrains(Train t) {
		Direction trainDirection = t.getPosition().getDirection();
		while((this.railway.railwayDirection != null & this.railway.railwayDirection != trainDirection) || this.nextStation(t.getPosition()).willBeFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.decrementTrains();
		t.getPosition().setElement(t.nextElement());
		this.railway.railwayDirection = trainDirection;
		this.railway.railwayTrains++;
		this.nextStation(t.getPosition()).incrementTrains();
		notifyAll();
	}

	/**
	 * Allows a Train to enter the Station if it is not full.
	 * @param t The Train to be allowed.
	 */
	@Override
	public synchronized void allowTrain(Train t) {
		while (this.isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.railway.railwayTrains--;
		this.railway.railwayDirection = this.railway.railwayTrains==0 ? null : this.railway.railwayDirection;
		notifyAll();
	}
}
