package tema;

public class EvaluationVisitor implements Visitor {

	Variables[] variables = new Variables[1000];
	String error;
	int missing_return = 1;
	int assert_failed = 1;
	String value;
	void nup(Node top) {

		if (!(top.st instanceof VarNode))
			top.st.accept(this);
		if (!(top.dr instanceof VarNode))
			top.dr.accept(this);
	}

	void if_choice(IfNode top) {
		if (top.conditie.value.equals("true"))
			top.st.accept(this);
		else
			top.dr.accept(this);
	}

	void for_exec(ForNode top) {

		String value = top.conditie.value;
		top.initializare.accept(this);
		top.conditie.accept(this);
		String ceva = top.conditie.value;
		while (ceva.equals("true")) {

			top.executie.accept(this);
			top.pas.accept(this);
			top.conditie.value = value;
			top.conditie.accept(this);
			ceva = top.conditie.value;

		}

	}

	void return_choice(ReturnNode nod) {
		if (!(nod.rezultat instanceof VarNode))
			nod.rezultat.accept(this);
	}

	@Override
	public Node visit(ExprNode nod) {
		nup(nod);
		return null;
	}

	@Override
	public Node visit(OrNode nod) {
		// TODO Auto-generated method stub
		nup(nod);
		nod.execute(nod, variables);
		return null;
	}

	@Override
	public Node visit(MinusNode nod) {
		// TODO Auto-generated method stub
		nup(nod);
		nod.execute(nod, variables);
		return null;
	}

	@Override
	public Node visit(PlusNode nod) {
		nup(nod);
		nod.execute(nod, variables);
		return null;
	}

	@Override
	public Node visit(EqualsNode nod) {
		nup(nod);
		nod.execute(nod, variables);
		return null;
	}

	@Override
	public Node visit(IfNode nod) {
		nod.conditie.accept(this);
		if_choice(nod);
		return null;
	}

	@Override
	public Node visit(ForNode nod) {
		for_exec(nod);
		return null;
	}

	@Override
	public Node visit(BiggerSmallerNode nod) {
		nup(nod);
		nod.execute(nod, variables);
		return null;
	}

	@Override
	public Node visit(VarNode nod) {

		return null;
	}
	public Node visit(AssertNode nod) {

		nod.st.accept(this);
		if(nod.st.value.equals("false"))
		{
			assert_failed = 0;
		//	System.out.println("ASSERT FAILED");
		}
		return null;
	}

	public Node visit(ReturnNode nod) {
		missing_return = 0;
		return_choice(nod);
		Node a = new ExprNode();
		int val2;
		if (nod.isNumeric(nod.rezultat.value) == true)
			val2 = Integer.parseInt(nod.rezultat.value);
		else
			val2 = nod.getVar(nod.rezultat.value, variables);

		if (a.error == null){
			System.out.println(val2);
			value = val2+"";
		}
		return null;
	}

}
