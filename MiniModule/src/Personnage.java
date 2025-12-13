public class Personnage {
    private String nom;
    private int vie;
    private int attaque;

    public Personnage(String nom, int vie, int attaque) {
        this.nom = nom;
        this.vie = vie;
        this.attaque = attaque;
    }

    public boolean estVivant() {
        return vie > 0;
    }

    public void attaquer(Personnage cible) {
        if (!this.estVivant()) {
            System.out.println(nom + " est mort et ne peut plus attaquer.");
            return;
        }
        if (!cible.estVivant()) {
            System.out.println(cible.nom + " est déjà mort.");
            return;
        }

        cible.vie -= this.attaque;
        if (cible.vie < 0) {
            cible.vie = 0;
        }

        System.out.println(nom + " attaque " + cible.nom + " et lui enlève " + attaque + " points de vie.");
        System.out.println("Il reste " + cible.vie + " HP à " + cible.nom + ".");
    }

    public void afficherEtat() {
        System.out.println(nom + " - " + vie + " HP");
    }

    public String getNom() {
        return nom;
    }
}
