package tema;

public class ExprNode extends Node{
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	void execute(Node top,Variables [] variables) {
		// TODO Auto-generated method stub
		
	}

}
