package tema;

public class ForNode extends Node {
	
	Node conditie = new ExprNode();
	Node initializare = new ExprNode();
	Node pas = new ExprNode();
	Node executie = new ExprNode();
	
	public ForNode(){
		
	}
	
/*	public ForNode(Node st1,Node dr,Node dad,String condition,String visible,String init,String body,String value) {
		this.st = st1;
		this.dr = dr;
		this.dad = dad;
		this.condition = condition;
		this.visible = visible;
		this.init = init;
		this.body = body;
		this.value = value;
		// TODO Auto-generated constructor stub
	}
*/
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	@Override
	void execute(Node top,Variables [] variables) {
		// TODO Auto-generated method stub
		
	}

}
