package rmiClient;

import metier.Compte;
import rmiService.IBanque;
import javax.naming.Context;
import javax.naming.InitialContext;

public class BanqueClient {

    public static void main(String[] args) {
        try {
            // 1. Ouvre le contexte JNDI (lit jndi.properties du client)
            Context ctx = new InitialContext();

            // 2. Recherche l'objet distant par son nom
            IBanque banque = (IBanque) ctx.lookup("banque");

            // 3. Mêmes tests que la version 1
            System.out.println(banque.creerCompte(new Compte(1, 1000)));
            System.out.println(banque.creerCompte(new Compte(1, 500)));
            System.out.println(banque.creerCompte(new Compte(2, 2500)));
            System.out.println(banque.getInfoCompte(1));
            System.out.println(banque.getInfoCompte(99));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}