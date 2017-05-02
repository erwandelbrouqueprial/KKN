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
	private Float minFloat;
	private Float maxFloat;
	private Integer maxInt;
	private Integer minInt;
	
	public Attribute(final String name, final Class<?> value) {
		this.name = name;
		this.type = value;
		this.values = new ArrayList<String>();
		this.rank = false;
		this.isBool = false;
		this.minFloat = new Float("99999999");
		this.maxFloat = new Float(0);
		this.minInt = new Integer("99999999");
		this.maxInt = new Integer(0);
	}
	
	public Attribute(final String name, final Class<?> value,final ArrayList<String>values,boolean rank) {
		this.name = name;
		this.type = value;
		this.values = values;
		this.rank = rank;
		this.isBool = false;
		this.minFloat = new Float("99999999");
		this.maxFloat = new Float(0);
		this.minInt = new Integer("99999999");
		this.maxInt = new Integer(0);
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
	
	public int getLastIndex(){
		return values.size();
	}
	
	public String getFirstIndex(){
		return values.get(0);
	}
	
	public Float getMaxFloat(){
		return maxFloat;
	}
	
	public void setMaxFloat(Float i){
		this.maxFloat = i;
	}
	
	public Float getMinFloat(){
		return minFloat;
	}
	
	public void setMinFloat(Float i){
		this.minFloat = i;
	}

	/**
	 * @return the minInt
	 */
	public Integer getMinInt() {
		return minInt;
	}

	/**
	 * @param minInt the minInt to set
	 */
	public void setMinInt(Integer minInt) {
		this.minInt = minInt;
	}

	/**
	 * @return the maxInt
	 */
	public Integer getMaxInt() {
		return maxInt;
	}

	/**
	 * @param maxInt the maxInt to set
	 */
	public void setMaxInt(Integer maxInt) {
		this.maxInt = maxInt;
	}
}
