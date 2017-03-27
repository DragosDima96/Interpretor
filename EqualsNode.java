package tema;

public class EqualsNode extends Node {
	String result;

	@Override
	void execute(Node top,Variables [] variables) {
		String a = top.st.value;
		if(top.check_var(a, variables)==false)
		adauga_nume(a, variables);
		
		String b = top.dr.value;
		adauga_valoare(b, variables,a);
	}

	@Override
	void accept(Visitor visitor) {
		visitor.visit(this);
		// TODO Auto-generated method stub
		
	}
}
