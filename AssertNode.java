package tema;

public class AssertNode extends Node{

	@Override
	void execute(Node top, Variables[] variables) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

}
