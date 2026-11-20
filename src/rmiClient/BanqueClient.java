package rmiClient;

import metier.Compte;
import rmiService.IBanque;
import java.rmi.Naming;

public class BanqueClient {

    public static void main(String[] args) {
        try {
            // 1. Cherche la référence distante dans l'annuaire RMI
            IBanque banque = (IBanque) Naming.lookup("rmi://localhost:1099/banque");

            // 2. Crée un compte (code 1, solde initial 1000)
            Compte c1 = new Compte(1, 1000);
            System.out.println(banque.creerCompte(c1));

            // 3. Tente de recréer le même code : le serveur doit refuser
            Compte doublon = new Compte(1, 500);
            System.out.println(banque.creerCompte(doublon));

            // 4. Crée un deuxième compte
            System.out.println(banque.creerCompte(new Compte(2, 2500)));

            // 5. Consulte un compte existant
            System.out.println(banque.getInfoCompte(1));

            // 6. Consulte un compte inexistant
            System.out.println(banque.getInfoCompte(99));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}