package org.example;


import java.util.ArrayList;
import java.util.List;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1439">https://acm.timus.ru/problem.aspx?space=1&num=1439</a>
     */
    public List<Integer> getOriginalDoorNumbers(int maxDoors, List<Action> actionList) {
        ImplicitTreap implicitTreap = new ImplicitTreap();
        for (int i = 0; i < maxDoors; ++i) {
            implicitTreap.add(i, i);
        }

        List<Integer> result = new ArrayList<>();
        for (Action action : actionList) {
            if (action.isLook()) {
                Integer trueNumber = implicitTreap.search(action.doorNumber);
                result.add(trueNumber);
            } else {
                implicitTreap.remove(action.doorNumber);
            }
        }
        return result;
    }

    /**
     * <h1>Задание 2.</h1>
     * Решить задачу <br/>
     * <a href="
     * ">https://acm.timus.ru/problem.aspx?space=1&num=1521</a><br/>
     * <h2>Пошагово</h2>
     * Для 5 3 входных данных:<br/>
     * _ -> 3 позиции<br/>
     * _ 1 2 <b>3</b> 4 5 => 3 <br/>
     * <b>1</b> 2 _ 4 5 => 1 <br/>
     * _ 2 4 <b>5</b> => 5 <br/>
     * <b>2</b> 4 _ => 2 <br/>
     * _ <b>4</b> => 4
     */
    public List<Integer> getLeaveOrder(int maxUnits, int leaveInterval) {
        ImplicitTreap implicitTreap = new ImplicitTreap();
        for (int i = 0; i < maxUnits; ++i) {
            implicitTreap.add(i, i + 1);
        }

        List<Integer> result = new ArrayList<>();

        int pos = leaveInterval - 1;
        while (maxUnits > 1) {
            result.add(implicitTreap.search(pos));
            implicitTreap.remove(pos);
            --maxUnits;
            pos = (pos - 1 + leaveInterval)%maxUnits;
        }
        result.add(implicitTreap.search(0));
        return result;
    }

}
