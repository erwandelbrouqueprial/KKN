import java.util.List;

/**
 * 
 */

/**
 * @author erwan
 *
 */
public class Distance {
	private Point p1;
	private Point p2;
	private List<Attribute> allAttributes;
	
	public Distance(final Point p1, final Point p2,List<Attribute> liste) {
		this.p1 = p1;
		this.p2 = p2;
		allAttributes = liste;
	}

	public double getDistance(){
		for(Attribute a : allAttributes){
			p1.getHash().get(a);
			p2.getHash().get(a);
			if( a.getValue().getName() == "java.lang.String"){
				return StringType((Valeur)p1.getHash().get(a).getValeur(),(Valeur)p2.getHash().get(a).getValeur());
			}else if(a.getValue().toString() == "java.util.List"){
				
			}else if(a.getValue().getName() == "java.lang.Float"){
				
			}
		}
		return 0.0;
	}
	
	public double StringType(Valeur v1, Valeur v2){
		if(v1.getValeur().toString() != v2.getValeur().toString()){
			return 1.0;
		}
		return 0.0;
	}
	
	public double BooleanType(Valeur v1, Valeur v2){
		if(v1.getValeur().toString() != v2.getValeur().toString()){
			return 1.0;
		}
		return 0.0;
	}
	
	public double ListType(Valeur v1, Valeur v2){
		if(v1.getValeur().toString() != v2.getValeur().toString()){
			return 1.0;
		}
		return 0.0;
	}

	
	
	
	/**
	 * @return the p2
	 */
	public Point getP2() {
		return p2;
	}

	/**
	 * @param p2 the p2 to set
	 */
	public void setP2(Point p2) {
		this.p2 = p2;
	}

	/**
	 * @return the p1
	 */
	public Point getP1() {
		return p1;
	}

	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	
	
}
