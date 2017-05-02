import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author delbrouquepri
 *
 */
public class Main {

	private static boolean bool = false;
	private static int tolerance = 0;
	private static Scanner scan;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Loader load = new Loader(args[0]); 
		load.run();
		bool = false;
		scan = new Scanner(System.in);
		tolerance =0;
		System.out.println("Entrez la tolérance(nombre de voisin plus proche entre les nouvaux points) :");
		for (;;) {
			if (!scan.hasNextInt()) {
				System.out.println(" Entrez uniquement un entier: ");
				scan.next(); // discard
				continue;
			}
			tolerance = scan.nextInt();
			if (tolerance >= 0) {

			} else {

			}
			break;
		}
		List<Point> l = new ArrayList<Point>();
		if(Loader.getAttributeclasse().getName() != "toto"){
			boolean finish = false;
			int cmp = 0;
			while(!finish){
				cmp++;
				List<Valeur> va = new ArrayList<Valeur>();
				for(Attribute a : load.getListAttribute()){
					if(!a.getName().equalsIgnoreCase(load.getAttributeclasse().getName())){
						System.out.println("donnée la valeur pour l'attribut: "+a.getName());
						if( a.getValue().getName() == "java.lang.String"){
							System.out.println("type: string");
							String s = scan.next();
							Valeur v = new Valeur(s);
							va.add(v);
						}else if(a.getValue().getName() == "java.util.List"){
							System.out.println("type: list");
							String f = "";
							boolean equal = false;
							while(!equal){
								System.out.println("choississez parmis une de ses possibilité: "+a.getPossibility());
								f = scan.nextLine();
								try{
									for(int i = 0 ; i < a.getPossibility().size();i++){
										if(a.getPossibility().get(i).equalsIgnoreCase(f)){
	
											equal = true;
										}
									}
									System.out.println(f);
									if(!equal){
										System.out.println("cela ne fait pas partie du choix");
										equal = false;
									}
								}catch(NumberFormatException e){
									System.out.println("veuillez entrer un float");
									equal = false;
								}
							}
							Valeur v = new Valeur(f);
							va.add(v);
						}else if(a.getValue().getName() == "java.lang.Float"){
							System.out.println("type: float");
							Float val = new Float(0);
							boolean equal = false;
							while(!equal){
								String f = scan.next();
								try{
									val = Float.parseFloat(f);
									equal = true;
								}catch(NumberFormatException e){
									System.out.println("veuillez entrer un float");
									equal = false;
								}
							}
							Valeur v = new Valeur(val);
							va.add(v);
						}else if(a.getValue().getName() == "java.lang.Integer"){
							System.out.println("type: integer");
							Integer val = 0;
							boolean equal = false;
							while(!equal){
								String f = scan.next();
								try{
									val = Integer.parseInt(f);
									equal = true;
								}catch(NumberFormatException e){
									System.out.println("veuillez entrer un entier");
									equal = false;
								}
							}
	
							Valeur v = new Valeur(val);
							va.add(v);
						}
					}else{
						
					}
				}
				Point p = new Point("new"+cmp);
				int i = 0;
				System.out.println(va);
				for(Valeur v: va){
					boolean place = false;
					while(place == false){
						System.out.println(v.toString());
						if(load.getListAttribute().get(i).getName().equalsIgnoreCase(Loader.getAttributeclasse().getName())){
							System.out.println("classe");
							p.addIntoHash(load.getListAttribute().get(i), null);
						}else{
							p.addIntoHash(load.getListAttribute().get(i), v);
							//System.out.println(a.getName()+" "+v.toString());
							place = true;
						}
						i++;
					}
				}
				l.add(p);
				System.out.println("si vous voulez poursuivre sans créer de nouveau point entrez 'terminer'");
				String t = scan.next();
				if(t.equalsIgnoreCase("terminer")){
					finish = true;
				}
				
			}
			Knn knn = new Knn(load.getPoints(), tolerance,l,load.getListAttribute());
			knn.runClasse();
		}else{
			System.out.println(" entrez un nombre de cluster:");
			int nbClust = 0;
			for (;;) {
				if (!scan.hasNextInt()) {
					System.out.println(" Entrez uniquement un entier: ");
					scan.next(); // discard
					continue;
				}
				nbClust = scan.nextInt();
				break;
			}
			Knn knn = new Knn(load.getPoints(), tolerance,load.getListAttribute(),nbClust);
			knn.run();
		}
	}
	/**
	 * @return the bool
	 */
	public static boolean isBool() {
		return bool;
	}
	/**
	 * @param bool the bool to set
	 */
	public static void setBool(boolean bool) {
		Main.bool = bool;
	}


}
