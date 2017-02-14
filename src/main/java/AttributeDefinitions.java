import java.util.Hashtable;


public class AttributeDefinitions {

	Hashtable<String, String> colourTable = new Hashtable<String, String>();
	Hashtable<String, String> sizeTable = new Hashtable<String, String>();
	
	   public void initializeColours() {
	      // Create a hash map for colours
	      colourTable.put("red", "1.0 0.0 0.0");
	      colourTable.put("green", "0.0 1.0 0.0");
	      colourTable.put("blue", "0.0 0.0 1.0");
	      colourTable.put("brown", "0.6 0.35 0.0");
	      colourTable.put("black", "0.0 0.0 0.0");
	      colourTable.put("white", "1.0 1.0 1.0");
	   }
	   
	   public void initializeSizes(String shape) {
		   
		   if (shape.equalsIgnoreCase("box")){
			   sizeTable.put("small", "size 1.0 1.0 1.0");
			   sizeTable.put("regular", "size 2.0 2.0 2.0");
			   sizeTable.put("large", "size 4.0 4.0 4.0");
		   }
		   if (shape.equalsIgnoreCase("sphere")){
			   sizeTable.put("small", "radius 0.5");
			   sizeTable.put("regular", "radius 1.0");
			   sizeTable.put("large", "radius 2.0");
		   }
		   if (shape.equalsIgnoreCase("cone")){
			   sizeTable.put("small", " bottomRadius 0.5 "+ "height 1.0 "+ "side TRUE "+ "bottom TRUE ");
			   sizeTable.put("regular", " bottomRadius 1.0 "+ "height 2.0 "+ "side TRUE "+ "bottom TRUE ");
			   sizeTable.put("large", " bottomRadius 2.0 "+ "height 4.0 "+ "side TRUE "+ "bottom TRUE ");
		   }
		   if (shape.equalsIgnoreCase("cylinder")){
			   sizeTable.put("small", " radius 0.5 " + "height 1.0 " + "side TRUE "+ "bottom TRUE " + "top TRUE ");
			   sizeTable.put("regular", " radius 1.0 " + "height 2.0 " + "side TRUE "+ "bottom TRUE " + "top TRUE ");
			   sizeTable.put("large", " radius 2.0 " + "height 4.0 " + "side TRUE "+ "bottom TRUE " + "top TRUE ");
		   }
/*		   if (shape.equalsIgnoreCase("table")){
			   sizeTable.put("small", "1.0 0.0 0.0");
			   sizeTable.put("regular", "0.0 1.0 0.0");
			   sizeTable.put("large", "0.0 0.0 1.0");
		   }
		   if (shape.equalsIgnoreCase("chair")){
			   sizeTable.put("small", "1.0 0.0 0.0");
			   sizeTable.put("regular", "0.0 1.0 0.0");
			   sizeTable.put("large", "0.0 0.0 1.0");

		   }*/

	   }
}
