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
		//System.out.println("appel de la distance");
		double distance = 0.0;
			for(Attribute a : allAttributes){
				if(!a.getName().equalsIgnoreCase(Loader.getAttributeclasse().getName())){
				//System.out.println("parcourt de pour l'attribut : "+a.getName()+" de type : "+a.getValue().getName());
					if(a.getValue().getName() == "java.lang.String"){
						
						//System.out.println("vrai string");
						distance += StringType((Valeur)p1.getHash().get(a),(Valeur)p2.getHash().get(a));
						//System.out.println("valeur tempo: "+distance+" a l'attribute "+a.getName());
					}
					if(a.getValue().getName() == "java.util.List"){
						
						//ystem.out.println("vrai list");
						if(a.isRank()){
							//System.out.println("vrai rank");
							distance += rankType(p1,p2,(Valeur)p1.getHash().get(a),(Valeur)p2.getHash().get(a),a);
		
							//System.out.println("valeur tempo: "+distance+" a l'attribute "+a.getName());
						}else{
							//System.out.println("faux rank");
							distance += ListType((Valeur)p1.getHash().get(a),(Valeur)p2.getHash().get(a));
							//System.out.println("valeur tempo: "+distance+" a l'attribute "+a.getName());
						}
						
					}
					if(a.getValue().getName() == "java.lang.Float"){
						
						//System.out.println("vrai float");
						distance += floatType((Valeur) p1.getHash().get(a),(Valeur)p2.getHash().get(a), a);
						//System.out.println("valeur tempo: "+distance+" a l'attribute "+a.getName());
					}
					if(a.getValue().getName() =="java.lang.Integer"){
						
						if(a.isBool()){
							distance += booleanType((Valeur) p1.getHash().get(a),(Valeur)p2.getHash().get(a));
						}else{
							distance += intType((Valeur) p1.getHash().get(a),(Valeur)p2.getHash().get(a), a);
						}
					}
				}else{
					
				}
			}
		
		return Math.sqrt(distance);
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param a
	 * @param b
	 * @param a1
	 * @return
	 */
	private double rankType(Point p1, Point p2,Valeur a, Valeur b,Attribute a1) {
		//System.out.println("v1: "+(a1.getRankOfValues((String)a.getValeur()))+" v2: "+(a1.getRankOfValues((String)b.getValeur()))+" last index: "+a1.getLastIndex());
		//System.out.println("v2bis: "+(((a1.getRankOfValues((String)b.getValeur()))-1)/((float)(a1.getLastIndex()-1)))+" v1bis: "+(((a1.getRankOfValues((String)a.getValeur()))-1)/((float)(a1.getLastIndex()-1))));
		double i = Math.pow(((((a1.getRankOfValues((String)b.getValeur()))-1)/((float)(a1.getLastIndex()-1)))-(((a1.getRankOfValues((String)a.getValeur()))-1)/((float)(a1.getLastIndex()-1)))),2);
		return i;
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double StringType(Valeur v1, Valeur v2){
		if(!v1.getValeur().toString().equalsIgnoreCase(v2.getValeur().toString())){
			return Math.pow(1.0,2);
		}
		return Math.pow(0.0,2);
	}
	
	public double booleanType(Valeur v1, Valeur v2){
		if(!v1.getValeur().toString().equalsIgnoreCase(v2.getValeur().toString())){
			return Math.pow(1.0,2);
		}
		return Math.pow(0.0,2);
	}
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double ListType(Valeur v1, Valeur v2){
		if(!v1.getValeur().toString().equalsIgnoreCase(v2.getValeur().toString())){
			return Math.pow(1.0,2);
		}
		return Math.pow(0.0,2);
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param a
	 * @return
	 */
	public double floatType(Valeur v1, Valeur v2, Attribute a){
		//System.out.println("v1: "+ (v1.getValeur())+" v2: "+v2.getValeur()+" max: "+a.getMaxFloat()+" min : "+a.getMinFloat());
		return Math.pow((((Float)v2.getValeur())-((Float)v1.getValeur())/((a.getMaxFloat())-(a.getMinFloat()))),2);
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param a
	 * @return
	 */
	public double intType(Valeur v1, Valeur v2, Attribute a){
		//System.out.println("v1: "+ (v1.getValeur())+" v2: "+v2.getValeur()+" max: "+a.getMaxInt()+" min : "+a.getMinInt());
		return Math.pow(((((Integer)v2.getValeur())-((Integer)v1.getValeur()))/((a.getMaxInt())-(a.getMinInt()))),2);
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
