package jetbrains.interships.data;

public interface IVisitor {
    public void visit(StatementList l);

    public void visit(WhileStatement p);

    public void visit(IfStatement p);

    public void visit(AssignStatement p);

    public void visit(ExprNodeTree p);
}
