package rmiService;

import metier.Compte;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {

    private static final long serialVersionUID = 1L;

    // Stockage de tous les comptes : clé = code, valeur = Compte
    private final Map<Integer, Compte> comptes = new HashMap<>();

    public BanqueImpl() throws RemoteException {
        super();   // exporte l'objet pour qu'il puisse recevoir des appels distants
    }

    @Override
    public synchronized String creerCompte(Compte c) throws RemoteException {
        if (comptes.containsKey(c.getCode())) {
            return "Erreur : le compte " + c.getCode() + " existe déjà";
        }
        comptes.put(c.getCode(), c);
        return "Compte " + c.getCode() + " créé avec succès";
    }

    @Override
    public synchronized String getInfoCompte(int code) throws RemoteException {
        Compte c = comptes.get(code);
        if (c == null) {
            return "Compte " + code + " introuvable";
        }
        return c.toString();
    }
}