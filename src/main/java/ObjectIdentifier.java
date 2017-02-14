import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjectIdentifier {
	
	CodeGenerator codeGenerator= new CodeGenerator();
	String[] colours = { "red", "green", "blue","brown","black", "white"};
	String[] sizes = {"small","regular","large"};
	static PrintWriter writer;
	
	public void defineObject(ArrayList<VRMLObject> vrmlObjects) throws IOException{
		writer= new PrintWriter("generated.wrl", "UTF-8");
		writer.println("#VRML V2.0 utf8");
		
		for(VRMLObject obj:vrmlObjects){
			String colour="black";
			String size = "regular";
			
			for(String a:obj.attributes){
				if(Arrays.asList(colours).contains(a)){
					colour=a;
				}
				else if(Arrays.asList(sizes).contains(a)){
					size=a;
				}
			}
			if(obj.name.equalsIgnoreCase("table")){
				if(obj.attributes.contains("round")){
					System.out.println("Round table present");
					codeGenerator.drawRoundTable(colour,size);
				}else if(obj.attributes.contains("square")){
					System.out.println("Object table present");
					codeGenerator.drawSquareTable(colour,size);
				}else{
					System.out.println("Object table present");
					codeGenerator.drawRoundTable(colour,size);
				}
			} 
			
			else if (obj.name.equalsIgnoreCase("box")){
				System.out.println("Object box present");
				codeGenerator.drawBox(colour,size);
			}
			else if(obj.name.equalsIgnoreCase("sphere")){
				System.out.println("Object sphere present");
				codeGenerator.drawSphere(colour,size);				
			}
			else if(obj.name.equalsIgnoreCase("cone")){
				System.out.println("Object cone present");
				codeGenerator.drawCone(colour,size);
			}
			else if(obj.name.equalsIgnoreCase("cylinder")){
				System.out.println("Object cylinder present");
				codeGenerator.drawCylinder(colour,size);
			}else if(obj.name.equalsIgnoreCase("chair")){
				System.out.println("Object chair present");
				codeGenerator.drawChair(colour,size);
			}
		}
		
		writer.close();
		System.out.println("Successfully created file!");
	}
}
