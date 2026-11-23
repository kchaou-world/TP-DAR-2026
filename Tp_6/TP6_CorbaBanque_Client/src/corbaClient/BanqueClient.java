package corbaClient;

import corbaBanque.Compte;
import corbaBanque.IBanqueRemote;
import corbaBanque.IBanqueRemoteHelper;
import javax.naming.Context;
import javax.naming.InitialContext;

public class BanqueClient {

    public static void main(String[] args) {
        try {
            // a. Récupérer les propriétés JNDI (jndi.properties) et ouvrir le contexte
            Context ctx = new InitialContext();

            // b. Récupérer la référence de l'objet distant enregistré sous "BK"
            org.omg.CORBA.Object ref = (org.omg.CORBA.Object) ctx.lookup("BK");

            // c. Convertir la référence en IBanqueRemote : création du Stub
            IBanqueRemote stub = IBanqueRemoteHelper.narrow(ref);

            // d. Tester chaque méthode distante
            System.out.println("--- creerCompte ---");
            stub.creerCompte(new Compte(1, 1000f));
            stub.creerCompte(new Compte(2, 500f));
            stub.creerCompte(new Compte(1, 300f));   // doublon, refusé côté serveur

            System.out.println("--- verser 200 sur le compte 1 ---");
            stub.verser(200f, 1);

            System.out.println("--- retirer 100 du compte 2 ---");
            stub.retirer(100f, 2);

            System.out.println("--- getCompte(1) ---");
            Compte c = stub.getCompte(1);
            System.out.println("Compte " + c.code + " : solde = " + c.solde);

            System.out.println("--- getComptes ---");
            for (Compte x : stub.getComptes()) {
                System.out.println("Compte " + x.code + " : solde = " + x.solde);
            }

            System.out.println("--- conversion ---");
            System.out.println("500 EUR = " + stub.conversion(500f) + " DT");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}