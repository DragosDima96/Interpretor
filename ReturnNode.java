package tema;

public class ReturnNode extends Node{

	String error;
	Node rezultat = new VarNode();
	@Override
	void execute(Node top, Variables[] variables) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

}
