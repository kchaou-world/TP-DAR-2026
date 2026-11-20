package rmiServer;

import rmiService.ConversionImpl;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ConversionServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);                      // i. annuaire
            ConversionImpl service = new ConversionImpl();            // ii. instance
            Naming.rebind("rmi://localhost:1099/conversion", service);// iii. publication
            System.out.println("Serveur RMI prêt...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}