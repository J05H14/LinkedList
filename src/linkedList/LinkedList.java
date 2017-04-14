package linkedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class LinkedList <E1, E2, E3> {
	protected Node <E1, E2, E3> head;
	private int size = 0;
	private String cat1Label;
	private String cat2Label;
	private String cat3Label;
	private int groupingCategory = 1;

	public LinkedList(){
		this.head = null;
		this.cat1Label = null;
		this.cat2Label = null;
		this.cat3Label = null;
	}

	public LinkedList(Integer groupingCategory){
		this.groupingCategory = groupingCategory;
		this.head = null;
		this.cat1Label = null;
		this.cat2Label = null;
		this.cat3Label = null;
	}

	@SuppressWarnings("unchecked")
	public LinkedList(File file, int groupingCategory){
		this.groupingCategory = groupingCategory;

		try{
			Scanner scan = new Scanner(file);

			this.cat1Label = scan.nextLine();
			System.out.println(cat1Label);
			this.cat2Label = scan.nextLine();
			System.out.println(cat2Label);
			this.cat3Label = scan.nextLine();
			System.out.println(cat3Label);

			scan.nextLine();
			while(scan.hasNextLine()){
				String[] elements = scan.nextLine().split(", ");
				//System.out.println(Arrays.toString(elements));
				add((E1) elements[0], (E2) elements[1], (E3) elements[2]);
			}

			scan.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}


	}
	public void add(E1 value1, E2 value2, E3 value3){
		Node <E1, E2, E3> newNode = new Node<E1, E2, E3>(value1, value2, value3);
		//System.out.println("new node: " + newNode); 
		boolean isMatch = false;

		//System.out.println(this.size);
		if(this.size == 0){
			this.head = newNode;
		}
		else{
			Node<E1, E2, E3> currNode = head;
			//GROUP 1
			if(this.groupingCategory == 1){
				while(currNode.right != null){
					//System.out.println("curr node: " + currNode);
					Node<E1, E2, E3> currSubNode = currNode;

					if(currNode.category1.equals(value1)){
						while(currSubNode.down != null){
							currSubNode = currSubNode.down;
						}
						currSubNode.down = newNode;
						isMatch = true;
					}
					//System.out.println("sub node: " + currSubNode);
					currNode = currNode.right;		
				}

				if(currNode.category1.equals(value1)){
					Node<E1, E2, E3> lastSubNode = currNode;
					while(lastSubNode.down != null){
						lastSubNode = lastSubNode.down;
					}
					lastSubNode.down = newNode;
				}

				else if(!isMatch){
					currNode.right = newNode;
					newNode.left = currNode;
				}

			}
			//GROUP 2
			if(this.groupingCategory == 2){
				while(currNode.right != null){
					//System.out.println("curr node: " + currNode);
					Node<E1, E2, E3> currSubNode = currNode;

					System.out.println(currNode.category2 + "__" + value2 + "__" + currNode.category2.equals(value2));
					if(currNode.category2.equals(value2)){
						
						while(currSubNode.down != null){
							currSubNode = currSubNode.down;
						}
						currSubNode.down = newNode;
						isMatch = true;
					}
					//System.out.println("sub node: " + currSubNode);
					currNode = currNode.right;		
				}
				//System.out.println(currNode + " __ " + newNode);
				if(currNode.category2.equals(value2)){
					Node<E1, E2, E3> lastSubNode = currNode;
					while(lastSubNode.down != null){
						lastSubNode = lastSubNode.down;
					}
					lastSubNode.down = newNode;
				}

				else if(!isMatch){
					//System.out.println("add to right");
					currNode.right = newNode;
					newNode.left = currNode;
				}

			}

			//GROUP 3
			if(this.groupingCategory == 3){
				while(currNode.right != null){
					//System.out.println("curr node: " + currNode);
					Node<E1, E2, E3> currSubNode = currNode;

					if(currNode.category3.equals(value3)){
						while(currSubNode.down != null){
							currSubNode = currSubNode.down;
						}
						currSubNode.down = newNode;
						isMatch = true;
					}
					//System.out.println("sub node: " + currSubNode);
					currNode = currNode.right;		
				}
				//System.out.println(newNode + " __ " + currNode);
				if(currNode.category3.equals(value3)){

					Node<E1, E2, E3> lastSubNode = currNode;
					System.out.println(lastSubNode);
					while(lastSubNode.down != null){
						lastSubNode = lastSubNode.down;
					}
					lastSubNode.down = newNode;
				}

				else if(!isMatch){
					currNode.right = newNode;
					newNode.left = currNode;
				}

			}	
		}
		//System.out.println(newNode);

		//System.out.println(this.size);
		this.size++;

	}
	public void deleteFirst(){
		if(size == 0){
			System.out.println("This List Is Empty");
		}
		else{
			if(head.down != null){
				Node<E1, E2, E3> temp = this.head.right;
				head = head.down;
				head.right = temp;
			}
			else{
				this.head = this.head.right;
			}
			size--;
		}
	}

	public void deleteLast(){
		if(this.size == 0){
			System.out.println("This List Is Empty");
		}
		else{
			Node<E1, E2, E3> tail = head;
			while(tail.right != null){
				tail = tail.right;
			}
			Node<E1, E2, E3> befTail = tail.left;
			if(tail.down != null){
				Node <E1, E2, E3> temp = tail.left;
				tail = tail.down;
				tail.left = temp;
			}
			else{
				befTail.right = null;
			}
			this.size--;
		}
	}
	public void delete(int main, int sub){
		if(this.size == 0){
			System.out.println("EMPTY");
		}
		else if(main == 0 && sub ==0){
			deleteFirst();
		}
		else if(main == size() - 1 && sub ==0){
			deleteLast();
		}

		else{
			Node<E1, E2, E3> current = head;
			Node<E1, E2, E3> prev = head;
			for(int i = 0; i < main; i++){
				current = current.right;
			}
			for(int i = 1; i < main; i++){
				prev = prev.right;
			}
			//if there is a sublist
			if(current.down != null){
				prev = current;
				for(int i = 0; i < sub; i++){
					current = current.down;
				}
				for(int i = 1; i < sub; i++){
					prev = prev.down;
				}
				//if deleting last entry of sublist
				if(current.down == null){
					prev.down = null;
				}
				//iff deleting middle of sublist
				else{
					prev.down = prev.down.down;
				}
			}
			//if there is no sublist
			else{
				prev.right = prev.right.right;
			}
			size--;
		}
	}


	public int size(){
		int mainSize = 0;
		Node<E1, E2, E3> curr = this.head;

		while(curr != null){
			mainSize++;
			curr = curr.right;
		}
		return mainSize;
	}

	public int size(int index){
		int subSize = 0;
		Node<E1, E2, E3> curr = this.head;

		if(index > size() - 1){
			throw new IndexOutOfBoundsException("The List Isn't that Big. Size: " + size() + " Index: " + index);
		}
		else{
			for(int i = 0; i < index; i++){
				curr = curr.right;
			}
			while(curr != null){
				subSize++;
				curr = curr.down;
			}
		}
		return subSize;
	}

	public void clear(){
		head = null;
	}

	public String get(int mainIndex, int category){
		if(mainIndex > size() - 1){
			throw new IndexOutOfBoundsException("The List Isn't that Big. Size: " + size() + " Index: " + mainIndex);
		}
		else{
			Node<E1, E2, E3> retNode = this.head;
			for(int i = 0; i < mainIndex; i++){
				retNode = retNode.right;
			}
			if(category == 1){
				return cat1Label + ": " + retNode.category1;
			}
			else if(category == 2){
				return cat2Label + ": " + retNode.category2;
			}
			else if(category == 3){
				return cat3Label + ": " + retNode.category3;
			}
			else{
				throw new IndexOutOfBoundsException("Grouping Category must be 1, 2 or 3. input:" + category);
			}
		}
	}

	public String get(int mainIndex, int subIndex, int category){
		if(mainIndex > size() - 1){
			throw new IndexOutOfBoundsException("The List Isn't that Big. Size: " + size() + " Index: " + mainIndex);
		}
		if(subIndex > size(mainIndex) - 1){
			throw new IndexOutOfBoundsException("The Sub List Isn't the Big. Size: " + size(mainIndex) + " Index: " +subIndex);
		}
		else{
			Node<E1, E2, E3> retNode = head;

			for(int i = 0; i < mainIndex; i++){
				retNode = retNode.right;
			}
			for(int i = 0; i < subIndex; i++){
				retNode = retNode.down;
			}

			if(category == 1){
				return cat1Label + ": " + retNode.category1;
			}
			else if(category == 2){
				return cat2Label + ": " + retNode.category2;
			}
			else if(category == 3){
				return cat3Label + ": " + retNode.category3;
			}
			else{
				throw new IndexOutOfBoundsException("Grouping Category must be 1, 2 or 3. input:" + category);
			}
		}
	}

	public void regroup(int newGroupingCategory){
		if(newGroupingCategory > 3 || newGroupingCategory < 1){
			throw new IndexOutOfBoundsException("Grouping Category must be 1, 2 or 3. input:" + newGroupingCategory);
		}
		else{
			ArrayList<Node<E1, E2, E3>> allNodes = new ArrayList<Node<E1, E2, E3>>();
			Node <E1, E2, E3> currNode = head;
			this.groupingCategory = newGroupingCategory;
			this.size = 0;

			while(currNode != null){
				Node<E1, E2, E3> subCurrNode = currNode;
				while(subCurrNode != null){
					allNodes.add(subCurrNode);
					subCurrNode = subCurrNode.down;
				}
				currNode = currNode.right;
			}
			for(int i = 0; i < allNodes.size(); i++){
				allNodes.get(i).right = null;
				allNodes.get(i).left = null;
				allNodes.get(i).down = null;
			}

			System.out.println("array list: " + allNodes);

			for(int i = 0; i < allNodes.size(); i++){
				add(allNodes.get(i).category1, allNodes.get(i).category2, allNodes.get(i).category3);
			}
		}
	}

	public String toString(){
		Node<E1, E2, E3> current = head;
		String output = "[";

		while(current != null){
			output += current + "; ";
			current = current.right;
		}
		output += "\n";
		current = head;

		while(current != null){
			Node<E1, E2, E3> innerCurrent = current;
			while(innerCurrent != null){
				output += innerCurrent + ", ";
				innerCurrent = innerCurrent.down;
			}
			output += "\n";
			current = current.right;
		}

		output += "]";

		return output;
	}
}
