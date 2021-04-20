package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportXML implements Report {
    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<Employees>");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<Employee>")
                    .append(System.lineSeparator())
                    .append("<name>")
                    .append(employee.getName())
                    .append("</name>")
                    .append(System.lineSeparator())
                    .append("<hired>")
                    .append(employee.getHired())
                    .append("</hired>")
                    .append(System.lineSeparator())
                    .append("<fired>")
                    .append(employee.getFired())
                    .append("</fired>")
                    .append(System.lineSeparator())
                    .append("<salary>")
                    .append(employee.getSalary())
                    .append("</salary>")
                    .append(System.lineSeparator())
                    .append("</Employee>")
                    .append(System.lineSeparator());
        }
        text.append("</Employees>");
        return text.toString();
    }
}
