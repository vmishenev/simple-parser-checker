package jetbrains.interships.data;

public class IfStatement implements IStatement {
    ExprNodeTree cond;

    public IfStatement(ExprNodeTree cond, StatementList body) {
        this.cond = cond;
        this.body = body;
    }

    StatementList body;
    public void accept ( IVisitor v ) {
        v.visit( this );
    }
}
