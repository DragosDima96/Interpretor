package tema;

abstract public class Node {
	
	static String error = null;
	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	int getVar(String nume, Variables[] variables) {

		int i = 0;
		while (variables[i] != null) {
			if ((variables[i].name.equals(nume)))
				break;
			i++;
		}
		if (variables[i] == null) {
			//System.out.println("ERROR");
			error = "CHECK_FAILED";
			return 0;
		} else
			return (Integer.parseInt(variables[i].valoare));

	}
	boolean check_var(String nume, Variables[] variables){
		int i = 0;
		while (variables[i] != null) {
			if ((variables[i].name.equals(nume)))
				return true;
			i++;
		}
		return false;
	}
	
	int check_nume(String nume,Variables[] variables){
		int i = 0;
		while (variables[i] != null) {
			if ((variables[i].name.equals(nume)))
				return 1;
			i++;
		}
		
		
		return 0;
	}
	
	
	
	void adauga_nume(String nume, Variables[] variables) {
		int i = 0;
		while (variables[i] != null)
			i++;
		variables[i] = new Variables();
		variables[i].name = nume;

	}

	void adauga_valoare(String valoare, Variables[] variables, String nume) {
		int i = 0;
		while (!(variables[i].name.equals(nume)))
			i++;

		variables[i].valoare = valoare;

	}

	Node dad;
	Node st;
	Node dr;
//	String condition;
	//String visible;
	String value;
//	String init;
//	String body;

	abstract void execute(Node top, Variables[] variables);

	abstract void accept(Visitor visitor);
}
