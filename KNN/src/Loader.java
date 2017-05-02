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
	private List<Attribute> listAttribute = new ArrayList<Attribute>();
	private List<Point> points = new ArrayList<Point>();
	
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
							Attribute a = new Attribute(name, Float.class);
							listAttribute.add(a);
						}
					}
					
				}
				if(ligne.contains("@DATA") || ligne.contains("@data") || ligne.contains("@Data")){
					data = true;
					ligne = br.readLine();
				}
				
				if(data == true){
					if(!ligne.contains(",?,") || !ligne.contains("?,") ){
							
						Ligne currentLigne = new Ligne();
						String[] tab = ligne.split(",");
						for(int i = 0 ; i < tab.length;i++){
								if( listAttribute.get(i).getValue().getName() == "java.lang.String"){
									
									currentLigne.getValues().add(new Valeur(new String(tab[i])));
									
								}else if(listAttribute.get(i).getValue().getName() == "java.util.List"){
									
									currentLigne.getValues().add(new Valeur(new String(tab[i])));
									
								}else if(listAttribute.get(i).getValue().getName() == "java.lang.Float"){
									System.out.println(""+tab[i]);
									currentLigne.getValues().add(new Valeur(Float.parseFloat(tab[i])));
									
									if(Float.parseFloat(tab[i]) > listAttribute.get(i).getMaxFloat()){
										
										System.out.println(Float.parseFloat(tab[i])+">"+listAttribute.get(i).getMaxFloat());
										listAttribute.get(i).setMaxFloat(Float.parseFloat(tab[i]));
										
									}
									if(Float.parseFloat(tab[i]) < listAttribute.get(i).getMinFloat()){
										
										System.out.println(Float.parseFloat(tab[i])+"<"+listAttribute.get(i).getMaxFloat());
										listAttribute.get(i).setMinFloat(Float.parseFloat(tab[i]));
										
									}
								}else if (listAttribute.get(i).getValue().getName() == "java.lang.Integer"){
									currentLigne.getValues().add(new Valeur(Integer.parseInt(tab[i])));
									
									if(Integer.parseInt(tab[i]) > listAttribute.get(i).getMaxInt()){
										
										System.out.println(Integer.parseInt(tab[i])+">"+listAttribute.get(i).getMaxInt());
										listAttribute.get(i).setMaxInt(Integer.parseInt(tab[i]));
										
									}
									if(Integer.parseInt(tab[i]) < listAttribute.get(i).getMinInt()){
										
										System.out.println(Integer.parseInt(tab[i])+"<"+listAttribute.get(i).getMaxInt());
										listAttribute.get(i).setMinInt(Integer.parseInt(tab[i]));
										
									}
								}
								//System.out.println("current ligne "+currentLigne.getValues()+" ");		
						}
						cmpLigneData++;
						int i = 0;
						Point p = new Point("data"+cmpLigneData);
						for(Valeur v: currentLigne.getValues()){
							System.out.println(v.toString());
								p.addIntoHash(listAttribute.get(i), v);
								//System.out.println(a.getName()+" "+v.toString());
							i++;
						}
						points.add(p);
						//System.err.println(cmpLigneData+" [LIGNE]: "+p.getHash());
					}
				}
				
		    }
			// affiche tous les attributs
			/*for(Attribute t : listAttribute){
				System.out.println(t.getName()+" "+t.getValue()+" "+t.getPossibility().toString());
			}
			// affiche toutes les lignes ! 
			for(Ligne l : lignes){
				 System.out.println("[LIGNE]: "+l.getValues().toString());
			}*/
			/*
			Point p1 = new Point("newA");
			Point p2 = new Point("newB");
			int i = 0;
			for(Valeur v: lignes.get(0).getValues()){
				System.out.println(v.toString());
					p1.addIntoHash(listAttribute.get(i), v);
					//System.out.println(a.getName()+" "+v.toString());
				i++;
			}
			i = 0;
			for(Valeur v: lignes.get(1).getValues()){
					p2.addIntoHash(listAttribute.get(i), v);
					i++;
					//System.out.println(a.getName()+" "+v.toString());
			}
			System.out.println("p1");
			for(Attribute a : listAttribute){
				System.out.println(" key: "+a.getName()+" value: "+p1.getHash().get(a).toString());
			}
			System.out.println("p2");
			for(Attribute a : listAttribute){
				System.out.println(" key: "+a.getName()+" value: "+p2.getHash().get(a).toString());
			}
			Distance d = new Distance(p1, p2, listAttribute);
			System.out.println("distance: "+d.getDistance());
			*/
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
	
	public List<Point> getPoints(){
		return points;
	}

	/**
	 * @return the listAttribute
	 */
	public synchronized List<Attribute> getListAttribute() {
		return listAttribute;
	}

	/**
	 * @param listAttribute the listAttribute to set
	 */
	public synchronized void setListAttribute(List<Attribute> listAttribute) {
		this.listAttribute = listAttribute;
	}

}
