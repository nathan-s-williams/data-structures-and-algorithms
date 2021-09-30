import java.util.ArrayList;
import java.util.List;

public class TestIDedLinkedList {

	public static void main(String[] args) {

		IDedLinkedList<MyItem> LL = new IDedLinkedList<>();
		List<Integer> name = new ArrayList<>();
		List<Integer> name2 = new ArrayList<>();
		name.add(3); name.add(4); name.add(5);
		name2.add(10); name2.add(20); name2.add(30);
		MyItem new_item = new MyItem(1,2,name);
		boolean result = LL.insertAtFront(new_item);
		new_item = new MyItem(6,2,name2);
		result = LL.insertAtFront(new_item);
		//new_item = new MyItem(1,2,name);	//doesn't input bc duplicate
		result = LL.insertAtFront(new_item);
		
		System.out.print(result);
		
		
		MyItem output;
		output = LL.deleteFromFront();
		System.out.println(output.printID());
		
		new_item = new MyItem(7,2,name);
		result = LL.insertAtFront(new_item);
		new_item = new MyItem(8,2,name);
		result = LL.insertAtFront(new_item);
		
		System.out.print(result);
		
		output = LL.delete(7);
		System.out.println(output.printID());
		
		System.out.println(LL.printTotal());
		
		LL.makeEmpty();
		
		System.out.println(LL.printTotal());
		
		
		
		
	}
}
