package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        StringBuilder text = new StringBuilder();
        text.append("{");
        text.append(System.lineSeparator());
        for (int i = 0; i < list.size(); i++) {
            text.append("{")
                    .append(System.lineSeparator())
                    .append("name: ")
                    .append(list.get(i).getName()).append(",")
                    .append(System.lineSeparator())
                    .append("hired: ")
                    .append(list.get(i).getHired()).append(",")
                    .append(System.lineSeparator())
                    .append("fired: ")
                    .append(list.get(i).getFired()).append(",")
                    .append(System.lineSeparator())
                    .append("salary: ")
                    .append(list.get(i).getSalary())
                    .append(System.lineSeparator())
                    .append("}");
            if (i < list.size() - 1) {
                text.append(",");
            }
            text.append(System.lineSeparator());
        }
        text.append("}");
        return text.toString();
    }
}
