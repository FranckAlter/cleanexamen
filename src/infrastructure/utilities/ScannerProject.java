package infrastructure.utilities;

import UseCase.AddBook;
import UseCase.BorrowBook;
import UseCase.ReturnBook;
import UseCase.SeeAllBooks;
import domain.data.Book;
import domain.data.Borrow;
import domain.data.person.Member;
import infrastructure.factories.DaoFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ScannerProject {
    private Scanner scanner = new Scanner(System.in);

    public ScannerProject() {
        start();
    }

    private void start() {
        boolean endProgram = false;
        while (!endProgram) {
            System.out.println("Pour vous connecter en tant qu'utilisateur, entrez votre id, pour quitter le programme, entrez \"end\"");
            String s = scanner.nextLine();
            switch (s) {
                case "end":
                    endProgram = true;
                    break;
                default:
                    try {
                        int userId = Integer.parseInt(s);
                        List<Integer> existingId = DaoFactory.getPersonDao().getAllId();
                        if (existingId.contains(userId)) {
                            String role = DaoFactory.getPersonDao().getTypePerson(userId);
                            role (role, userId);
                        } else {
                            System.out.println("Utilisateur inconnu");
                        }
                    } catch (Exception e) {
                        System.out.println("Merci d'entrer une commande correcte");
                    }
            }
        }
    }

    private void role(String role, int userId) {
        switch (role) {
            case "M":
                member(userId);
                break;
            case "G":
                guest();
                break;
            case "L":
                librarian();
                break;
        }
    }

    private void member (int userId) {
        boolean endProgram = false;
        while (!endProgram) {
            System.out.println("Pour emprunter un livre, écrivez \"emprunt\", pour rendre un livre, écrivez \"retour\",pour retourner au menu principal, entrez \"menu\"");
            String s = scanner.nextLine();
            switch (s) {
                case "menu":
                    endProgram = true;
                    break;
                case "emprunt":
                    borrow (userId);
                    break;
                case "retour":
                    returned (userId);
                    break;
                default:
                    System.out.println("Merci d'entrer une commande correcte");
            }
        }
    }
    private void returned (int userId) {
        Member member = new Member (userId);
        int nbBorrow = DaoFactory.getBorrowDao().getNumberBorrowsForMember(member);
        System.out.println("Nombre de livres empruntés : " + nbBorrow);
        if (nbBorrow > 0) {
            System.out.println("Voici la liste des livres empruntés:");
            int count = 0;
            List<Book> books = DaoFactory.getBorrowDao().getBorrowBookForMember(member);
            for (Book book : books) {
                System.out.println("index n°" + count + " : " + book);
                count++;
            }
            System.out.println("Veuillez entrer l'index du livre que vous souhaitez rendre");
            String s = scanner.nextLine();
            try {
                int indexBook = Integer.parseInt(s);
                Book book = books.get(indexBook);
                Borrow borrow = new Borrow (book, member, Date.valueOf(LocalDate.now()));
                ReturnBook returnBook = new ReturnBook();
                returnBook.execute(borrow);

            } catch (Exception e) {
                System.out.println("Merci d'entrer une commande correcte");
            }
        }
    }
    private void borrow (int userId) {
            System.out.println("Voici la liste des livres disponibles:");
            int count = 0;
            List<Book> books = DaoFactory.getBookDao().getAvailableBooks();
            for (Book book : books) {
                System.out.println("index n°" + count + " : " + book);
                count++;
            }
            System.out.println("Veuillez entrer l'index du livre que vous souhaitez emprunter");
            String s = scanner.nextLine();
            try {
                int indexBook = Integer.parseInt(s);
                Book book = books.get(indexBook);
                Member member = new Member(userId);
                Borrow borrow = new Borrow (book, member, Date.valueOf(LocalDate.now()));
                BorrowBook borrowBook = new BorrowBook();
                borrowBook.execute(borrow);

            } catch (Exception e) {
                System.out.println("Merci d'entrer une commande correcte");
            }



    }

    private void guest() {
        boolean endProgram = false;
        while (!endProgram) {
            System.out.println("Pour consulter tous les livres, écrivez \"consultation\" pour retourner au menu principal, entrez \"menu\"");
            String s = scanner.nextLine();
            switch (s) {
                case "menu":
                    endProgram = true;
                    break;
                case "consultation":
                    SeeAllBooks seeAllBooks = new SeeAllBooks();
                    List<Book> books = seeAllBooks.execute();
                    for (Book book : books) {
                        System.out.println(book);
                    }
                    break;
                default:
                    System.out.println("Merci d'entrer une commande correcte");

            }
        }
    }

    private void  librarian () {
        boolean endProgram = false;
        while (!endProgram) {
            System.out.println("Pour ajout un livre, écrivez \"ajout\", pour retourner au menu principal, entrez \"menu\"");
            String s = scanner.nextLine();
            switch (s) {
                case "menu":
                    endProgram = true;
                    break;
                case "ajout":
                    addBook();
                    break;
                default:
                    System.out.println("Merci d'entrer une commande correcte");
            }
        }
    }

    private void addBook() {
        System.out.println("renseignez le titre du livre");
        String title = scanner.nextLine();
        System.out.println("renseignez l'auteur");
        String author = scanner.nextLine();
        Book book = new Book (title, author);
        AddBook addBook = new AddBook();
        addBook.execute(book);
        System.out.println("Livre ajouté");
    }

}
