package ru.job4j.ood.srp;

public interface Library {
    <T> T getBook(String bookName);
    <T> T addUser(String name);
}
