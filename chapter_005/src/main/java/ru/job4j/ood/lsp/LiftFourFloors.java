package ru.job4j.ood.lsp;

public class LiftFourFloors extends Lift {
    private int floors;

    @Override
    public String call(int floor) {
        // нельзя подставить вместо родительского класса, усиленно предусловие
        // ослаблено постусловие
        // отсутствует проверка на отрицательное значение, не все условия базового класса переданы
        if (floor == 2) {
            throw new IllegalArgumentException("на второй не ездим");
        }
        if (floors < 4) {
            throw new IllegalArgumentException("в здании нет лифта");
        }
        return super.call(floor);
    }
}
