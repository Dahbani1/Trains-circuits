package train;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Section extends Element {
	
	
	/**
	 * Constructor of the Section class. Initializes the Section with a name.
	 * The size is set to 1, indicating that the Section can only hold one train at a time.
	 * The number of trains is initially set to 0.
	 * @param name The name of the Section.
	 */
	public Section(String name) {
		super(name, 1, 0);
	}
}
