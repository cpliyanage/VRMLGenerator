import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;


public class ObjectIdentifier {
	
	CodeGenerator codeGenerator= new CodeGenerator();
	String[] colours = { "red", "green", "blue","brown","black", "white"};
	static PrintWriter writer;
	
	public void defineObject(ArrayList<VRMLObject> vrmlObjects) throws IOException{
		writer= new PrintWriter("new6.wrl", "UTF-8");
		writer.println("#VRML V2.0 utf8");
		
		for(VRMLObject obj:vrmlObjects){
			String colour="black";
			for(String a:obj.attributes){
				if(Arrays.asList(colours).contains(a)){
					colour=a;
				}
			}
			if(obj.name.equalsIgnoreCase("table")){
				System.out.println("Object table present");
				codeGenerator.drawTable(colour);
			} else if (obj.name.equalsIgnoreCase("box")){
				System.out.println("Object box present");
				codeGenerator.drawBox(colour);
			}
			else if(obj.name.equalsIgnoreCase("sphere")){
				System.out.println("Object sphere present");
				codeGenerator.drawSphere(colour);				
			}
			else if(obj.name.equalsIgnoreCase("cone")){
				System.out.println("Object cone present");
				codeGenerator.drawCone(colour);
			}
			else if(obj.name.equalsIgnoreCase("cylinder")){
				System.out.println("Object cylinder present");
				codeGenerator.drawCylinder(colour);
			}else if(obj.name.equalsIgnoreCase("chair")){
				System.out.println("Object chair present");
				codeGenerator.drawChair(colour);
			}
		}
		
		writer.close();
		System.out.println("Successfully created file!");
	}
}
