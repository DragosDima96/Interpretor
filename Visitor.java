package tema;

public interface Visitor {

	Node visit(ExprNode nod);
	Node visit(IfNode nod);
	Node visit(ForNode nod);
	Node visit(VarNode nod);
	Node visit(EqualsNode nod);
	Node visit(PlusNode nod);
	Node visit(MinusNode nod);
	Node visit(OrNode nod);
	Node visit(BiggerSmallerNode nod);
	Node visit(ReturnNode nod);
	Node visit (AssertNode nod);
}

