package rmiServer;

import rmiService.BanqueImpl;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class BanqueServer {

    public static void main(String[] args) {
        try {
            // 1. Démarre toujours le RMIRegistry (JNDI en a besoin comme annuaire)
            LocateRegistry.createRegistry(1099);

            // 2. Crée l'objet distant
            BanqueImpl banque = new BanqueImpl();

            // 3. Ouvre le contexte JNDI (configuré par jndi.properties)
            Context ctx = new InitialContext();

            // 4. Enregistre l'objet sous le nom "banque"
            ctx.rebind("banque", banque);

            System.out.println("Serveur banque prêt (enregistrement via JNDI)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}