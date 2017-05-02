import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Knn {

	private List<Point> nouveauPoint; // les points que l'utilisateur va définir pour trouver ses proches voisins.
	private int tolerance; //le nombre de voisin plus proche necessaire à trouver.
	private List<Point> allDataPoint; // les points des données;
	private List<Attribute> allAttribute;
	private HashMap<Point,ArrayList<Distance>> matrices;
	private HashMap<Point,ArrayList<Point>> kplusproche;
	private int nbClust;

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

	public Knn(List<Point> allDataPoint, int tolerance, List<Attribute> allAttribute, int nbClust) {
		this.allDataPoint = allDataPoint;
		this.tolerance = tolerance;
		this.allAttribute = allAttribute;
		this.nouveauPoint = new ArrayList<Point>();
		this.matrices = new HashMap<Point,ArrayList<Distance>>();
		this.kplusproche = new HashMap<Point,ArrayList<Point>>();
		this.nbClust = nbClust;
		
	}

	public void run(){
		ArrayList<Integer> randoms = new ArrayList<Integer>();
		for(int i = 0 ; i <nbClust ; i++){
			boolean t =false;
			while(t == false){
				Random r = new Random();
				int result = r.nextInt(allDataPoint.size()-1);
				if(!randoms.contains(result)){
					randoms.add(result);
					System.out.println(result);
					nouveauPoint.add(allDataPoint.get(result));
					t = true;
				}
			}
		}
		for(Point p : nouveauPoint){
			this.matrices.put(p, new ArrayList<Distance>());
			this.kplusproche.put(p, new ArrayList<Point>());
			
		}
		for(Point newP : nouveauPoint){
			for(Point p : allDataPoint){
					Distance d = new Distance(newP, p, allAttribute);
					System.out.println("distance entre "+newP.getNum()+" et "+p.getNum()+" : "+d.getDistance());
					matrices.get(newP).add(d);
				
				
			}
			System.out.println(" ");
		}
		int i = 0;
		int nv = 0;
		int nvb =0;
		while(i < matrices.size()){
			if((i+1)< matrices.size()){
				while(nv <matrices.get(nouveauPoint.get(i)).size() && nvb < matrices.get(nouveauPoint.get((i+1))).size() ){
					System.out.println(matrices.get(nouveauPoint.get(i)).get(nv).getP2().getNum()+" "+matrices.get(nouveauPoint.get((i+1))).get((nvb)).getP2().getNum());
					
					if(matrices.get(nouveauPoint.get(i)).get(nv).getDistance() < matrices.get(nouveauPoint.get((i+1))).get(nvb).getDistance()){
						matrices.get(nouveauPoint.get((i+1))).remove(nvb);
						nv++;
					}else if(matrices.get(nouveauPoint.get(i)).get(nv).getDistance() > matrices.get(nouveauPoint.get((i+1))).get(nvb).getDistance()){
						matrices.get(nouveauPoint.get(i)).remove(nv);
						nvb++;
					}
				}
				/*for(nv = 0 ; nv < allDataPoint.size(); nv++){
					System.out.println(matrices.get(nouveauPoint.get(i)).get(nv).getP2().getNum()+" "+matrices.get(nouveauPoint.get((i+1))).get((nv)).getP2().getNum());
					
					if(matrices.get(nouveauPoint.get(i)).get(nv).getDistance() < matrices.get(nouveauPoint.get((i+1))).get(nv).getDistance()){
						matrices.get(nouveauPoint.get((i+1))).remove(nv);
					}else if(matrices.get(nouveauPoint.get(i)).get(nv).getDistance() > matrices.get(nouveauPoint.get((i+1))).get(nv).getDistance()){
						matrices.get(nouveauPoint.get(i)).remove(nv);
					}
					
				}*/
				i++;
			}else{
				break;
			}
		}
		for(Point newP : nouveauPoint){
			System.out.println("les plus proches voisins de "+newP.getNum()+" sont: ");
			for(int j = 0 ; j < matrices.get(newP).size(); j++){
				System.out.println(matrices.get(newP).get(j).getP2());
			}
		
		}
		for(Point newP : nouveauPoint){
			
			/*double []tableauDistance = new double[allDataPoint.size()-1];
			for(int i = 0 ; i < allDataPoint.size()-1 ; i++){
				tableauDistance[i]=matrices.get(newP).get(i).getDistance();
			}
			
			Arrays.sort(tableauDistance);
			System.out.println(newP.getNum());
			for(int i = 0 ; i < allDataPoint.size()-1 ; i++){
				System.out.println(tableauDistance[i]);
			}*/
		}
		
		/*
		 Arrays.sort(tableauEntier);
					System.out.println("itération: "+j+" "+tableauEntier[0]+" "+tableauEntier[1]);
					for(int e = 0 ; e < tableauEntier.length ; e++){
						if(matrices.get(nouveauPoint.get(i)).get(j).getDistance() < tableauEntier[e]){
							System.out.println(nouveauPoint.get(i).getNum()+" "+matrices.get(nouveauPoint.get(i)).get(j).getDistance());
							if(tableauEntier[e]<tableauEntier[tableauEntier.length-1]){
								tableauEntier[tableauEntier.length-1] = tableauEntier[e];
								tableauEntier[e] = matrices.get(nouveauPoint.get(i)).get(j).getDistance();
								System.out.println("itération: "+j+" "+tableauEntier[0]+" "+tableauEntier[1]);
								break;
							}else{
								tableauEntier[e] = matrices.get(nouveauPoint.get(i)).get(j).getDistance();
							}
						}
					}*/
		
		
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
