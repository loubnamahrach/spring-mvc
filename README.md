# BDCC ENSET Spring MVC - Application de Gestion de Produits

Une application web de gestion de produits construite avec **Spring Boot 3.4.5** et **Spring MVC**, utilisant **Thymeleaf** pour le rendu des templates et **Spring Security** pour l'authentification et l'autorisation.

## 🎯 Vue d'ensemble

Cette application permet de gérer un catalogue de produits avec un système de rôles (USER/ADMIN). Les utilisateurs peuvent consulter la liste des produits, tandis que les administrateurs peuvent ajouter et supprimer des produits.

## ✨ Fonctionnalités principales

- **Authentification & Autorisation** : Système de sécurité basé sur les rôles (USER, ADMIN)
- **Gestion des Produits** :
  - 👁️ Consultation de la liste des produits (accès USER)
  - ➕ Création de nouveaux produits (accès ADMIN)
  - 🗑️ Suppression de produits (accès ADMIN)
- **Validation** : Validation des données côté serveur avec Jakarta Validation
- **Interface Web** : Interface utilisateur responsive avec Thymeleaf

## 🛠️ Technologies utilisées

- **Framework** : Spring Boot 3.4.5
- **Java** : JDK 21
- **Persistence** : Spring Data JPA / Hibernate
- **Sécurité** : Spring Security
- **Templating** : Thymeleaf
- **Validation** : Jakarta Validation
- **Build** : Maven
- **Database** : MySQL (configurable)

## 📋 Prérequis

- JDK 21 ou supérieur
- Maven 3.6+
- MySQL Server (ou autre base de données compatible)

## 🚀 Installation et démarrage

### 1. Cloner ou télécharger le projet

```bash
git clone <repository-url>
cd bdcc-enset-spring-mvc
```

### 2. Configurer la base de données

Modifier le fichier `src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/votre_base_de_donnees
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe
spring.jpa.hibernate.ddl-auto=update
```

### 3. Compiler et lancer l'application

```bash
# Avec Maven
./mvnw spring-boot:run

# Ou compiler et empaqueter
./mvnw clean package
java -jar target/bdcc-enset-spring-mvc-0.0.1-SNAPSHOT.jar
```

L'application sera disponible à : `http://localhost:8080`

## 📁 Structure du projet

```
src/
├── main/
│   ├── java/com/jee/bdccensetspringmvc/
│   │   ├── BdccEnsetSpringMvcApplication.java    # Classe principale
│   │   ├── entities/
│   │   │   └── Product.java                      # Entité Product
│   │   ├── repository/
│   │   │   └── ProductRepository.java            # Interface CRUD
│   │   ├── web/
│   │   │   └── ProductController.java            # Contrôleur MVC
│   │   └── sec/
│   │       └── SecurityConfig.java               # Configuration sécurité
│   └── resources/
│       ├── application.properties                # Configuration app
│       └── templates/
│           ├── layout1.html                      # Layout principal
│           ├── login.html                        # Page de connexion
│           ├── products.html                     # Liste des produits
│           ├── new-product.html                  # Formulaire ajout produit
│           └── notAuthorized.html                # Page accès refusé
└── test/
    └── java/...                                  # Tests unitaires
```

## 🔐 Authentification

L'application utilise Spring Security avec un système de rôles :

- **ROLE_USER** : Peut consulter la liste des produits
- **ROLE_ADMIN** : Peut créer et supprimer des produits

### Points de terminaison sécurisés

| URL | Méthode | Rôle requis | Description |
|-----|---------|-----------|-------------|
| `/user/index` | GET | USER | Affiche la liste des produits |
| `/admin/newProduct` | GET | ADMIN | Affiche le formulaire de création |
| `/admin/saveProduct` | POST | ADMIN | Enregistre un nouveau produit |
| `/admin/delete` | POST | ADMIN | Supprime un produit |
| `/login` | GET | Publique | Page de connexion |

## 📝 Modèle de données

### Product
```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;
    
    @Min(0)
    private double price;
    
    @Min(1)
    private long quantity;
}
```


## 📦 Dépendances principales

- `spring-boot-starter-data-jpa` - ORM et accès aux données
- `spring-boot-starter-security` - Authentification et autorisation
- `spring-boot-starter-thymeleaf` - Moteur de templates
- `spring-boot-starter-validation` - Validation des données
- `spring-boot-starter-web` - Support web et MVC
- `mysql-connector-java` - Driver MySQL
- `lombok` - Réduction du code boilerplate

