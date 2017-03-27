package tema;

public class OrNode extends Node{
	String result;

	@Override
	void execute(Node top, Variables[] variables) {

		String a = null;
		String b = null;
		int val1 = 0;
		int val2 = 0;
		a = top.st.value;
		b = top.dr.value;
		if (isNumeric(a) == true)
			val1 = Integer.parseInt(a);
		else
			val1 = getVar(a, variables);
		
		if (isNumeric(b) == true)
			val2 = Integer.parseInt(b);
		else
			val2 = getVar(b, variables);
		val1 = val1 * val2;
		top.value = val1 + "";

	}

	@Override
	void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

}
