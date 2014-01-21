import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class LightCompiler {
	public static void main(String[] args){
		new LightCompiler();
	}
	
	LightCompiler(){
		while(true){
			try{
				// ���͂��ꂽ�v���O�����̎擾
				String str = getProgram();
				
				if(str.equals("EOF")) break;
				
				// ������
				ArrayList tokens = lex(str);
				
				if(tokens==null){
					System.out.println("Syntax Error");
				}
				
				// �\�����
				if(!parser(tokens)){
					System.out.println("Syntax Error");
				}
				
				// �Ӗ����
				if(!semantic(tokens)){
					System.out.println("Syntax Error");
				}
				
				// �R�[�h�œK��
				tokens = optimize(tokens);
				
				// �{���̓R�[�h�������s�����C���ʂ��o�͂���
				System.out.println(tokens.get(0));
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	String getProgram() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		return str;
	}
	
	ArrayList lex(String str){
		ArrayList tokens = new ArrayList();
		int index = str.indexOf("+");
		if(index < 1){
			tokens.add(str);
		}else{
			String ft = str.substring(0, index);
			String st = str.substring(index+1, str.length());
			tokens.add(ft);
			tokens.add(str.charAt(index)+"");
			tokens.add(st);
		}
		
		return tokens;
	}
	
	boolean parser(ArrayList tokens){
		if(tokens.size()>1){
			String op = (String)tokens.get(1);
			if(!op.equals("+")) return false;
		}
		return true;
	}
	
	boolean semantic(ArrayList tokens){
		if(tokens.size()==1){
			String str = (String)tokens.get(0);
			if(!isInt(str)) return false;
		}else{
			String ft = (String)tokens.get(0);
			String st = (String)tokens.get(2);
			if(!isInt(ft) || !isInt(st)) return false;
		}
		return true;
	}
	
	boolean isInt(String str){
		return (Pattern.compile("^(-|)+[0-9]+$").matcher(str)).find(); // regular expression
	}
	
	ArrayList optimize(ArrayList tokens){
		if(tokens.size()==1) return tokens;
		
		ArrayList newTokens = new ArrayList();
		
		String ft = (String)tokens.get(0);
		int fv = Integer.parseInt(ft);
		
		String st = (String)tokens.get(2);
		int sv = Integer.parseInt(st);
		
		newTokens.add(fv+sv);
		return newTokens;
	}
}
