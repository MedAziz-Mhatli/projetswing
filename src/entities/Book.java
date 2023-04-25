package entities;

public class Book {
	private int id;
	private String auteur; 

	private String ISBN;
	private String titre;
	
	
	
	

	public Book(String auteur,String ISBN,String titre) { 
		
		this.auteur=auteur;
		this.ISBN=ISBN;
		this.titre=titre;
		
				
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", auteur=" + auteur + ", ISBN=" + ISBN + ", titre=" + titre + "]";
	}
	
	
	
}
