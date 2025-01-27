package com.example.project;

public class User{
    //requires 3 private attributes String name, String Id, Book book that is initialized to empty
    private String name;
    private String Id;
    private Book[] book = new Book[5];

    //requires 1 contructor with two parameters that will initialize the name and id
    public User(String name, String Id) {
        this.name = name;
        this.Id = Id;
    }
 
    // Returns the instance variable name
    public String getName() {
        return name;
    }

    // Sets name to n, the String passed in the parameter
    public void setName(String n) {
        name = n;
    }

    // Returns the instance variable Id
    public String getId() {
        return Id;
    }

    // Sets the Id instance variable to the Id String variable passed in the parameter
    public void setId(String Id) {
        this.Id = Id;
    }

    // Returns the instance variable book
    public Book[] getBooks() {
        return book;
    }

    // Sets book to b, the Book[] variable passed in the parameter
    public void setBooks(Book[] b) {
        book = b;
    }

    public String bookListInfo() { //returns a booklist for the user, if empty, output "empty"
        String str = "Books: \n";
        for (int i = 0; i < 5; i++) {
            if (book[i] == null) {
                str += "empty\n";
            } else {
                str += book[i].bookInfo() + "\n";
            }
        }
        return str;
    }

    public String userInfo() { //returns  "Name: []\nID: []\nBooks:\n[]"
        String str = "Name: " + name + "\n";
        str += "Id: " + Id + "\n";
        str += this.bookListInfo();
        return str;
    }
       
}