package lab1;
import java.util.*;
import java.io.*;
//import java.awt.*;

public class labMain {
	public static void main(String[] args) {
		String filePath,text = "",finalTxt = "";
		System.out.println("Please input the file path:");
		Scanner console = new Scanner(System.in);
		filePath = console.nextLine();
		//console.close();
		//System.out.println(filePath);
		File f = new File(filePath);
		try {
			Scanner input = new Scanner(f);
			while (input.hasNextLine()) {
				text += input.nextLine();
			}
			input.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(text);
		text = text.toLowerCase();
		System.out.println(text);
		for (int i = 0;i < text.length();i++) {
			if (text.charAt(i) < 'a' || text.charAt(i) > 'z') {
				finalTxt += " ";
			}else {
				finalTxt += text.charAt(i);
			}
		}
		System.out.println(finalTxt);
		Graph gh = new Graph(finalTxt);
		gh.printAjen();
		System.out.println(gh.getNumOfNodes());
		System.out.println("2:չʾ����ͼ");
		System.out.println("3����ѯ�ŽӴ�");
		System.out.println("4�������ŽӴ��������ı�");
		System.out.println("5���������·��");
		System.out.println("6���������");
		System.out.println("������ָ�����֣�");
		String w1,w2;
		String newText;
		int order = console.nextInt();

		while (order !=0) {
			if (order == 2)
				gh.showGraph();
			else if (order == 3) {
				
				System.out.println("�����һ�����ʣ�");
				w1 = console.next();
				System.out.println("����ڶ������ʣ�");
				w2 = console.next();
				gh.queryBridgeWords(w1, w2);
			}
			else if (order == 4) {
				
				System.out.print("����һ���ı���");
				newText = console.nextLine();
				newText = console.nextLine();
				gh.generateNewText(newText);
			}
			else if (order == 5) {
			//	String w1,w2;
				System.out.println("�����һ�����ʣ�");
				w1 = console.next();
				System.out.println("����ڶ������ʣ�");
				w2 = console.next();
				System.out.println(gh.calcShortestPath2(w1,w2));
				//gh.calcShortestPath1(w1,w2);
			}
			else if(order == 6)
				gh.randomWalk();
			System.out.println("2:չʾ����ͼ");
			System.out.println("3����ѯ�ŽӴ�");
			System.out.println("4�������ŽӴ��������ı�");
			System.out.println("5���������·��");
			System.out.println("6���������");
			System.out.println("������ָ�����֣�");
			order = console.nextInt();
		}
		console.close();
	}	

}