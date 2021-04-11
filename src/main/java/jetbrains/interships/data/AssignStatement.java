package jetbrains.interships.data;

public class AssignStatement implements IStatement {
    public String name;

    public AssignStatement(String name, ExprNodeTree value) {
        this.name = name;
        this.value = value;
    }

    public ExprNodeTree value;

    public void accept ( IVisitor v ) {
        v.visit( this );
    }
}
