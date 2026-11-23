package service;

import corbaBanque.Compte;
import corbaBanque.IBanqueRemotePOA;
import java.util.HashMap;
import java.util.Map;

public class BanqueImpl extends IBanqueRemotePOA {

    private static final double TAUX_EUR_DT = 3.4;

    // Stockage des comptes en mémoire : clé = code du compte
    private final Map<Integer, Compte> comptes = new HashMap<>();

    @Override
    public synchronized void creerCompte(Compte cpte) {
        if (comptes.containsKey(cpte.code)) {
            System.out.println("Création refusée : le compte " + cpte.code + " existe déjà");
        } else {
            comptes.put(cpte.code, cpte);
            System.out.println("Compte " + cpte.code + " créé (solde " + cpte.solde + ")");
        }
    }

    @Override
    public synchronized void verser(float mt, int code) {
        Compte c = comptes.get(code);
        if (c != null && mt > 0) {
            c.solde += mt;
            System.out.println("Versement de " + mt + " sur le compte " + code);
        } else {
            System.out.println("Versement impossible sur le compte " + code);
        }
    }

    @Override
    public synchronized void retirer(float mt, int code) {
        Compte c = comptes.get(code);
        if (c != null && mt > 0 && c.solde >= mt) {
            c.solde -= mt;
            System.out.println("Retrait de " + mt + " sur le compte " + code);
        } else {
            System.out.println("Retrait impossible sur le compte " + code);
        }
    }

    @Override
    public synchronized Compte getCompte(int code) {
        Compte c = comptes.get(code);
        if (c == null) {
            return new Compte(-1, 0f);   // compte "vide" signalant l'absence
        }
        return c;
    }

    @Override
    public synchronized Compte[] getComptes() {
        return comptes.values().toArray(new Compte[0]);
    }

    @Override
    public double conversion(float mt) {
        return mt * TAUX_EUR_DT;
    }
}