import java.util.InputMismatchException;
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
		/*
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
		
		System.out.println(" entrez un nouveau point:");
		boolean finish = false;
		while(!finish){
			double x = 0.0;
			double y = 0.0;
			for (;;) {
				System.out.println("donnée sa coordonnée x:");
				if (!scan.hasNextDouble()) {
		            System.out.println(" Entrez uniquement un double: ");
		            scan.next(); // discard
		            continue;
		        }
		        x = scan.nextDouble();
		        if ( x > 0.0) {

		        } else {

		        }
		        break;
			}
			for (;;) {
				System.out.println("donnée sa coordonnée x:");
				if (!scan.hasNextDouble()) {
		            System.out.println(" Entrez uniquement un double: ");
		            scan.next(); // discard
		            continue;
		        }
		        y = scan.nextDouble();
		        if ( y > 0.0) {

		        } else {

		        }
		        break;
			}
			System.out.println("si vous voulez poursuivre sans créer de nouveau point entrez 'terminer'");
			String t = scan.next();
			if(t.equalsIgnoreCase("terminer")){
				finish = true;
			}
		}
		*/
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
