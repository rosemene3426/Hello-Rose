import java.util.Scanner;

public class Jeu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le Jeu de Combat !");

        System.out.print("Joueur 1, entre ton nom : ");
        String nomJ1 = scanner.nextLine();

        System.out.print("Joueur 2, entre ton nom : ");
        String nomJ2 = scanner.nextLine();

        Joueur joueur1 = new Joueur(nomJ1, creerEquipe(scanner, nomJ1));
        Joueur joueur2 = new Joueur(nomJ2, creerEquipe(scanner, nomJ2));

        int tour = 1;
        while (joueur1.aEncoreDesPersonnagesVivants() &&
                joueur2.aEncoreDesPersonnagesVivants()) {

            System.out.println("\n--- TOUR " + tour + " ---");

            tourDeJeu(scanner, joueur1, joueur2);

            if (!joueur2.aEncoreDesPersonnagesVivants()) {
                break;
            }

            tourDeJeu(scanner, joueur2, joueur1);

            tour++;
        }

        if (joueur1.aEncoreDesPersonnagesVivants()) {
            System.out.println("Victoire de " + joueur1.getNom() + " ! Tous les personnages de " + joueur2.getNom() + " sont éliminés.");
        } else {
            System.out.println("Victoire de " + joueur2.getNom() + " ! Tous les personnages de " + joueur1.getNom() + " sont éliminés.");
        }

        scanner.close();
    }

    private static Personnage[] creerEquipe(Scanner scanner, String nomJoueur) {
        Personnage[] equipe = new Personnage[3];
        System.out.println("\n" + nomJoueur + ", crée ton équipe :");

        for (int i = 0; i < 3; i++) {
            System.out.print("Nom du personnage " + (i + 1) + " : ");
            String nomPerso = scanner.nextLine();
            equipe[i] = new Personnage(nomPerso, 100, 20);
        }
        return equipe;
    }

    private static void tourDeJeu(Scanner scanner, Joueur joueurActif, Joueur joueurCible) {
        System.out.println("\nTour de " + joueurActif.getNom());

        joueurActif.afficherEquipe();
        joueurCible.afficherEquipe();

        Personnage attaquant = null;
        while (attaquant == null) {
            System.out.print(joueurActif.getNom() + ", choisis le numéro de ton personnage attaquant : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            attaquant = joueurActif.choisirPersonnageVivant(choix);
            if (attaquant == null) {
                System.out.println("Choix invalide ou personnage mort. Réessaie.");
            }
        }

        Personnage cible = null;
        while (cible == null) {
            System.out.print(joueurActif.getNom() + ", choisis le numéro du personnage à attaquer chez " + joueurCible.getNom() + " : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            cible = joueurCible.choisirPersonnageVivant(choix);
            if (cible == null) {
                System.out.println("Choix invalide ou personnage mort. Réessaie.");
            }
        }

        attaquant.attaquer(cible);

        System.out.println("\nÉtat de l'équipe de " + joueurCible.getNom() + " :");
        joueurCible.afficherEquipe();
    }
}
