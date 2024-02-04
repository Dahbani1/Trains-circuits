package train;

/**
 * The Position class represents the position of a train in the circuit.
 * A position is characterized by two values:
 * <ol>
 *   <li>The element where the train is positioned: a station (class {@link Station})
 *   or a railway section (class {@link Section}).</li>
 *   <li>The direction it takes (enumeration {@link Direction}): from left
 *   to right or from right to left.</li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr> Modified by Mayte Segarra
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 * @version 0.3
 */
public class Position implements Cloneable {
	private  Direction direction;
	private  Element element;

	/**
	 * Constructor of the Position class. Initializes the Position with an Element and a Direction.
	 * @param elt The Element where the train is positioned.
	 * @param d The Direction the train is taking.
	 * @throws NullPointerException if the Element or the Direction is null.
	 */
	public Position(Element elt, Direction d) {
		if (elt == null || d == null)
			throw new NullPointerException();

		this.element = elt;
		this.direction = d;
	}

	/**
	 * Clones the Position object.
	 * @return A clone of the Position object.
	 * @throws CloneNotSupportedException if the object's class does not implement the Cloneable interface.
	 */
	@Override
	public Position clone() {
		try {
			return (Position) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns the Element of the Position.
	 * @return The Element of the Position.
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * Returns the Direction of the Position.
	 * @return The Direction of the Position.
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Returns a string representation of the Position.
	 * @return A string representation of the Position.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(this.element.toString());
		result.append(" going ");
		result.append(this.direction);
		return result.toString();
	}

	/**
	 * Sets the Element of the Position.
	 * @param elt The Element to be set.
	 */
	public void setElement(Element elt) {
		this.element = elt;	
	}

	/**
	 * Sets the Direction of the Position.
	 * @param d The Direction to be set.
	 */
	public void setDirection(Direction d) {
		this.direction = d;	
	}

	/**
	 * Turns the Direction of the Position to the opposite direction.
	 */
	public void turn() {
		this.direction = this.direction.turn();
	}
}
