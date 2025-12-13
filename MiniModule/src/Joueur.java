public class Joueur {
    private String nom;
    private Personnage[] equipe;

    public Joueur(String nom, Personnage[] equipe) {
        this.nom = nom;
        this.equipe = equipe;
    }

    public String getNom() {
        return nom;
    }

    public void afficherEquipe() {
        System.out.println("Équipe de " + nom + " :");
        for (int i = 0; i < equipe.length; i++) {
            System.out.print((i + 1) + " - ");
            equipe[i].afficherEtat();
        }
    }

    public boolean aEncoreDesPersonnagesVivants() {
        for (Personnage p : equipe) {
            if (p.estVivant()) {
                return true;
            }
        }
        return false;
    }

    public Personnage choisirPersonnageVivant(int index) {
        if (index < 1 || index > equipe.length) {
            return null;
        }
        Personnage p = equipe[index - 1];
        if (p.estVivant()) {
            return p;
        }
        return null;
    }
}
