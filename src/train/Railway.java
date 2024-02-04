package train;


/**
 * The Railway class represents a circuit made up of railway elements: station or railway section.
 * It contains an array of Elements that make up the railway, an array of Stations on the railway,
 * the direction of the railway, and the number of trains on the railway.
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;
	private final Station[] stations;
	public Direction railwayDirection;
	public int railwayTrains;

	/**
	 * Constructor of the Railway class. Initializes the Railway with an array of Elements and an array of Stations.
	 * @param elements The array of Elements that make up the railway.
	 * @param stations The array of Stations on the railway.
	 * @throws NullPointerException if the array of Elements is null.
	 */
	public Railway(Element[] elements, Station[] stations) {
		if(elements == null)
			throw new NullPointerException();
		
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
		this.railwayDirection = null;
		this.railwayTrains = 0;
		this.stations=stations;
	}

	/**
	 * Returns a string representation of the Railway.
	 * @return A string representation of the Railway.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Element e : this.elements) {
			if (first)
				first = false;
			else
				result.append("--");
			result.append(e);
		}
		return result.toString();
	}
	
	/**
	 * Returns the next Element in the given Position.
	 * @param pos The Position to check.
	 * @return The next Element in the Position.
	 */
	public Element nextElement(Position pos) {
		Element currElement = pos.getElement();
		Direction currDirection = pos.getDirection();
		
		for(int i=0; i<this.elements.length; i++) {
			if(this.elements[i] == currElement) {			
				if(currDirection == Direction.LR) {
					if(i == this.elements.length - 1) {
						pos.turn();
						return this.elements[i - 1];
					} else {
						return this.elements[i + 1];
					}
				} else {
					if(i == 0) {
						pos.turn();
						return this.elements[i + 1];
					} else {
						return this.elements[i - 1];
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the next Station after the given Station.
	 * @param stat The Station to check.
	 * @return The next Station after the given Station.
	 */
	public Station nextStation(Station stat) {
		int a =0;
		for (int i = 0; i <= this.stations.length-1; i++) {
			if(stat==this.stations[i]) {
				 a =i;
				 break;
			}
		}
		
		
		return this.stations[(a+1)%(this.stations.length)];
		
	}
	
	/**
	 * Returns the next Station in the given Position.
	 * @param pos The Position to check.
	 * @return The next Station in the Position.
	 */
	public Station nextStation(Position pos) {
			
			Element currElement = pos.getElement();
			Direction currDirection = pos.getDirection();
			
			for(int i=0; i<this.elements.length; i++) {
				if(this.elements[i] == currElement) {			
					if(currDirection == Direction.LR) {
						if(i == this.elements.length - 1) {
							pos.turn();
							return ((Station)this.elements[0]);
						} else {
							return ((Station)this.elements[this.elements.length - 1]);
						}
					} else {
						if(i == 0) {
							pos.turn();
							return ((Station)this.elements[this.elements.length - 1]);
						} else {
							return ((Station)this.elements[0]);
						}
					}
				}
			}
			return null;
		}
		
			
	
	
}
