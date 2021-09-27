import java.util.ArrayList;
import java.util.List;

public class TestIDedLinkedList {

	public static void main(String[] args) {

		IDedLinkedList<MyItem> LL = new IDedLinkedList<>();
		List<Integer> name = new ArrayList<>();
		name.add(3); name.add(4); name.add(5);
		MyItem new_item = new MyItem(1,2,name);
		boolean result = LL.insertAtFront(new_item);
		result = LL.insertAtFront(new_item);
		
		
		System.out.print(result);
		
	}
}
