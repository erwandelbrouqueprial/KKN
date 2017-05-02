import java.util.List;

public class Knn {

	private List<Point> nouveauPoint; // les points que l'utilisateur va définir pour trouver ses proches voisins.
	private int tolerance; //le nombre de voisin plus proche necessaire à trouver.
	private List<Point> allDataPoint; // les points des données;
	
	public Knn(final List<Point> allDataPoint, final int tolerance) {
		this.allDataPoint = allDataPoint;
		this.tolerance = tolerance;
	}

	/**
	 * @return the allDataPoint
	 */
	public List<Point> getAllDataPoint() {
		return allDataPoint;
	}

	/**
	 * @param allDataPoint the allDataPoint to set
	 */
	public void setAllDataPoint(List<Point> allDataPoint) {
		this.allDataPoint = allDataPoint;
	}

	/**
	 * @return the tolerance
	 */
	public int getTolerance() {
		return tolerance;
	}

	/**
	 * @param tolerance the tolerance to set
	 */
	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	/**
	 * @return the nouveauPoint
	 */
	public List<Point> getNouveauPoint() {
		return nouveauPoint;
	}

	/**
	 * @param nouveauPoint the nouveauPoint to set
	 */
	public void setNouveauPoint(List<Point> nouveauPoint) {
		this.nouveauPoint = nouveauPoint;
	}
	
	public void addPoint(Point p){
		this.nouveauPoint.add(p);
	}
	
}
