import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Knn {

	private List<Point> nouveauPoint; // les points que l'utilisateur va définir pour trouver ses proches voisins.
	private int tolerance; //le nombre de voisin plus proche necessaire à trouver.
	private List<Point> allDataPoint; // les points des données;
	private List<Attribute> allAttribute;
	private HashMap<Point,ArrayList<Distance>> matrices;
	private HashMap<Point,ArrayList<Point>> kplusproche;

	public Knn(final List<Point> allDataPoint, final int tolerance) {
		this.allDataPoint = allDataPoint;
		this.tolerance = tolerance;
	}

	public Knn(List<Point> allDataPoint, int tolerance, List<Point> l,List<Attribute> allAttribute) {
		this.allDataPoint = allDataPoint;
		this.tolerance = tolerance;
		this.nouveauPoint = l;
		this.allAttribute = allAttribute;
		this.matrices = new HashMap<Point,ArrayList<Distance>>();
		this.kplusproche = new HashMap<Point,ArrayList<Point>>();
		
		for(Point p : l){
			this.matrices.put(p, new ArrayList<Distance>());
			this.kplusproche.put(p, new ArrayList<Point>());
			
		}
		
	}

	public void run(){
		//pour chaque nouveauPoint on calcul la distance entre celui-ci et l'ensemble des points du jeu de donnée
		for(Point newP : nouveauPoint){
			for(Point p : allDataPoint){
				Distance d = new Distance(newP, p, allAttribute);
				System.out.println(d.getDistance());
				matrices.get(newP).add(d);
				
			}
		}
		int i = 0;
		while(i < matrices.size()){
			double tableauEntier[] = new double[tolerance];
			for(int j = 0 ; j < matrices.get(nouveauPoint.get(i)).size();j++){
				if(j < tolerance ){
						tableauEntier[j] = matrices.get(nouveauPoint.get(i)).get(j).getDistance();
						System.out.println("j: "+j+" "+tableauEntier[j]);
				}else{
					Arrays.sort(tableauEntier);
					System.out.println("itération: "+j+" "+tableauEntier[0]+" "+tableauEntier[1]);
					for(int e = 0 ; e < tableauEntier.length ; e++){
						if(matrices.get(nouveauPoint.get(i)).get(j).getDistance() < tableauEntier[e]){
							if(tableauEntier[e]<tableauEntier[tableauEntier.length-1]){
								tableauEntier[tableauEntier.length-1] = tableauEntier[e];
								tableauEntier[e] = matrices.get(nouveauPoint.get(i)).get(j).getDistance();
								System.out.println("itération: "+j+" "+tableauEntier[0]+" "+tableauEntier[1]);
								break;
							}else{
								tableauEntier[e] = matrices.get(nouveauPoint.get(i)).get(j).getDistance();
							}
						}
					}
				}
				
			}
			
			for(int j  = 0 ; j < tableauEntier.length;j++){
				System.out.println("distance plus proche: "+tableauEntier[j]+" j : "+j);
				for(int z = 0 ; z < matrices.get(nouveauPoint.get(i)).size();z++){
					if(matrices.get(nouveauPoint.get(i)).get(z).getDistance() == tableauEntier[j]){
						System.out.println("TROUVER");
						kplusproche.get(nouveauPoint.get(i)).add(matrices.get(nouveauPoint.get(i)).get(z).getP2());
						System.out.println(matrices.get(nouveauPoint.get(i)).get(z).getP2());
						break;
					}
				}
			}
			i++;
		}
		
		for(Point p : nouveauPoint){
			System.out.println("les plus proches voisin de "+p.getNum()+" sont "+kplusproche.get(p).size());
			System.out.println(kplusproche.get(p).toString());
			for(Point cp : kplusproche.get(p)){
				System.out.println("valeur de la classe que nous cherchons du voisin:"+cp.getNum()+" : "+cp.getHash().get(Loader.getAttributeclasse()).getValeur());
			}
		}
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

	/**
	 * @return the allAttribute
	 */
	public synchronized List<Attribute> getAllAttribute() {
		return allAttribute;
	}

	/**
	 * @param allAttribute the allAttribute to set
	 */
	public synchronized void setAllAttribute(List<Attribute> allAttribute) {
		this.allAttribute = allAttribute;
	}

}
