import java.util.Hashtable;


public class AttributeDefinitions {

	Hashtable colourTable = new Hashtable();
	
	   public void initializeColours() {
	      // Create a hash map for colours
	      colourTable.put("red", "1.0 0.0 0.0");
	      colourTable.put("green", "0.0 1.0 0.0");
	      colourTable.put("blue", "0.0 0.0 1.0");
	      colourTable.put("brown", "0.6 0.35 0.0");
	      colourTable.put("black", "0.0 0.0 0.0");
	      colourTable.put("white", "1.0 1.0 1.0");
	   }


}
