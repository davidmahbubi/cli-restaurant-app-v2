package com.main;

import java.util.LinkedList;

public class Table extends LinkedList<Menu> {
    public Table(Menu[] menuItems) {
        for (Menu menu : menuItems) {
            super.addLast(menu);
        }
    }
}
