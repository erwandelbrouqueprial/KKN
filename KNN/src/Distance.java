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
		double distance = 0.0;
		for(Attribute a : allAttributes){
			p1.getHash().get(a);
			p2.getHash().get(a);
			if( a.getValue().getName() == "java.lang.String"){
				distance += StringType((Valeur)p1.getHash().get(a),(Valeur)p2.getHash().get(a));
			}else if(a.getValue().toString() == "java.util.List"){
				if(a.isRank()){
					distance += rankType(p1,p2,(Valeur)p1.getHash().get(a),(Valeur)p2.getHash().get(a),a);
				}else{
					distance += StringType((Valeur)p1.getHash().get(a),(Valeur)p2.getHash().get(a));
				}
			}else if(a.getValue().getName() == "java.lang.Float"){
				distance += floatType((Valeur) p1.getHash().get(a),(Valeur)p2.getHash().get(a), a);
			}else if(a.getValue().getName() =="java.lang.Integer"){
				if(a.isBool()){
					distance += booleanType((Valeur) p1.getHash().get(a),(Valeur)p2.getHash().get(a));
				}else{
					distance += intType((Valeur) p1.getHash().get(a),(Valeur)p2.getHash().get(a), a);
				}
			}
		}
		return Math.sqrt(distance);
	}

	private double rankType(Point p1, Point p2,Valeur a, Valeur b,Attribute a1) {
		double i = Math.pow((((a1.getRankOfValues((String)b.getValeur()))-(a1.getRankOfValues((String)a.getValeur())))/((a1.getRankOfValues(a1.getLastIndex()))-(a1.getRankOfValues(a1.getFirstIndex())))),2);
		return i;
	}

	public double StringType(Valeur v1, Valeur v2){
		if(v1.toString()!= v2.toString()){
			return Math.pow(1.0,2);
		}
		return Math.pow(0.0,2);
	}
	
	public double booleanType(Valeur v1, Valeur v2){
		if(v1.getValeur().toString() != v2.getValeur().toString()){
			return Math.pow(1.0,2);
		}
		return Math.pow(0.0,2);
	}
	
	public double ListType(Valeur v1, Valeur v2){
		if(v1.getValeur().toString() != v2.getValeur().toString()){
			return Math.pow(1.0,2);
		}
		return Math.pow(0.0,2);
	}
	
	public double floatType(Valeur v1, Valeur v2, Attribute a){
		return Math.pow((((float)v2.getValeur())-((float)v1.getValeur())/((a.getMaxFloat())-(a.getMinFloat()))),2);
	}

	public double intType(Valeur v1, Valeur v2, Attribute a){
		return Math.pow(((((int)v2.getValeur())-((int)v1.getValeur()))/((a.getMaxInt())-(a.getMinInt()))),2);
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
