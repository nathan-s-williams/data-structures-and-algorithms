import java.util.ArrayList;
import java.util.List;

public class TestMyItem {

	public static void main(String[] args) {
		List<Integer> arr = new ArrayList<>();
		arr.add(3);
		arr.add(4);
		arr.add(5);
		MyItem mi = new MyItem(1, 2, arr);
	
		System.out.println(mi.printID());
		System.out.println(mi.getID());
		System.out.println(mi.getPrice());
		System.out.println(mi.getItemDescription().toString());
		
		
		mi.setID(6);
		mi.setPrice(7);
		mi.addItemDescription(8);
		System.out.println(mi.printID());
		
		mi.deleteItemDescription(5);
		System.out.println(mi.printID());

	}

}
