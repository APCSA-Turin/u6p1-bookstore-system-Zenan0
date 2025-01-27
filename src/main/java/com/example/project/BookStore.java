package com.example.project;

public class BookStore{

    //requires at least 2 attributes Book[] books, User[] users (initialized to an empty array of 10 max users) 
    private Book[] books = new Book[0];
    private User[] users = new User[10];

    //requires 1 empty constructor
    public BookStore() { }

    // Returns users array
    public User[] getUsers() {
        return users;
    }

    // Sets instance variable users to parameter newUsers
    public void setUsers(User[] newUsers) {
        users = newUsers;
    }

    // Returns books array
    public Book[] getBooks() {
        return books;
    }

    // Adds the user parameter to the first null value in the users array
    public void addUser(User user) {
        // Boolean variable to track if a null value in the users array has been found yet
        Boolean isNull = false;
        // Int variable of the current index of users array
        int i = 0;
        // Checks to see if the first null value has been found yet
        while (isNull != true) { 
            // Checks to see if the current index of users holds a null value
            // If it is a null value, it gets replaced with the user parameter and isNull becomes true to stop the while loop
            if (users[i] == null) {
                users[i] = user;
                isNull = true;
            }
            // moves onto the next index of the users array
            i++;
        }
    } 

    public void removeUser(User user) {
        // Iterates over the users array
        for (int i = 0; i < users.length; i++) {
            // Checks to see if the current element of users is the same as the user variable passed in the parameter
            // If it is, the current element gets set to null and the users array gets consolidated
            if (users[i] == user) {
                users[i] = null;
                consolidateUsers();
            }
        }
    }

    // Moves all the null values in users array to the back and puts the non-null values at the front of the array
    public void consolidateUsers() {
        // Creates a new array with all null values
        User[] newUsers = new User[users.length];
        // Int variable to track the earliest index of newUsers that has a null value
        int idx = 0;
        // For loop that iterates over all values of users array
        for (int i = 0; i < users.length; i++) {
            // Checks to see if the current element of users is not equal to null
            // If it is not equal, the earliest index of newUsers that is null gets set to the current element of users and idx is incremented by one to move to the next null value in newUsers array
            if (users[i] != null) {
                newUsers[idx] = users[i];
                idx++;
            }
        }
        // Sets the users array to the newUsers array
        users = newUsers;
    }

    public void addBook(Book book) {
        // Creates a new Book array that has the same length as books + 1
        Book[] newBooks = new Book[books.length + 1];
        // Checks to see if the length of books is 1
        // If it isn't, all of the elements in books gets copied to newBooks
        if (books.length != 0) {
            for (int i = 0; i < books.length; i++) {
                newBooks[i] = books[i];
            }
        }
        // Sets the last element of newBooks to the book variable passed in the parameter
        newBooks[books.length] = book;
        // Sets books to newBooks
        books = newBooks;
    }

    public void insertBook(Book book, int index) {
        // Creates a new Book array with the same length as books + 1
        Book[] newBooks = new Book[books.length + 1];
        // Iterates from 0 to the value of index
        // Sets the current element of newBooks to the current element of books
        // If i equals index, the current element of newBooks gets set to the book variable passed in the parameter instead
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                newBooks[i] = book;
            } else {
                newBooks[i] = books[i];
            }
        }
        // Int variable to track the current element of books, starting at the element that didn't get added in the previous for loop
        int idx = index;
        // Iterates over the remaining elements of newBooks that are null
        // Fills in the null values with the element that is at the idx of books
        // idx increments by one to reach the end of the books array
        for (int i = index + 1; i < newBooks.length; i++) {
            newBooks[i] = books[idx];
            idx++;
        }
        // Sets books to newBooks
        books = newBooks;
    }

    public void removeBook(Book book) {
        // Int variable that is used to track which index the book variable passed in the parameter is located at in books
        // Initialized to 0
        int bookIdx = 0;
        // For loop that iterates over each element of books
        // If the current element in books is equal to the book variable passed in the parameter, the quantity of the book is decreased by 1 and bookIdx is set to i
        for (int i = 0; i < books.length; i++) {
            if (books[i] == book) {
                books[i].setQuantity(books[i].getQuantity() - 1);
                bookIdx = i;
            }
        }
        // Checks to see if the quantity of the book at bookIdx is equal to zero
        // If it is, a new Book array is created with the same length as books - 1
        if (books[bookIdx].getQuantity() == 0) {
            Book[] newBooks = new Book[books.length - 1];
            // Int variable used as the index of newBooks
            int idx = 0;
            // For loop that iterates over each element in the books array
            // If the current book in books is not equal to the book variable passed in the parameter, the current book is added to the newBooks array and idx is incremented by one
            // idx does not increase if the current book is equal to the book variable and it does not get added to the newBooks array
            for (int i = 0; i < books.length; i++) {
                if (books[i] != book) {
                    newBooks[idx] = books[i];
                    idx++;
                }
            }
            // Sets books to newBooks
            books = newBooks;
        }
    }
       
    public String bookStoreBookInfo() { //you are not tested on this method but use it for debugging purposes
        String str = "Books: \n";
        for (int i = 0; i < books.length; i++) {
            str += books[i].bookInfo() + "\n";
        }
        return str;
    }

    public String bookStoreUserInfo() { //you are not tested on this method but use it for debugging purposes
        String str = "Users: \n";
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                str += users[i].userInfo();
            }
        }
        return str;
    }

}