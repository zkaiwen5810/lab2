package lab1;

public class Vertice {
	private String word;
	private Vertice next,ajen;
	private int weight;
	private int rank;
	private int d;
	private Vertice pi;
	public Vertice() {
		word = "";
		next = null;
		ajen = null;
		weight = 0;
		d = 10000000;
		pi = null;
	}
	public Vertice(Vertice v) {
		this.word = v.getWord();
		this.weight = 0;
	}
	public Vertice(String word,Vertice next,Vertice ajen) {
		this.word = word;
		this.next = next;
		this.ajen = ajen;
		this.weight = 0;
	}
	public String getWord() {
		return this.word;
	}
	public Vertice getNext() {
		return this.next;
	}
	public Vertice getAjen() {
		return this.ajen;
	}
	public int getWeight() {
		return this.weight;
	}
	public int getRank() {
		return this.rank;
	}
	public int getD() {
		return this.d;
	}
	public Vertice getPi() {
		return this.pi;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public void setNext(Vertice next) {
		this.next = next;
	}
	public void setAjen(Vertice ajen) {
		this.ajen = ajen;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setD(int d) {
		this.d = d;
	}
	public void setPi(Vertice pi) {
		this.pi = pi;
	}
}
