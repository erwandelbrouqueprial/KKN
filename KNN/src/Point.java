import java.util.HashMap;

/**
 * 
 */

/**
 * @author admin
 *
 */
public class Point {
	
	private String num; // nom du point;
	private HashMap<Attribute,Valeur> keyAttrVal;//correspondant attribut point
	private double x;
	private double y;
	
	public Point(String num) {
		this.num = num;
		keyAttrVal = new HashMap<Attribute,Valeur >();
	}
	public Point(String num,double x, double y) {
		this.num = num;
		this.x = x;
		this.y = y;
		keyAttrVal = new HashMap<Attribute,Valeur >();
	}
	
	public void addIntoHash(Attribute attr, Valeur val){
		keyAttrVal.put(attr, val);
	}

	public HashMap<Attribute, Valeur> getHash(){
		return keyAttrVal;
	}
	
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}


	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}


	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	
}
