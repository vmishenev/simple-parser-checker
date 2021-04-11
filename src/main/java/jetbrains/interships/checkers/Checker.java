package jetbrains.interships.checkers;

import jetbrains.interships.data.StatementList;

public abstract class Checker {
    abstract void check(StatementList list);
    void error(String err) {
        System.err.println(err);
    }
}
