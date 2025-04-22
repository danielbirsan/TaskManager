# Task Manager API – Proiect Backend (Spring Boot + MySQL)

 Acesta este primul meu proiect realizat folosind **Spring Boot + MySQL**. A fost o experiență interesantă, mai ales că am descoperit cât de familiar mi s-a părut framework-ul față de ceea ce am folosit anterior în **C# și .NET (MVC, entity framework)**. Structura în straturi (controller → service → repository → model) este foarte asemănătoare.

---

##  Tehnologii utilizate

-  **Java 17**
-  **Spring Boot 3**
-  **MySQL** (sau MariaDB)
-  **Maven**
-  **Lombok**
-  **JPA / Hibernate**
-  **REST API (JSON)**
-  **Postman** (pentru testare endpointuri)

---

## Structura aplicației

Aplicația este împărțită în:

- `model` – entitățile JPA (`Task`, `Comment`)
- `repository` – interfețe pentru accesul la date
- `service` – logica de business (validări, reguli)
- `controller` – expune endpointurile REST

---

## Baza de date

Baza de date este relațională și conține două tabele principale:

1. `Task` – tabelul principal pentru taskuri, fiecare task poate avea un câmp `parent_id` care face referință la alt task, ceea ce permite structură ierarhică (subtask-uri).

2. `Comment` – comentarii asociate unui task, prin `task_id`.

Important: dacă un task este un subtask (`parent_id != null`), atunci aplicația setează automat `responsible = null`.


### Relație 1: Task ↔ Subtasks (One-To-Many)
Fiecare task poate avea 0 sau mai multe subtaskuri.

Un subtask este de fapt un Task care are parent_id setat către alt Task.id.

### Relație 2: Task ↔ Comments (One-To-Many)
Fiecare task poate avea 0 sau mai multe comentarii.

Fiecare comentariu aparține exact unui singur task.

Mai multe detalii în PDF-ul încărcat!
Am presupus că, pentru moment, nu este necesară integrarea unui sistem de utilizatori (User) în această etapă a aplicației. Task-urile sunt gestionate fără autentificare sau asociere explicită la un user dintr-o tabelă separată.
Totuși, arhitectura aplicației este gândită în așa fel încât, dacă se dorește adăugarea ulterioară a unui model User, fără a afecta semnificativ codul existent, respectând principiile SOLID, în special principiul Open/Closed (OCP).
---


## Testare cu Postman 

| #  | Nume cerere         | Endpoint                            | Descriere                                     |
|----|---------------------|--------------------------------------|-----------------------------------------------|
| 1  | Create Task         | `POST /tasks`                        | Creează un task nou                           |
| 2  | Add Subtask         | `POST /tasks/{id}/subtasks`          | Adaugă un subtask pentru un task existent     |
| 3  | Update Task         | `PUT /tasks/{id}`                    | Actualizează datele unui task                 |
| 4  | Add Comment         | `POST /tasks/{id}/comments`          | Adaugă un comentariu la un task               |
| 5  | Get All Tasks       | `GET /tasks`                         | Afișează toate taskurile                      |
| 6  | Get Task by ID      | `GET /tasks/{id}`                    | Afișează un task după ID                      |
| 7  | Get Task by Responsible | `GET /tasks/responsible?name=Ana`    | Filtrare după responsabil                     |
| 8  | Search by Title     | `GET /tasks/search?title=tema`      | Căutare în titlu                              |
| 9  | Delete Task         | `DELETE /tasks/{id}`                 | Șterge un task după ID                        |

