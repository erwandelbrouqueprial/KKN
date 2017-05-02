import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author delbrouquepri
 *
 */
public class Attribute {
	
	private String name;
	private Class<?> type; 
	private List<String> values;
	private boolean rank;
	private boolean isBool;
	private float minFloat;
	private float maxFloat;
	private int maxInt;
	private int MinInt;
	
	public Attribute(final String name, final Class<?> value) {
		this.name = name;
		this.type = value;
		this.values = new ArrayList<String>();
		this.rank = false;
		this.isBool = false;
		this.minFloat = 0;
		this.maxFloat = 0;
		this.maxInt = 0;
		this.MinInt = 0;
	}
	
	public Attribute(final String name, final Class<?> value,final ArrayList<String>values,boolean rank) {
		this.name = name;
		this.type = value;
		this.values = values;
		this.rank = rank;
		this.isBool = false;
		this.minFloat = 0;
		this.maxFloat = 0;
		this.maxInt = 0;
		this.MinInt = 0;
	}

	public Class<?> getValue() {
		return type;
	}

	public void setValue(Class<?> value) {
		this.type = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the values
	 */
	public List<String> getPossibility() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setPossibility(List<String> values) {
		this.values = values;
	}
	
	public void addPossibilité(String pos){
		this.values.add(pos);
	}
	
	public int getRankOfValues(String val){
		for(int i = 0 ; i < values.size();i++){
			if(values.get(i).equalsIgnoreCase(val)){
				return i+1;
			}
		}
		return 0;
	}

	/**
	 * @return the rank
	 */
	public boolean isRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(boolean rank) {
		this.rank = rank;
	}

	/**
	 * @return the isBool
	 */
	public boolean isBool() {
		return isBool;
	}

	/**
	 * @param isBool the isBool to set
	 */
	public void setBool(boolean isBool) {
		this.isBool = isBool;
	}
	
	public String getLastIndex(){
		return values.get(values.size());
	}
	
	public String getFirstIndex(){
		return values.get(0);
	}
	
	public float getMaxFloat(){
		return maxFloat;
	}
	
	public void setMaxFloat(float i){
		maxFloat = i;
	}
	
	public float getMinFloat(){
		return minFloat;
	}
	
	public void setMinFloat(float i){
		minFloat = i;
	}

	/**
	 * @return the minInt
	 */
	public int getMinInt() {
		return MinInt;
	}

	/**
	 * @param minInt the minInt to set
	 */
	public void setMinInt(int minInt) {
		MinInt = minInt;
	}

	/**
	 * @return the maxInt
	 */
	public int getMaxInt() {
		return maxInt;
	}

	/**
	 * @param maxInt the maxInt to set
	 */
	public void setMaxInt(int maxInt) {
		this.maxInt = maxInt;
	}
}
