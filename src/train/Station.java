package train;

/**
 * Représentation d'une gare. C'est une sous-classe de la classe {@link Element}.
 * Une gare est caractérisée par un nom et un nombre de quais (donc de trains
 * qu'elle est susceptible d'accueillir à un instant donné).
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Station extends Element {

	public Station(String name, int size, int trains) {
		super(name, size, trains);
		if(name == null || size <=0)
			throw new NullPointerException();
	}
	
	
	
//	@Override
//	public synchronized void allowTrain(Train t) {
//		while (this.isFull()) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		t.moveToNextElement();
//	}
	
	@Override
	public synchronized void notifyTrains(Train t) {
		Direction trainDirection = t.getPosition().getDirection();
		while(this.railway.railwayDirection != null & this.railway.railwayDirection != trainDirection) {
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
		notifyAll();
	}
	
	@Override
	public synchronized void allowTrain(Train t) {
		while (this.isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.incrementTrains();
		this.railway.railwayTrains--;
		this.railway.railwayDirection = this.railway.railwayTrains==0 ? null : this.railway.railwayDirection;
		notifyAll();
	}
}
