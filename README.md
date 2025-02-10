# E-Commerce Microservices Application

## Description
Ce projet est une application e-commerce développée avec **Spring Boot** et une architecture **microservices**. L'application permet la gestion des clients, des produits, des commandes, des paiements et des notifications. La communication entre les services est assurée par **Feign Client** et **Kafka**.

## Architecture
L'application est divisée en plusieurs microservices :

- **Customer-Service** : Gestion des clients.
- **Product-Service** : Gestion des produits et catégories.
- **Order-Service** : Gestion des commandes et des lignes de commande.
- **Payment-Service** : Gestion des paiements.
- **Notification-Service** : Envoi des notifications et des e-mails de confirmation.
- **Config-Service** : Configuration centralisée des microservices.
- **Discovery-Service** : Service de découverte avec **Eureka**.
- **Gateway-Service** : API Gateway pour la gestion des requêtes.

## Technologies utilisées
- **Spring Boot** (REST, Data JPA, Lombok, Validation, Logging)
- **Spring Cloud** (Config, Eureka, Gateway, OpenFeign)
- **Kafka** (Communication asynchrone entre services)
- **MongoDB** (Customer, Notification Service)
- **PostgreSQL** (Product, Order, Payment Services)
- **JavaMailSender** (Envoi d'e-mails de confirmation)
- **Docker** (Déploiement et gestion des services)

## Communication entre les services
- **Feign Client** est utilisé pour les appels synchrones entre microservices.
- **Kafka** est utilisé pour les messages asynchrones :
  - `order-topic` : Envoi de confirmations de commande.
  - `payment-topic` : Envoi de confirmations de paiement.
- **JavaMailSender** est utilisé dans le **Notification-Service** pour envoyer des e-mails de confirmation après un paiement ou une commande réussie.

## Fonctionnalités principales
### Customer Service
- Création et gestion des clients.
- Association d'une adresse à un client.

### Product Service
- Gestion des produits.
- Gestion des catégories de produits.

### Order Service
- Création et gestion des commandes.
- Gestion des lignes de commande.
- Communication avec **Customer-Service** et **Product-Service** via **Feign Client**.
- Publication d'un événement Kafka après création d'une commande.

### Payment Service
- Gestion des paiements avec différents moyens (PayPal, Visa, MasterCard, etc.).
- Publication d'un événement Kafka après validation du paiement.

### Notification Service
- Écoute des événements Kafka (`order-topic` et `payment-topic`).
- Envoi d'e-mails de confirmation via **JavaMailSender**.

## Installation et exécution
### Prérequis
- **Java 17**
- **Docker & Docker Compose**
- **Kafka & Zookeeper**
- **PostgreSQL & MongoDB**

### Démarrage du projet
1. Cloner le projet :
   ```sh
   git clone https://github.com/karima-fullstack/e-com-app.git
   cd e-com-app
   ```
2. Démarrer **Kafka** et **Zookeeper** :
   ```sh
   docker-compose up -d zookeeper kafka
   ```
3. Démarrer les bases de données **PostgresSQL** et **MongoDB** :
   ```sh
   docker-compose up -d postgres mongodb
   ```
4. Lancer les microservices :
   ```sh
   mvn spring-boot:run -pl config-service
   mvn spring-boot:run -pl discovery-service
   mvn spring-boot:run -pl gateway-service
   mvn spring-boot:run -pl customer-service
   mvn spring-boot:run -pl product-service
   mvn spring-boot:run -pl order-service
   mvn spring-boot:run -pl payment-service
   mvn spring-boot:run -pl notification-service
   ```

## Améliorations futures
- Implémentation d'une gestion des stocks avancée.
- Ajout d'un service d'authentification (Spring Security, Keycloak).
- Mise en place d'une interface utilisateur avec **React**.

## Auteur
Développé par **Karima** 🚀.


