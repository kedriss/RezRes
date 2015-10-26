# RezRes

Projet de réservation de ressources.

FILA2 - Cédric LINQUE, Ludovic SINQUIN, Maël NACCACHE

## Deploiement

Le projet nécessite :
  - Un serveur SQL h2 : http://www.h2database.com/html/main.html
  - JRE >= 1.7
  - Tomcat >= 7

### Base de donnée :
Un modéle de base de donnée fonctionnel ce trouve à la racine du projet :
  RezRes/database/RezRes.h2.db

Il faut placer ce fichier dans le répertoire utilisateur (/home/<user> pour Linux, C:\Users\<user> pour Windows)
ou modifier le chemin vers le fichier dans le persistance.xml à la ligne 9 :

  <property name="hibernate.connection.url"           value="jdbc:h2:tcp://localhost/~/RezRes"/>

Une fois le fichier déployé, il suffit de lancer un serveur H2 pour que le projet
puisse utilisé la base de donnée.

### Création du .WAR
Pour créer une archive WAR utilisable avec Tomcat, il suffit d'éxécuter la
commande :

  mvn build package

A la racine du projet.
Un fichier .WAR devrais avoir été créé dans RezRes/target.

### Déploiement dans Tomcat
Une fois le .WAR correctement créé, il suffit de le copier dans
le dossier webapps de vôtre Tomcat sous le nom de "RezRes.war".

Lancer ensuite Tomcat (avec la commande bin/startup.bat a la racine de vôtre
installation de Tomcat sous Windows) qui devrais reconnaitre le WAR et afficher :

  INFO: Déploiement de l'archive RezRes.war de l'application webapps

Le WAR est alors bien deployé.

## Utilisation
Par défaut, l'application devrais être joignable à l'adresse : http://127.0.0.1/RezRes/home
Un login admin est disponible dans la base par défaut avec les identifiants suivants :
  - Login : admin
  - Mot de passe : password

Vous pourrez ensuite créer des utilisateurs, des types, des ressources et faire des
reservations.
