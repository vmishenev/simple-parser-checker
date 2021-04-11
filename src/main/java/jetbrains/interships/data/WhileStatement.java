package jetbrains.interships.data;

public class WhileStatement implements IStatement {
    ExprNodeTree cond;

    public WhileStatement(ExprNodeTree cond, StatementList body) {
        this.cond = cond;
        this.body = body;
    }

    StatementList body;
    public void accept ( IVisitor v ) {
        v.visit( this );
    }

}
