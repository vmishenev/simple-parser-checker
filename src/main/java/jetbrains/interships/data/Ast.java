package jetbrains.interships.data;

public interface Ast {
    void accept(IVisitor v);
}
