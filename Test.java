package tema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;

import javax.xml.validation.SchemaFactoryConfigurationError;

public class Test {

	int find_index_if2(String s1) {
		int ok = 0;
		int k = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == '[') {
				k++;
				ok = 1;
			}
			if (s1.charAt(i) == ']')
				k--;
			if (k == 0 && ok == 1) {
				return i;
			}
		}

		return -1;

	}

	int find_index_if3(String s1) {
		int ok = 0;
		int k = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == '[') {
				k++;
				ok = 1;
			}
			if (s1.charAt(i) == ']')
				k--;
			if (k == 0) {
				return i;
			}
		}

		return -1;

	}

	int find_index_if(String s1) {
		int ok = 0;
		int k = 0;
		for (int i = 1; i < s1.length(); i++) {
			if (s1.charAt(i) == '[') {
				k++;
				ok = 1;
			}
			if (s1.charAt(i) == ']')
				k--;
			if (k == 0 && ok == 1) {
				return i;
			}
		}

		return -1;

	}

	int find_index(String s1) {
		int k = 0;
		int ok = 0;
		if (s1.charAt(3) != '[')
			return 3;
		else
			for (int i = 1; i < s1.length(); i++) {
				if (s1.charAt(i) == '[') {
					k++;
					ok = 1;
				}
				if (s1.charAt(i) == ']')
					k--;
				if (k == 0 && ok == 1) {
					return i;
				}
			}

		return -1;
	}

	int check_paranteze(String s1) {
		for (int i = 0; i < s1.length(); i++)
			if (s1.charAt(i) == '[')
				return 1;

		return 0;
	}

	Node NodeFactoryst(String s1, int index) {

		if (index > 3)
			return new ExprNode();

		return new VarNode();
	}

	Node NodeFactorydr(String s1, int index) {
		if (check_paranteze(s1) == 1)
			return new ExprNode();

		return new VarNode();
	}

	Node solver(String s1, Node top, Variables[] variables) {

		// System.out.println(s1);
		int k = 0;
		int index;
		String a;
		String b;
		boolean add;
		int checker = 0;
		index = find_index(s1);
		char oper = s1.charAt(1);
		char oper2 = s1.charAt(2);
		top.value = s1.substring(1, 2);
		if (top.value.equals("+"))
			top = new PlusNode();
		else if (top.value.equals("-"))
			top = new MinusNode();
		else if (top.value.equals("=") && oper2 != '=')
			top = new EqualsNode();
		else if (top.value.equals("*"))
			top = new OrNode();
		else if (top.value.equals(">") || top.value.equals("<") || oper2 == '=')
			top = new BiggerSmallerNode();
		top.value = s1.substring(1, 2);
		if ((oper == '+' || oper == '-' || oper == '*' || oper == '/' || oper == ';' || oper == '=' || oper == '<'
				|| oper == '>') && oper2 != '=') {

			if (index <= 3) {
				int i = 3;
				if (Character.isDigit(s1.charAt(i))) {
					while (Character.isDigit(s1.charAt(i)))
						i++;
				} else {
					while (Character.isLetter(s1.charAt(i)))
						i++;
				}
				k = i - 3 - 1;

			}

			a = s1.substring(3, index + 1 + k);
			b = s1.substring(index + 2 + k, s1.length() - 1);
			top.st = NodeFactoryst(s1, index);
			top.st.dad = top;
			top.dr = NodeFactorydr(b, index);
			top.dr.dad = top;
			top.st.value = a;
			top.dr.value = b;
			if (index > 3) {
				top.st = solver(a, top.st, variables);
				if (check_paranteze(b) == 1) {
					top.dr = solver(b, top.dr, variables);
				}

			} else {
				if (check_paranteze(b) == 1) {
					top.dr = solver(b, top.dr, variables);
				}
			}
		}
		if (oper == '=' && oper2 == '=') {

			if (index <= 4) {
				int i = 4;
				if (Character.isDigit(s1.charAt(i))) {
					while (Character.isDigit(s1.charAt(i)))
						i++;
				} else {
					while (Character.isLetter(s1.charAt(i)))
						i++;
				}

				k = i - 4 - 1;

			}

			a = s1.substring(4, index + 2 + k);
			b = s1.substring(index + 3 + k, s1.length() - 1);
			top.st = NodeFactoryst(s1, index - 1);
			top.st.dad = top;
			top.dr = NodeFactorydr(b, index - 1);
			top.dr.dad = top;
			top.st.value = a;
			top.dr.value = b;
			if (index > 4) {
				top.st = solver(a, top.st, variables);
				if (check_paranteze(b) == 1) {
					top.dr = solver(b, top.dr, variables);
				}

			} else {
				if (check_paranteze(b) == 1) {
					top.dr = solver(b, top.dr, variables);
				}
			}
		}
		if (oper == 'i') {

			IfNode ifnode = new IfNode();
			// top = new IfNode(top.st, top.dr, top.dad, top.condition,
			// top.visible, top.init, top.body, top.value);
			index = find_index_if(s1);
			// top.condition = s1.substring(4, index + 1);
			ifnode.value = top.value;
			ifnode.conditie.value = s1.substring(4, index + 1);
			ifnode.conditie = solver(ifnode.conditie.value, ifnode.conditie, variables);
			top = ifnode;
			s1 = s1.substring(index + 2, s1.length() - 1);
			// System.out.println(s1);
			index = find_index_if2(s1);
			top.st = new ExprNode();
			top.dr = new ExprNode();

			a = s1.substring(0, index + 1);
			// System.out.println(a.length());
			// System.out.println("1 " + a);
			b = s1.substring(index + 2, s1.length());
			// System.out.println("2 " + b);
			top.st.value = s1.substring(0, index + 1);
			top.dr.value = s1.substring(index + 2, s1.length());
			top.st = solver(a, top.st, variables);
			top.dr = solver(b, top.dr, variables);

		}

		if (oper == 'f') {

			ForNode forNode = new ForNode();
			// System.out.println(s1);
			// top = new ForNode(top.st, top.dr, top.dad, top.condition,
			// top.visible, top.init, top.body, top.value);
			index = find_index_if(s1);
			// System.out.println("initialire" + " "+ s1.substring(5,
			// index+1));//init
			// top.init = s1.substring(5, index + 1);
			forNode.value = top.value;
			forNode.initializare.value = s1.substring(5, index + 1);
			forNode.initializare = solver(forNode.initializare.value, forNode.initializare, variables);
			// System.out.println(s1.substring(index+2,s1.length()-1));
			s1 = s1.substring(index + 2, s1.length() - 1);
			// System.out.println(s1);
			// System.out.println(s1);
			index = find_index_if3(s1);
			// System.out.println(index);
			// System.out.println("conditie" + " " + s1.substring(0,
			// index+1));//cond
			// top.condition = s1.substring(0, index);
			forNode.conditie.value = s1.substring(0, index + 1);
			forNode.conditie = solver(forNode.conditie.value, forNode.conditie, variables);
			s1 = s1.substring(index + 2, s1.length());
			// System.out.println(s1);
			index = find_index_if3(s1);
			forNode.pas.value = s1.substring(0, index + 1);
			forNode.pas = solver(forNode.pas.value, forNode.pas, variables);
			forNode.executie.value = s1.substring(index + 2, s1.length());
			forNode.executie = solver(forNode.executie.value, forNode.executie, variables);
			top = forNode;
			// System.out.println("pas" + " " + s1.substring(0, index+1));//pas
			// top.body = s1.substring(index + 3, s1.length());
			s1 = s1.substring(index + 2, s1.length());
			// System.out.println("executie" + " " + s1); //executie
			/*
			 * index = find_index_if(s1); a = s1.substring(0, index); b =
			 * s1.substring(index , s1.length()); System.out.println(a);
			 * System.out.println(b);
			 */
		}

		if (oper == 'r') {
			ReturnNode retur = new ReturnNode();
			index = find_index_if2(s1);
			// top.value = s1.substring(8, index);
			retur.rezultat.value = s1.substring(8, index);
			if (check_paranteze(retur.rezultat.value) == 1)
				retur.rezultat = solver(retur.rezultat.value, retur.rezultat, variables);
			top = retur;
		}
		if (oper == 'a') {
			AssertNode asrt = new AssertNode();
			index = find_index_if3(s1);
			asrt.st = new ExprNode();
			asrt.st.value = s1.substring(8, index);
			// System.out.println(asrt.st.value);
			if (check_paranteze(asrt.st.value) == 1)
				asrt.st = solver(asrt.st.value, asrt.st, variables);
			top = asrt;

		}

		return top;
	}

	void print(Node top, int k) {

		System.out.print("Nivel:" + k + "  ");
		System.out.println(top.value);
		if (top.st != null) {
			System.out.print("stanga");
			print(top.st, k + 1);
		}
		if (top.dr != null) {
			System.out.print("dreapta");
			print(top.dr, k + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		br = new BufferedReader(new FileReader("date.in"));
		EvaluationVisitor evaluate = new EvaluationVisitor();
		String s = "";
		String sCurrentLine;

		while ((sCurrentLine = br.readLine()) != null) {

			sCurrentLine = sCurrentLine.trim();

			if (!sCurrentLine.equals("")) {

				if (sCurrentLine.equals("]")) {
					s += sCurrentLine;
				} else {
					s += " " + sCurrentLine;
				}
				// System.out.println(sCurrentLine.replaceAll("\\s+",""));
				// System.out.print(sCurrentLine);

			}
		}
		s = s.substring(1, s.length());
		// s = br.readLine();
		// System.out.print(s);
		// System.out.println(s.length());
		Variables[] variables = new Variables[1000];
		Test nou = new Test();
		Node top = new ExprNode();
		// nou.solver_if(s);

		top = nou.solver(s, top, variables);
		// top.adauga_nume("abc", variables);
		// top.adauga_valoare("3", variables, "abc");
		// System.out.println(top.getVar("abc", variables));
		// nou.print(top, 0);

		top.accept(evaluate);

		BufferedWriter wr = new BufferedWriter(new FileWriter("date.out"));
		if (top.error != null)
			wr.write(top.error);
		else if (evaluate.missing_return == 1) {

			wr.write("Missing Return");
		}
		else
			if(evaluate.assert_failed == 0)
				wr.write("ASSERT FAILED");
			else
				wr.write(evaluate.value);
		wr.close();
		// System.out.println(top.value);
		// nou.print(top, 0);
		// System.out.println(evaluate.variables[0].name);
		// System.out.println(evaluate.variables[0].valoare);
		// if(top instanceof IfNode)
		// System.out.println("yes");
		// int index = nou.find_index(s);
		// System.out.println(nou.find_index(s));
		// System.out.println(s.substring(0, index + 1));
		// System.out.println(s.substring(index + 2, s.length()));
	}
}
