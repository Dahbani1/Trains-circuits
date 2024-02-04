package train;

import java.util.Arrays;
import java.util.List;

/**
 * Représentation d'une gare. C'est une sous-classe de la classe {@link Element}.
 * Une gare est caractérisée par un nom et un nombre de quais (donc de trains
 * qu'elle est susceptible d'accueillir à un instant donné).
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
	 * If the railway direction between two successive train is not null and not the same as the train direction, or the next station will be full, it waits.
	 * @param t The Train to be notified.
	 */
	@Override
	public synchronized void notifyTrains(Train t) {
		Direction trainDirection = t.getPosition().getDirection();
		
		
		List<Station> stationList = Arrays.asList(this.railway.stations);
		int index = stationList.indexOf(this);
	    
		
		if(trainDirection == Direction.LR) {
			while((this.railway.railwayDirection[index] != null & this.railway.railwayDirection[index] != trainDirection) || this.nextStation(trainDirection).willBeFull()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.decrementTrains();
			t.getPosition().setElement(t.nextElement());
			this.railway.railwayDirection[index] = trainDirection;
			this.railway.railwayTrains[index]++;
			this.nextStation(trainDirection).incrementTrains();
			notifyAll();
		}
		
		else if(trainDirection == Direction.RL) {
			while((this.railway.railwayDirection[index-1] != null & this.railway.railwayDirection[index-1] != trainDirection) || this.nextStation(trainDirection).willBeFull()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			this.decrementTrains();
			t.getPosition().setElement(t.nextElement());
			this.railway.railwayDirection[index-1] = trainDirection;
			this.railway.railwayTrains[index-1]++;
			this.nextStation(trainDirection).incrementTrains();
			notifyAll();
		}
	}
	
	
	/**
	 * Allows a Train to enter the Station if it is not full.
	 * @param t The Train to be allowed.
	 */
	@Override
	public synchronized void allowTrain(Train t) {
		Direction trainDirection = t.getPosition().getDirection();
		
		
		List<Station> stationList = Arrays.asList(this.railway.stations);
		int index = stationList.indexOf(this);
		
		if(trainDirection == Direction.LR) {
			while (this.isFull()) {  // this loop is unnecessary as the condition is always False. In fact the train has already checked that he has a place in its next station when it left the previous station.
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			this.railway.railwayTrains[index-1]--;
			this.railway.railwayDirection[index-1] = this.railway.railwayTrains[index-1]==0 ? null : this.railway.railwayDirection[index-1];
			notifyAll();
		}
		
		else if(trainDirection == Direction.RL) {
			while (this.isFull()) { // this loop is unnecessary as the condition is always False. In fact the train has already checked that he has a place in its next station when it left the previous station.
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			this.railway.railwayTrains[index]--;
			this.railway.railwayDirection[index] = this.railway.railwayTrains[index]==0 ? null : this.railway.railwayDirection[index];
			notifyAll();
		}
	}
}
