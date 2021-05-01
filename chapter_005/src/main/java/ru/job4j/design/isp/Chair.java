package ru.job4j.design.isp;

interface Chair {

    // интерфейс клесло, метод seat подходит для всех, метод lie и transform только для трансформируемых
    boolean seat();
    boolean lie();
    boolean transform();
}
