package ru.job4j.design.isp;

interface Tree {
    // расти могут все деревья, хвойные листья не сбрасывают и зимой не спят

    boolean grow();
    boolean dropLeaves();
    boolean winterDreaming();
}
