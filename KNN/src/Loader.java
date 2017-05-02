import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 
 */

/**
 * @author delbrouquepri
 *
 */
public class Loader {
	
	private File file;
	private BufferedInputStream fis;
	List<Attribute> listAttribute = new ArrayList<Attribute>();
	List<Ligne> lignes = new ArrayList<Ligne>();
	
	public Loader(final String pathname) {
		init(pathname);
	}
	
	public void init(String pathname){
		 
		file = new File(pathname);
		if(!file.exists()){
			System.err.println("Le fichier n'existe pas");
			System.exit(-1);
		}
		
		if(file.isDirectory()){
			System.err.println("Le chemin passé en paramètre est un dossier");
			System.exit(-1);
		}
		
		if(!file.isFile()){
			System.err.println("Ce n'est pas un fichier");
			System.exit(-1);
		}
	}
	
	public void run(){
		
		try {
			 fis = new BufferedInputStream(new FileInputStream(this.file));
		} catch (FileNotFoundException e) {
			System.err.println("Instanciation des reader échoués");
			System.exit(-1);
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		try {
			String ligne ="";
			boolean data = false;
			int cmpTotal = 0;
			int cmpLigneData = 0;
			while((ligne = br.readLine()) != null){
				cmpTotal++;
				if((ligne.contains("@ATTRIBUTE") ||ligne.contains("@attribute")) && data == false){
					
					String tab[];
					if(ligne.contains("{")){
						tab = ligne.split(Pattern.quote("{"));
						for(int i = 0 ; i < tab.length ;i++){
							System.out.println(tab[i]);
						}
						//
						String[] tabBis = tab[0].split(" ");
						String name = tabBis[1];
						//
						
						Attribute a = new Attribute(name,List.class);
						
						String[] tabs = tab[1].split(Pattern.quote("}"));
						
						String[] finalAttrVals = tabs[0].split(",");
						for(int i = 0 ; i < finalAttrVals.length ;i++){
							a.addPossibilité(finalAttrVals[i]);
						}
						
						Scanner scan = new Scanner(System.in);
						String val ="";
						boolean rep = false;
						while(rep == false){
							System.out.println("entrez oui ou non si l'attribut "+name+" doit être considéré comme un rang ");
							val = scan.nextLine();
							if(val.equalsIgnoreCase("oui") || val.equalsIgnoreCase("non")){
								
								rep = true;
							}
						}
						if(val.equalsIgnoreCase("oui")){
							a.setRank(true);
						}
						listAttribute.add(a);
					}else{
						tab = ligne.split(" ");
						String name = tab[1];
						
						if(tab[2].equalsIgnoreCase("STRING")){
							listAttribute.add(new Attribute(name,String.class));
						}else if(tab[2].equalsIgnoreCase("NUMERIC")){
							Scanner scan = new Scanner(System.in);
							String val ="";
							boolean rep = false;
							while(rep == false){
								System.out.println("entrez oui ou non si l'attribut "+name+" doit être considéré comme un booléen ");
								val = scan.nextLine();
								if(val.equalsIgnoreCase("oui") || val.equalsIgnoreCase("non")){
									rep = true;
								}
							}
							Attribute a = new Attribute(name, Integer.class);
							if(val.equalsIgnoreCase("oui")){
								a.setBool(true);
							}
							listAttribute.add(a);
						}else if(tab[2].equalsIgnoreCase("REAL")){
							Scanner scan = new Scanner(System.in);
							String val ="";
							boolean rep = false;
							while(rep == false){
								System.out.println("entrez oui ou non si l'attribut "+name+" doit être considéré comme un booléen ");
								val = scan.nextLine();
								if(val.equalsIgnoreCase("oui") || val.equalsIgnoreCase("non")){
									rep = true;
								}
							}
							Attribute a = new Attribute(name, Integer.class);
							if(val.equalsIgnoreCase("oui")){
								a.setBool(true);
							}
							listAttribute.add(a);
						}
					}
					
				}
				if(ligne.contains("@DATA")){
					data = true;
					ligne = br.readLine();
				}
				if(data == true){
					Ligne currentLigne = new Ligne();
					String[] tab = ligne.split(",");
					for(int i = 0 ; i < tab.length;i++){	
						for(Attribute a: listAttribute){
							if( a.getValue().getName() == "java.lang.String"){
								currentLigne.getValues().add(new Valeur(new String(tab[i])));
								break;
							}else if(a.getValue().toString() == "java.util.List"){
								currentLigne.getValues().add(new Valeur(new String(tab[i])));
								break;
							}else if(a.getValue().getName() == "java.lang.Float"){
								currentLigne.getValues().add(new Valeur(Float.parseFloat(tab[i])));
								break;
							}
						}
					}
					cmpLigneData++;
					lignes.add(currentLigne);
					//System.err.println(cmpLigneData+" [LIGNE]: "+ligne);
				}
		    }
			// affiche tous les attributs
			for(Attribute t : listAttribute){
				System.out.println(t.getName()+" "+t.getValue()+" "+t.getPossibility().toString());
			}
			// affiche toutes les lignes ! 
			Point p1 = new Point("newA");
			Point p2 = new Point("newB");
			/*for(Ligne l : lignes){
				 //System.out.println("[LIGNE]: "+l.getValues().toString());
			}*/
			for(Valeur v: lignes.get(0).getValues()){
				for(Attribute a : listAttribute){
					p1.addIntoHash(a, v);
				}
			}
			
			for(Attribute a : listAttribute){
				System.out.println(a.getName()+" "+p1.getHash().get(a));
			}
		
			for(Valeur v: lignes.get(1).getValues()){
				System.out.println(v.toString());
				for(Attribute a : listAttribute){
					p1.addIntoHash(a, v);
				}
			}
			for(Attribute a : listAttribute){
				System.out.println(a.getName()+" "+((Valeur)p2.getHash().get(a)).toString());
			}
			//Distance d = new Distance(p1, p2, listAttribute);
			//System.out.println("distance: "+d.getDistance());
		} catch (IOException e) {
			System.err.println("Tentative de lecture échouée");
			System.exit(-1);
		}finally{
			try {
				fis.close();
				isr.close();
				br.close();
			} catch (IOException e) {
				System.err.println("Tentative de fermeture du fichier échouée");
				System.exit(-1);
			}
		}
		
	}
	
	

}
