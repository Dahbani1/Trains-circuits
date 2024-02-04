package train;

/**
 * Représentation de la direction que peut prendre un train : de gauche à droite
 * ou de droite à gauche.
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public enum Direction {
	/**
	 * Represents the direction from left to right.
	 * The toString method returns "-->".
	 * The turn method returns the opposite direction, RL.
	 */
	LR {
		@Override
		public String toString() {
			return "-->";
		}
		
        @Override
        public Direction turn() {
            return RL;
        }
	},
	
	/**
	 * Represents the direction from right to left.
	 * The toString method returns "<--".
	 * The turn method returns the opposite direction, LR.
	 */
	RL {
		@Override
		public String toString() {
			return "<--";
		}
		
        @Override
        public Direction turn() {
            return LR;
        }
	};
	
	/**
	 * Abstract method to be implemented by each direction.
	 * It returns the opposite direction.
	 * @return The opposite direction.
	 */
	
	public abstract Direction turn();
}
