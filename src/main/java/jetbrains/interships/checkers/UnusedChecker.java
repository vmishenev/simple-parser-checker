package jetbrains.interships.checkers;

import jetbrains.interships.data.*;

public class UnusedChecker extends Checker {
    class Visitor implements IVisitor {


        @Override
        public void visit(StatementList l) {

        }

        @Override
        public void visit(WhileStatement p) {

        }

        @Override
        public void visit(IfStatement p) {

        }

        @Override
        public void visit(AssignStatement p) {

        }

        @Override
        public void visit(ExprNodeTree p) {

        }
    }
    @Override
    void check(StatementList list) {

        list.accept( new Visitor());
    }
}
