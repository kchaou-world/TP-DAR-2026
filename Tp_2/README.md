# TP2 — Échanges TCP structurés

**Mohamed Kchaou** · LSI 3 · Développement d’applications réparties

Le TP2 reprend la calculatrice du TP1 et fait évoluer la représentation des requêtes : d’abord une commande textuelle, puis un objet Java sérialisé.

## Activités

- [Activité 2-1 — Requête sous forme de chaîne](./Activite_1/README.md) : le client transmet une expression telle que `55 * 25` et le serveur renvoie le résultat.
- [Activité 2-2 — Requête sous forme d’objet](./Activite_2/README.md) : le client sérialise une instance de `Operation` et le serveur renvoie le résultat calculé.

## Progression

La première activité sépare les valeurs à partir d’une ligne de texte. La seconde définit un format de données explicite avec une classe sérialisable, afin de transmettre les deux opérandes et l’opérateur comme un seul objet.
