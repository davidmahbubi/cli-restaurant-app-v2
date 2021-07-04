package com.main;

import java.util.ArrayList;

public class Main {

    public static Menu[] menusList;
    public static Table[] tablesList;
    public static Worker[] workers;

    public static void main(String[] args) {

        menusList = new Menu[10];
        workers = new Worker[3];

        menusList[0] = new Menu("Spageti Carbonara", 3, 'm');
        menusList[1] = new Menu("Baked Salmon", 4, 'm');
        menusList[2] = new Menu("Beef Steak", 5, 'm');
        menusList[3] = new Menu("Pesto Chicken", 30, 's');
        menusList[4] = new Menu("Chicken Teriyaki", 10, 's');
        menusList[5] = new Menu("Thai Red Curry", 15, 's');
        menusList[6] = new Menu("Lobster Mac Cheese", 30, 's');
        menusList[7] = new Menu("Rissoto", 22, 's');
        menusList[8] = new Menu("Shoyu Ramen", 23, 's');
        menusList[9] = new Menu("Butter Scallops", 1.5f, 'm');

        int[][] tableSchema = {
                {0, 2, 1},
                {1, 5, 8},
                {4, 1, 3},
                {7, 6, 2},
                {9, 5, 1},
                {8, 1, 3},
                {9, 1, 3},
                {7, 5, 1},
                {2, 4, 6},
                {1, 0, 3},
        };

        initTable(tableSchema);

        workers[0] = new Worker(0, "\uD83E\uDDD1\u200D\uD83C\uDF73", Main::traceMenuOnTable);
        workers[1] = new Worker(1, "\uD83E\uDDD1\uD83C\uDFFB\u200D\uD83C\uDF73", Main::traceMenuOnTable);
        workers[2] = new Worker(2, "\uD83D\uDC69\uD83C\uDFFC\u200D\uD83C\uDF73", Main::traceMenuOnTable);

        assignJob(workers[0]);
        assignJob(workers[1]);
        assignJob(workers[2]);
    }

    public static void initTable(int[][] tableSchemes) {
        tablesList = new Table[ 10];
        for (int i = 0; i < tableSchemes.length; i++) {
            Menu[] tableListScheme = {menusList[tableSchemes[i][0]], menusList[tableSchemes[i][1]], menusList[tableSchemes[i][2]]};
            tablesList[i] = new Table(tableListScheme);
        }
    }

    public static void traceMenuOnTable(int workerID) {
        assignJob(workers[workerID]);
    }

    public static void assignJob(Worker worker) {

        for (Table menus : tablesList) {

            if (menus.size() > 0) {

                Menu menuOrder = menus.getFirst();
                worker.setMenu(menuOrder);

                ArrayList<Integer> tableWithSameMenus = new ArrayList<>();

                for (int i = 0; i < tablesList.length; i++) {
                    if (tablesList[i].remove(menuOrder)) {
                        tableWithSameMenus.add(i);
                    }
                }

                worker.setTableIndexes(tableWithSameMenus);
                worker.doCook();

                return;
            }
        }
    }
}
