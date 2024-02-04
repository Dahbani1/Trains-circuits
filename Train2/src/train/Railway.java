package train;

import java.util.Arrays;

/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;
	public final Station[] stations;
	public Direction[] railwayDirection;
	public int[] railwayTrains;

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
		this.stations=stations;
		this.railwayDirection = new Direction[this.stations.length-1];
		Arrays.fill(this.railwayDirection, null);
		this.railwayTrains = new int[this.stations.length-1];
		Arrays.fill(this.railwayTrains, 0);
		
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
							int a=0;
							for(int j=i+1; j<this.elements.length; j++) {
								if(this.elements[j] instanceof Station) {
									a=j;
									break;
								}
							}
							return ((Station)this.elements[a]);
						}
					} else {
						if(i == 0) {
							pos.turn();
							return ((Station)this.elements[this.elements.length - 1]);
						} else {
							int a=0;
							for(int j=i-1; j>0; j--) {
								if(this.elements[j] instanceof Station) {
									a=j;
									break;
								}
							}
							return ((Station)this.elements[a]);
						}
					}
				}
			}
			return null;
		}
		
			
	
	
}
