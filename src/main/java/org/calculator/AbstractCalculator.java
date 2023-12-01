package org.calculator;

public interface AbstractCalculator {

    /**
     * Ввод выражения для его последующего вычисления
     *
     * @param expression математическое выражение
     */
    public void input(String expression);

    /**
     * Решение математического выражения.
     *
     * @return результат введённого ранее примера
     */
    public double solve();

}
