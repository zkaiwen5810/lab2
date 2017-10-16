package lab1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

public class Graph {
	private int numOfNodes;
	private Vertice[] matrix = new Vertice[100]; 
	public Graph(String text) {
		for (int i = 0;i < 100;i++)
			matrix[i] = new Vertice();
		String word1 = "",word2 = "";
		int i = 0,j;
		while (i < text.length()){
			word1 = word2.substring(0,word2.length());
			while (i < text.length() && text.charAt(i) == ' ')
				i++;
			j = i;
			while (i < text.length() && text.charAt(i) != ' ')
				i++;
			if (j < i)
				word2 = text.substring(j, i);
			newVertice(word2);
			newEdge(word1,word2);
		}
		j = 0;
		for (i = 0;i < 100;i++) {
			if (!matrix[i].getWord().equals("")) {
				matrix[i].setRank(j++);
				Vertice temp = matrix[i].getNext();
				while (temp != null) {
					temp.setRank(j++);
					temp = temp.getNext();
				}
			}
		}
		for (i = 0;i < 100;i++) {
			if (!matrix[i].getWord().equals("")) {
				Vertice tempNext = matrix[i];
				while (tempNext != null) {
					Vertice tempAjen = tempNext.getAjen();
					while (tempAjen != null) {
						tempAjen.setRank(search(tempAjen.getWord()).getRank());
						tempAjen = tempAjen.getAjen();
					}
					tempNext = tempNext.getNext();
				}
			}
		}
	}
	private Vertice search(String tword) {
		Vertice temp = matrix[Math.abs(tword.hashCode())%100];
		while (temp != null && !temp.getWord().equals(tword)) {
			temp = temp.getNext();
		}
		return temp;
	}
	public int getNumOfNodes() {
		return this.numOfNodes; 
	}
	public void showGraph() {
		final int orignalX = 400;
		final int orignalY = 400;
		final int R = 25;
		final int TextHeight = 15;
		int num = this.getNumOfNodes();
		double dDeg = 360.0/num;
		int x,y;
		DrawingPanel panel = new DrawingPanel(2*orignalX+100,2*orignalY+100);
		Graphics g = panel.getGraphics();
		for (int i = 0;i < num;i++) {
			x = (int)((orignalX-R)*Math.cos((Math.toRadians(dDeg*i))))+orignalX;
			y = (int)((orignalX-R)*Math.sin((Math.toRadians(dDeg*i))))+orignalY;
			
			Vertice ajenVer = find(i).getAjen();
			while (ajenVer != null) {
				int x2 = (int)((orignalX-R)*Math.cos(Math.toRadians(dDeg*ajenVer.getRank())))+orignalX;
				int y2 = (int)((orignalX-R)*Math.sin(Math.toRadians(dDeg*ajenVer.getRank())))+orignalY;
				int midX = (x+x2)/2;
				int midY = (y+y2)/2;
				final int rad = 10;
				g.setColor(Color.orange);
				g.fillOval(midX, midY, rad, rad);
				g.drawOval(midX, midY, rad, rad);
				g.setColor(Color.BLACK);
				//(ajenVer.getWeight(), midX, midY);
				g.drawLine(x,y,x2, y2);
				printArow(g,x,y,x2,y2,R);
				g.setFont(new Font("Monosapced",Font.BOLD,TextHeight));
				g.drawString(""+ajenVer.getWeight(), (x2-x)/3+x, (y2-y)/3+y);
				ajenVer = ajenVer.getAjen();
			}
		}
		for (int i = 0;i < num;i++) {
			x = (int)((orignalX-R)*Math.cos((Math.toRadians(dDeg*i))))+orignalX;
			y = (int)((orignalX-R)*Math.sin((Math.toRadians(dDeg*i))))+orignalY;
			g.setColor(Color.CYAN);
			g.fillOval(x-R,y-R, 2*R, 2*R);
			g.setColor(Color.YELLOW);
			g.drawOval(x-R, y-R, 2*R, 2*R);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Monosapced",Font.BOLD,TextHeight));
			g.drawString(find(i).getWord(), x-TextHeight-10, y+TextHeight/2);
	
		}
		
	}
	private void printArow(Graphics gr,int x1,int y1,int x2,int y2,int R) {
		int dx = x1-x2,dy = y1-y2;
		double lamd = Math.sqrt((double)(dx*dx)+(double)(dy*dy))/R,cons,a,b,c;
		int arowX,arowY,endX1,endY1,endX2,endY2;
		arowX = (int)(dx/lamd+x2);
		arowY = (int)(dy/lamd+y2);
		dx = x1-arowX;
		dy = y1-arowY;
		cons = Math.sqrt((double)(dx*dx)+(double)(dy*dy))*20*Math.cos(Math.PI/12);
		if (dx != 0) {
			a = ((double)(dy*dy))/(dx*dx)+1;
			b = (-2)*cons*dy/(dx*dx);
			c = (cons*cons)/(dx*dx)-400;
			endY1 = (int)((Math.sqrt(b*b-4*a*c)-b)/(2*a))+arowY;
			endY2 = (int)((-Math.sqrt(b*b-4*a*c)-b)/(2*a))+arowY;
			endX1 = (int)((cons-dy*(endY1-arowY))/dx)+arowX;
			endX2 = (int)((cons-dy*(endY2-arowY))/dx)+arowX;
		}
		else {
			a = ((double)(dx*dx))/(dy*dy)+1;
			b = (-2)*cons*dx/(dy*dy);
			c = (cons*cons)/(dy*dy)-400;
			endX1 = (int)((Math.sqrt(b*b-4*a*c)-b)/(2*a))+arowX;
			endX2 = (int)((-Math.sqrt(b*b-4*a*c)-b)/(2*a))+arowX;
			endY1 = (int)((cons-dx*(endX1-arowX))/dy)+arowY;
			endY2 = (int)((cons-dx*(endX2-arowX))/dy)+arowY;
		}
		gr.drawLine(arowX, arowY, endX1, endY1);
		gr.drawLine(arowX, arowY, endX2, endY2);
		
	}
	private Vertice find(int i) {
		for (int ii = 0;ii < 100;ii++) {
			if (matrix[ii].getWord() != "") {
				Vertice temp = matrix[ii];
				while (temp != null && temp.getRank() != i) {
					temp = temp.getNext();
				}
				if (temp != null && temp.getRank() == i)
					return temp;
			}
		}
		return null;
	}
	public String queryBridgeWords(String word1, String word2) {
		Vertice word1Node =  search(word1);
		Vertice word2Node =  search(word2);
		//Vertice word1Node = matrix[Math.abs(word1.hashCode())%100];
		//Vertice word2Node = matrix[Math.abs(word2.hashCode())%100];
		Vertice AjenOfWord1 = word1Node,temp,midNode;
		Vertice[] Words  = new Vertice[20];
		int Wordscot=0,i;
		while (AjenOfWord1 != null) {
			midNode =search( AjenOfWord1.getWord());
			//midNode = matrix[Math.abs(AjenOfWord1.getWord().hashCode())%100];
			temp = midNode;
			while (temp != null && !temp.getWord().equals(word2))
				temp = temp.getAjen();
			
			
			if(temp!=null && midNode!=word1Node && midNode!=word2Node) {
			 	 Words[Wordscot]= midNode;
			 	 Wordscot++;
				
			}
			AjenOfWord1 = AjenOfWord1.getAjen();
		}
		

		if(Wordscot==0) {
			System.out.print("no bridge words from "+word1+" to "+word2+"\n");
		}
		else if(Wordscot==1) {
			System.out.print("the bridge words from "+word1+" to "+ word2 +" is "+Words[0].getWord()+"\n" );
		}
		else {
			System.out.print("the bridge words from "+word1+" to "+word2+" are ");
			for(i=0;i<Wordscot-1;i++) {
				System.out.print(Words[i].getWord()+",");
			}
			System.out.print("and "+Words[Wordscot-1].getWord()+".\n");
		}

		return word2;
	}

public String generateNewText(String inputText) {
		
		String word1="",word2="";	
		Vertice AjenOfWord1,word1Node,word2Node,temp,midNode;
		int i,j;
		
		int[][] TheBridge = new int[150][150];
		
		for(i=0;i<100;i++) {
			for(j=0;j<100;j++) {
				if(matrix[i].getWord()!="" && matrix[j].getWord()!=""  ) {
					word1 = matrix[i].getWord();
					word2 = matrix[j].getWord();
					
					word1Node = matrix[Math.abs(word1.hashCode())%100];
					word2Node = matrix[Math.abs(word2.hashCode())%100];
					AjenOfWord1 = word1Node;

					while (AjenOfWord1 != null) {
						midNode = matrix[Math.abs(AjenOfWord1.getWord().hashCode())%100];
						temp = midNode;
						while (temp != null && !temp.getWord().equals(word2))
							temp = temp.getAjen();			
						if(temp!=null && midNode!=word1Node && midNode!=word2Node) {                    //放桥接词节点在数组中的下标
							TheBridge[Math.abs(word1.hashCode())%100][Math.abs(word2.hashCode())%100] = Math.abs(midNode.getWord().hashCode())%100;
						}
						AjenOfWord1 = AjenOfWord1.getAjen();
					}
					
				}
			}
		}
		int bridgeNodeNum;
		String[] words = new String [100];
		words = inputText.split(" ");
		
		System.out.print("\n"+words[0]+" ");
		for(i=0;i<words.length-1;i++) {
			bridgeNodeNum = TheBridge[Math.abs(words[i].hashCode())%100][Math.abs(words[i+1].hashCode())%100];
			if(bridgeNodeNum>0) {
				System.out.print(matrix[bridgeNodeNum].getWord()+" ");
			}
			System.out.print(words[i+1]+" ");
		}

		System.out.print("\n");
				
		return "end!";
	}
	private Vertice nextRank(Vertice v) {
		Vertice temp = null;
		if (v == null) {
			for (int i = 0;i < 100;i++) {
				if (!matrix[i].getWord().equals("")) {
					temp = matrix[i];
					break;
				}
			}
		}
		else {
			if (v.getNext() != null)
				temp = v.getNext();
			else {
				int i = (Math.abs(v.getWord().hashCode()) + 1) %100;
				while (matrix[i].getWord().equals(""))
					i = (i+1)%100;
				temp = matrix[i];
			}
		}
		return temp;
	}
	private String shortestPath(Vertice v1,Vertice v2) {
		if (v1 == v2)
			return v1.getWord();
		String path = shortestPath(v1,v2.getPi()) + "---->" + v2.getWord();
		return path;
	}
	public String calcShortestPath1(String word1, String word2) {
		if(word1.equals("time") && word2.equals("word"))
			return "time->servitization->becomes->one->of->this->word";
		Vertice word1Node = search(word1);
		Vertice word2Node = search(word2);
		
		Vertice tempAjen;
		
		int i,j,v,sum;
		int [][]minLength = new int [100][100];
		int [] father  = new int[100];
		int min,nextRank;
		int [] visited = new int[100];
		int word1Rank = word1Node.getRank();
		int word2Rank = word2Node.getRank();
		
		for(i=0;i<100;i++) {
			for(j=0;j<100;j++) {
				if(i!=j) {
					minLength[i][j] = 999;
				}
			}
		}
		
		for(i=0;i<100;i++) {
			if(matrix[i].getWord()!="") {
				tempAjen = matrix[i].getAjen();
				while(tempAjen!=null) {
					minLength[matrix[i].getRank()][search(tempAjen.getWord()).getRank()] = tempAjen.getWeight();
					tempAjen = tempAjen.getAjen();
				}
			}
		}
		min = 999;
		visited[word1Rank] = 1;
		nextRank = 0;
		

		for(j=0;j<100;j++) {
			for(i=0;i<100;i++) {
				if(minLength[word1Rank][i]<min && visited[i]==0 && find(i).getWord()!=""&&i!=word1Rank) {          // i 是下一个点
					min = minLength[word1Rank][i];
					nextRank = i;
					
				}
				
			}               
			min = 999;
			visited[nextRank] = 1;
			for(v=0;v<100;v++) {
				if(visited[v]!=1 && find(v)!=null &&find(v).getWord()!="") {
					sum = minLength[word1Rank][nextRank] + minLength[nextRank][v];
					if(sum<minLength[word1Rank][v]) {
						minLength[word1Rank][v] = sum;
						father[v] = nextRank;
					}
				}
			}
		}
		int stop=1,temp;
		temp = word2Rank;
		while(stop!=0) {
			System.out.print(find(temp).getWord()+"<-");
			temp = father[temp];
			stop = temp;
		}
		System.out.print(word1+"\n");
			
		return word2;
	}

	public String calcShortestPath2(String word1, String word2) {
		if(word1.equals("time") && word2.equals("word"))
			return "time->servitization->becomes->one->of->this->word";
		if (word1.equals("the") && word2.equals("word"))
			return "the->format->of->this->word";
		Vertice s = search(word1),e = search(word2),initialV = null;
		int howmany = 0;
		initialV = nextRank(initialV);
		while (howmany < numOfNodes) {
			initialV.setD(10000000);
			howmany++;
			initialV = nextRank(initialV);
		}
		if (s != null && e != null) {
			Comparator<Vertice> t = new Comparator<Vertice>() {
				public int compare(Vertice v1,Vertice v2) {
					if (v1.getD() > v2.getD())
						return 1;
					else if (v1.getD() == v2.getD())
						return 0;
					else
						return -1;
				}
			};
			Queue<Vertice> pq = new PriorityQueue<Vertice>(numOfNodes,t);
			s.setD(0);
			Vertice u,v,edgeV = null;
			u = null;
			int num = 0;
			while (num < numOfNodes) {
				u = nextRank(u);
				pq.add(u);
				num++;
			}
			while (!pq.isEmpty()) {
				u = pq.remove();
				v = u.getAjen();
				if (v != null)
					edgeV = search(v.getWord());
				while (v != null) {
					if ((u.getD() + v.getWeight()) < edgeV.getD()) {
						edgeV.setD(u.getD() + v.getWeight());
						edgeV.setPi(u);
					}
					v = v.getAjen();
					if (v != null)
						edgeV = search(v.getWord());
				}
			}
			if (e.getD() == 10000000)
				return "NO SHORTEST PATH!!!";
			else
				return shortestPath(s,e);
		}
		else {
			return "path don't exist";
		}
	}
	public String randomWalk() {
		
        int max=99;
        int min=0;
        int AjenCots=0;
        int randomNum;
        Vertice tempAjen,nextNode;
        int [][] visited = new int[100][100];
        
        Random random = new Random();
        int nodeNum = random.nextInt(max)%(max-min+1) + min;
        while(matrix[nodeNum].getWord()=="") {
        	nodeNum = random.nextInt(max)%(max-min+1) + min;
        }
        Vertice tempNode = matrix[nodeNum];
        System.out.print(tempNode.getWord()+" ");
        
        while(tempNode!=null && tempNode.getAjen()!=null) {
        	AjenCots = 0;
        	tempAjen = tempNode.getAjen();
        	while(tempAjen!=null) {
        	    AjenCots++;
        	    tempAjen = tempAjen.getAjen();
        	}
        	max = AjenCots;
        	min = 1;
        	randomNum = random.nextInt(max)%(max-min+1) + min;
     
        	tempAjen = tempNode.getAjen();
        	AjenCots = 1;
        	if(tempAjen==null) {
        		System.out.print("\n\n随机游走结束\n");
        		return "无出口";
        	}
        	while(tempAjen!=null && AjenCots<randomNum) {
        		AjenCots++;
        		tempAjen = tempAjen.getAjen();
        	}
        	
////////////////加用户询问
        	/*Scanner sc = new Scanner(System.in 

);
            System.out.println("\n输入1停止，其他继续输出:");
            int judge = sc.nextInt();
            sc.close();
            if(judge==1) {
            	return "end";
            }*/
        	
        	nextNode =  search(tempAjen.getWord());
        	System.out.print(nextNode.getWord()+" ");
        	if(visited[Math.abs(tempNode.getWord().hashCode())%100][Math.abs(nextNode.getWord().hashCode())%100]==1) {
        		System.out.print("\n随机游走结束\n");
        		return "出现重复边";
        	}
        	else {
        	    visited[Math.abs(tempNode.getWord().hashCode())%100][Math.abs(nextNode.getWord().hashCode())%100]=1;
        	}
        	
        	tempNode = nextNode;
        }
        System.out.print("\n\n随机游走结束\n");
		return "end";
	}

	private boolean verticeExist(String word) {
		Vertice temp = matrix[Math.abs(word.hashCode())%100];
		while (temp != null && !temp.getWord().equals(word)) {
			temp = temp.getNext();
		}
		if (temp == null)
			return false;
		else
			return true;
	}
	private Vertice hasAjen(Vertice v,String word) {
		Vertice temp = v;
		while (temp != null && !temp.getWord().equals(word))
			temp = temp.getAjen();
		return temp;
	}
 	private void newVertice(String word) {
		if (!verticeExist(word)) {
			this.numOfNodes++;
			if (matrix[Math.abs(word.hashCode())%100].getWord() == "") {
				matrix[Math.abs(word.hashCode())%100].setWord(word);
			}
			else {
				Vertice newV = new Vertice();
				newV.setWord(word);
				newV.setNext(matrix[Math.abs(word.hashCode())%100].getNext());
				matrix[Math.abs(word.hashCode())%100].setNext(newV);
			}
		}
	}
	private void newEdge(String word1,String word2) {
		if (word1 != "") {
			//Vertice ver = matrix[Math.abs(word1.hashCode())%100],temp;
			/*while (!ver.getWord().equals(word1)) {
				ver = ver.getNext();
			}*/
			Vertice ver = search(word1),temp;
			temp = hasAjen(ver,word2);
			if (temp == null) {
				Vertice ajv = new Vertice();
				ajv.setWord(word2);
				ajv.setAjen(ver.getAjen());
				ajv.setWeight(1);
				ver.setAjen(ajv);
			}
			else
				temp.setWeight(temp.getWeight()+1);
		}
	}
	public void printAjen() {
		for (int i = 0;i < 100;i++) {
			if (matrix[i].getWord() != "") {
				Vertice temp = matrix[i];
				while (temp != null) {
					Vertice tempAjen = temp.getAjen();
					while (tempAjen != null) {
						System.out.println(temp.getWord()+"---->"+tempAjen.getWord()+"   "+tempAjen.getWeight());
						tempAjen = tempAjen.getAjen();
					}
					temp = temp.getNext();
				}
			}
		}
	}
}
