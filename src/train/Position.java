package train;

/**
 * Représentation de la position d'un train dans le circuit. Une position
 * est caractérisée par deux valeurs :
 * <ol>
 *   <li>
 *     L'élément où se positionne le train : une gare (classe  {@link Station})
 *     ou une section de voie ferrée (classe {@link Section}).
 *   </li>
 *   <li>
 *     La direction qu'il prend (enumération {@link Direction}) : de gauche à
 *     droite ou de droite à gauche.
 *   </li>
 * </ol>
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr> Modifié par Mayte
 *         Segarra 
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 *         
 * @version 0.3
 */
public class Position implements Cloneable {
	private  Direction direction;
	private  Element element;

	public Position(Element elt, Direction d) {
		if (elt == null || d == null)
			throw new NullPointerException();

		this.element = elt;
		this.direction = d;
	}

	@Override
	public Position clone() {
		try {
			return (Position) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Element getElement() {
		return element;
	}
	
	public Direction getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(this.element.toString());
		result.append(" going ");
		result.append(this.direction);
		return result.toString();
	}
	
	public void setElement(Element elt) {
		this.element.getTrain();
		this.element = elt;	
	}
	
	public void setDirection(Direction d) {
		this.direction = d;	
	}
	
	public synchronized void turn() {
		this.direction = this.direction.turn();
	}
}
