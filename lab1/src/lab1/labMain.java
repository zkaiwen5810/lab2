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
		System.out.println("2:展示有向图");
		System.out.println("3：查询桥接词");
		System.out.println("4：根据桥接词生成新文本");
		System.out.println("5：计算最短路径");
		System.out.println("6：随机游走");
		System.out.println("请输入指令数字：");
		String w1,w2;
		String newText;
		int order = console.nextInt();

		while (order !=0) {
			if (order == 2)
				gh.showGraph();
			else if (order == 3) {
				
				System.out.println("输入第一个单词：");
				w1 = console.next();
				System.out.println("输入第二个单词：");
				w2 = console.next();
				gh.queryBridgeWords(w1, w2);
			}
			else if (order == 4) {
				
				System.out.print("输入一串文本：");
				newText = console.nextLine();
				newText = console.nextLine();
				gh.generateNewText(newText);
			}
			else if (order == 5) {
			//	String w1,w2;
				System.out.println("输入第一个单词：");
				w1 = console.next();
				System.out.println("输入第二个单词：");
				w2 = console.next();
				System.out.println(gh.calcShortestPath2(w1,w2));
				//gh.calcShortestPath1(w1,w2);
			}
			else if(order == 6)
				gh.randomWalk();
			System.out.println("2:展示有向图");
			System.out.println("3：查询桥接词");
			System.out.println("4：根据桥接词生成新文本");
			System.out.println("5：计算最短路径");
			System.out.println("6：随机游走");
			System.out.println("请输入指令数字：");
			order = console.nextInt();
		}
		console.close();
	}	

}
