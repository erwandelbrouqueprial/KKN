import java.util.ArrayList;
import java.util.List;

public class Ligne {
	private int pos;
	private List<Valeur> values = new ArrayList<Valeur>();
	
	
	public Ligne() {
	}
	
	public List<Valeur> getValues() {
		return values;
	}
	public void setValues(List<Valeur> values) {
		this.values = values;
	}
	
	public String toString(){
		String printer ="";
		for(Valeur v : values){
			printer +=" "+v.toString();
		}
		return printer;
	}
	
}
