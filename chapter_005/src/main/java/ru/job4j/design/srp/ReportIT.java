package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportIT implements Report {
    private Store store;

    public ReportIT(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html><body><p>Name; Hired; Fired; Salary;</p>");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>")
                    .append(System.lineSeparator());
        }
        text.append("</body></html>");
        return text.toString();
    }
}
