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
	private Class type; 
	private List<String> values;
	private boolean rank;
	private boolean isBool;
	
	public Attribute(final String name, final Class value) {
		this.name = name;
		this.type = value;
		this.values = new ArrayList<String>();
		this.rank = false;
		this.isBool = false;
	}
	
	public Attribute(final String name, final Class value,final ArrayList<String>values,boolean rank) {
		this.name = name;
		this.type = value;
		this.values = values;
		this.rank = rank;
		this.isBool = false;
	}

	public Class getValue() {
		return type;
	}

	public void setValue(Class value) {
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
}
