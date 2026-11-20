package rmiServer;

import rmiService.BanqueImpl;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BanqueServer {

    public static void main(String[] args) {
        try {
            // 1. Démarre l'annuaire RMI (RMIRegistry) sur le port 1099
            LocateRegistry.createRegistry(1099);

            // 2. Crée l'objet distant (le service banque)
            BanqueImpl banque = new BanqueImpl();

            // 3. Publie sa référence dans l'annuaire sous le nom "banque"
            Naming.rebind("rmi://localhost:1099/banque", banque);

            System.out.println("Serveur RMI banque prêt sur le port 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}