package ru.job4j.ood.ocp;

public class Windows {

    /*
    Если окно не имеет возможности открываться, то надо предусмотреть поведение метода для него,
    если бы был интерфейс с методом open, то можно было его наследовать для окон, которые открываются
     */
    public String open() {
        return "Window opened";
    }
}
