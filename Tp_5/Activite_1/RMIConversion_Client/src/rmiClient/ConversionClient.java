package rmiClient;

import rmiService.IConversion;
import java.rmi.Naming;

public class ConversionClient {
    public static void main(String[] args) {
        try {
            IConversion stub = (IConversion) Naming.lookup("rmi://localhost:1099/conversion");
            System.out.println("500 EUR = " + stub.convertirMontant(500) + " DT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}