package corbaServer;

import service.BanqueImpl;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class BanqueServer {

    public static void main(String[] args) {
        try {
            // a. Initialiser l'ORB
            ORB orb = ORB.init(args, null);

            // b. Récupérer le RootPOA et activer son gestionnaire
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // c. Configurer l'annuaire JNDI (+ jndi.properties lu automatiquement)
            Hashtable<String, Object> env = new Hashtable<>();
            env.put("java.naming.corba.orb", orb);
            Context ctx = new InitialContext(env);

            // d. Créer le servant
            BanqueImpl servant = new BanqueImpl();

            // e. Obtenir sa référence CORBA et l'enregistrer dans l'annuaire
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(servant);
            ctx.rebind("BK", ref);

            System.out.println("Serveur CORBA prêt, en attente des clients...");

            // f. Mettre l'ORB en attente des requêtes
            orb.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}