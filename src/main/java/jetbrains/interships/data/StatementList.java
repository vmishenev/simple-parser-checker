package jetbrains.interships.data;

import java.util.LinkedList;
import java.util.List;

public class StatementList implements Ast {
    LinkedList<IStatement> l;

    public StatementList() {
        l =new LinkedList<>();
    }

    public List<IStatement> getL() {
        return l;
    }

    public void setL(LinkedList<IStatement> l) {
        this.l = l;
    }

    public void accept ( IVisitor v ) {
        v.visit( this );
    }

}
