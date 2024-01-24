package train;

import java.util.ArrayList;

/**
 * Cette classe abstraite est la représentation générique d'un élément de base d'un
 * circuit, elle factorise les fonctionnalitÃ©s communes des deux sous-classes :
 * l'entrée d'un train, sa sortie et l'appartenance au circuit.<br/>
 * Les deux sous-classes sont :
 * <ol>
 *   <li>La représentation d'une gare : classe {@link Station}</li>
 *   <li>La représentation d'une section de voie ferrée : classe {@link Section}</li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public abstract class Element {
	private final String name;
	protected Railway railway;
	private int trains;
	private int size;

	protected Element(String name) {
		if(name == null)
			throw new NullPointerException();
		
		this.name = name;
		this.trains = 0;
	
	}

	public void setRailway(Railway r) {
		if(r == null)
			throw new NullPointerException();
		
		this.railway = r;
	}
	
	public Element nextElement(Position pos) {
		return this.railway.nextElement(pos);
	}
	
	public boolean isFull() {
		return trains == size;
	}
	
	public synchronized void getTrain() {
		while (this.isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		this.trains++;
		
		notifyAll();
	}

	@Override
	public String toString() {
		return this.name;
	}
}
