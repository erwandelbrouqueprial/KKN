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
	
	public Point(String num) {
		this.num = num;
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
	
}
