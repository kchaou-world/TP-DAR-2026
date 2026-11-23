# TP3 — Serveurs TCP multiclients

**Mohamed Kchaou** · LSI 3 · Développement d’applications réparties

Le TP3 explore le traitement simultané de plusieurs connexions TCP avec un thread dédié par client, puis applique cette architecture à une calculatrice distribuée.

## Activités

- [Activité 3-1 — Serveur multithread](./Activite_1/README.md) : le serveur attribue un numéro à chaque connexion et traite les clients dans des threads distincts.
- [Activité 3-2 — Calculatrice multithread](./Activite_2/README.md) : chaque client envoie un objet `Operation`, traité par un thread serveur, avec un compteur partagé synchronisé.

## Progression

Le serveur accepte les connexions dans une boucle. À chaque connexion, il crée un `ClientProcess`, ce qui permet de continuer à accepter d’autres clients pendant le traitement des précédents.
