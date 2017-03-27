package tema;

public class VarNode extends Node{
	
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	void execute(Node top,Variables [] variables) {
		System.out.println(top.value);

	}

}
