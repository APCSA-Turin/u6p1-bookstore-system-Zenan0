package com.example.project;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BookStore store = new BookStore();
        System.out.println("Welcome to the GFG Library!");
        System.out.println("Select From The Following Options:");
        System.out.println("-------------------------------------------");
        System.out.println("Press 0 to Exit Application.");
        System.out.println("Press 1 to Add new Book.");
        System.out.println("Press 2 to Upgrade Quantity of a Book.");
        System.out.println("Press 3 to Search a Book.");
        System.out.println("Press 4 to Show All Books.");
        System.out.println("Press 5 to Register Student.");
        System.out.println("Press 6 to Show All Registered Students.");
        System.out.println("Press 7 to Check Out Book.");
        System.out.println("Press 8 to Check In Book.");
        System.out.println("-------------------------------------------");
        int userOption = scan.nextInt();
        scan.nextLine();
        while (userOption != 0) {
            if (userOption == 1) {
                System.out.println("Enter Serial No of Book:");
                String serialNo = scan.nextLine();
                System.out.println("Enter Book Name:");
                String bookName = scan.nextLine();
                System.out.println("Enter Author Name:");
                String authorName = scan.nextLine();
                System.out.println("Enter quantity of books:");
                int quantity = scan.nextInt();
                System.out.println("Enter year published:");
                int year = scan.nextInt();
                Book newBook = new Book(bookName, authorName, year, serialNo, quantity);
                store.addBook(newBook);
                System.out.println("Your book has been added!");
            }
            if (userOption == 2) {
                System.out.println("Which book do you want to upgrade the quantity of? (Enter serial number)");
                String bookNum = scan.nextLine();
                System.out.println("How much do you want to increase the quantity by?");
                int increaseQuantity = scan.nextInt();
                scan.nextLine();
                int bookIdx = searchBook(bookNum, store.getBooks());
                Book selectedBook = store.getBooks()[bookIdx];
                selectedBook.setQuantity(selectedBook.getQuantity() + increaseQuantity);
                System.out.println("The quantity has been increased!");
            }
            if (userOption == 3) {
                System.out.println("What book are you looking for? (Enter name of the book)");
                String bookName = scan.nextLine();
                Book[] bookList = store.getBooks();
                int bookIdx = -99;
                for (int i = 0; i < bookList.length; i++) {
                    if (bookName.equals(bookList[i].getTitle())) {
                        bookIdx = i;
                    }
                }
                if (bookIdx == -99) {
                    System.out.println("No book with that name is in the library.");
                } else {
                    System.out.println(bookList[bookIdx].bookInfo());
                }
            }
            if (userOption == 4) {
                System.out.println(store.bookStoreBookInfo());
            }
            if (userOption == 5) {
                System.out.println("Enter your name:");
                String name = scan.nextLine();
                IdGenerate.generateID();
                User student = new User(name, IdGenerate.getCurrentId());
                store.addUser(student);
                System.out.println("You have been registered! Your ID number is " + IdGenerate.getCurrentId() + ".");
            }
            if (userOption == 6) {
                System.out.println(store.bookStoreUserInfo() + "\n");
            }
            if (userOption == 7) {
                System.out.println("Enter your ID:");
                int Id = scan.nextInt();
                scan.nextLine();
                System.out.println("What book would you like to check out? (Enter Serial Number)");
                String serialNum = scan.nextLine();
                int bookIdx = searchBook(serialNum, store.getBooks());
                Book selectedBook = store.getBooks()[bookIdx];
                User student = store.getUsers()[Id - 100];
                Book[] newBookList = student.getBooks();
                Boolean isNull = false;
                int idx = 0;
                while (isNull != true) {
                    if (newBookList[idx] == null) {
                        newBookList[idx] = selectedBook;
                        isNull = true;
                    }
                    idx++;
                }
                student.setBooks(newBookList);
                store.removeBook(selectedBook);
                System.out.println("You have successfully checked out the book!");
            }
            if (userOption == 8) {
                System.out.println("Enter your ID:");
                int Id = scan.nextInt();
                scan.nextLine();
                System.out.println("Enter Serial No of the Book you would like to return:");
                String serialNo = scan.nextLine();
                User student = store.getUsers()[Id - 100];
                Book[] newBookList = student.getBooks();
                for (int i = 0; i < newBookList.length; i++) {
                    if (newBookList[i] != null && serialNo.equals(newBookList[i].getIsbn())) {
                        newBookList[i] = null;
                    }
                }
                student.setBooks(newBookList);
                Boolean isInLibrary = false;
                Book[] bookList = store.getBooks();
                int bookNum = 0;
                for (int i = 0; i < bookList.length; i++) {
                    if (bookList[i].getIsbn().equals(serialNo)) {
                        isInLibrary = true;
                        bookNum = i;
                    }
                }
                if (isInLibrary) {
                    bookList[bookNum].setQuantity(bookList[bookNum].getQuantity() + 1);
                    System.out.println("You have successfully returned a book!");
                } else {
                    System.out.println("Enter Book Name:");
                    String bookName = scan.nextLine();
                    System.out.println("Enter Author Name:");
                    String authorName = scan.nextLine();
                    System.out.println("Enter year published:");
                    int year = scan.nextInt();
                    Book newBook = new Book(bookName, authorName, year, serialNo, 1);
                    store.addBook(newBook);
                    System.out.println("You have successfully returned a book!");
                }
            }
            System.out.println("Select From The Following Options:");
            System.out.println("-------------------------------------------");
            System.out.println("Press 0 to Exit Application.");
            System.out.println("Press 1 to Add new Book.");
            System.out.println("Press 2 to Upgrade Quantity of a Book.");
            System.out.println("Press 3 to Search a Book.");
            System.out.println("Press 4 to Show All Books.");
            System.out.println("Press 5 to Register Student.");
            System.out.println("Press 6 to Show All Registered Students.");
            System.out.println("Press 7 to Check Out Book.");
            System.out.println("Press 8 to Check In Book.");
            System.out.println("-------------------------------------------");
            userOption = scan.nextInt();
            scan.nextLine();
        }
        System.out.println("Thank you for checking out the GFG Library!");
        scan.close();
    }

    public static int searchBook(String serialNum, Book[] bookList) {
        for (int i = 0; i < bookList.length; i++) {
            if (serialNum.equals(bookList[i].getIsbn())) {
                return i;
            }
        }
        return -99;
    }
}
