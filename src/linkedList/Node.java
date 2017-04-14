package linkedList;

public class Node <E1, E2, E3> {
	protected E1 category1;
	protected E2 category2;
	protected E3 category3;
	
	protected Node<E1, E2, E3> left = null;
	protected Node<E1, E2, E3> right = null;
	protected Node<E1, E2, E3> down = null;
	
	public Node(E1 category1, E2 category2, E3 category3){
		this.category1 = category1;
		this.category2 = category2;
		this.category3 = category3;
	}
	
	public String toString(){
		return this.category1 + ", " + this.category2 + ", " + this.category3;
	}
	
	public E1 getCategory1() {
		return category1;
	}
	public void setCategory1(E1 category1) {
		this.category1 = category1;
	}
	public E2 getCategory2() {
		return category2;
	}
	public void setCategory2(E2 category2) {
		this.category2 = category2;
	}
	public E3 getCategory3() {
		return category3;
	}
	public void setCategory3(E3 category3) {
		this.category3 = category3;
	}
}
