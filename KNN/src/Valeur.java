
public class Valeur {
	
	private Object valeur;
	
	public Valeur(final Object valeur) {
		
		this.valeur = valeur;
	}

	public Object getValeur() {
		return valeur;
	}

	public void setValeur(Object valeur) {
		this.valeur = valeur;
	}

	public String toString(){
		return valeur.toString();
	}
}
