package train;


/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;

	public Railway(Element[] elements) {
		if(elements == null)
			throw new NullPointerException();
		
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
	}

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
}
