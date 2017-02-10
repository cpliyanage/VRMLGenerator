import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class CodeGenerator {
	//ObjectIdentifier objectIdentifier= new ObjectIdentifier();
	//Basic shapes
	public void drawBox(String colour) throws IOException{
		AttributeDefinitions attributes= new AttributeDefinitions();
		attributes.initializeColours();
		//PrintWriter writer = new PrintWriter("new5.wrl", "UTF-8");
		//writer.println("#VRML V2.0 utf8");
		ObjectIdentifier.writer.println("Transform {"+
		    "translation 0.0 0.615 0.0"+
		    "children ["+
			"Shape {"+
			    "appearance DEF " +colour+ " Appearance {"+
				"material Material {"+
				    "diffuseColor "+attributes.colourTable.get(colour)+
				" }"+
			    "}"+
			    "geometry Box {"+
				"size 2.0 2.0 2.0"+
			    "}"+
			"}"+
		    "]"+
		"}");
	
	    //writer.close();
		 System.out.println("Box drawn successfully!");	
	}
	
	public void drawSphere(String colour) throws IOException{
		AttributeDefinitions attributes= new AttributeDefinitions();
		attributes.initializeColours();
		//PrintWriter writer = new PrintWriter("new5.wrl", "UTF-8");
		//writer.println("#VRML V2.0 utf8");
		ObjectIdentifier.writer.println("Transform {"+
		    "translation 0.0 0.615 0.0"+
		    "children ["+
			"Shape {"+
			    "appearance DEF " +colour+ " Appearance {"+
				"material Material {"+
				    "diffuseColor "+attributes.colourTable.get(colour)+
				" }"+
			    "}"+
			    "geometry Sphere {"+
				"radius 1.0"+
			    "}"+
			"}"+
		    "]"+
		"}");
	
	    //writer.close();
		 System.out.println("Sphere drawn successfully!");	
	}
	
	public void drawCylinder(String colour) throws IOException{
		AttributeDefinitions attributes= new AttributeDefinitions();
		attributes.initializeColours();
		//PrintWriter writer = new PrintWriter("new5.wrl", "UTF-8");
		//writer.println("#VRML V2.0 utf8");
		ObjectIdentifier.writer.println("Transform {"+
		    "translation 0.0 0.615 0.0"+
		    "children ["+
			"Shape {"+
			    "appearance DEF " +colour+ " Appearance {"+
				"material Material {"+
				    "diffuseColor "+attributes.colourTable.get(colour)+
				" }"+
			    "}"+
			    "geometry Cylinder {"+
			    " radius 1.0 "+
			    "height 2.0 "+
			    "side TRUE "+
			    "bottom TRUE "+
			    "top TRUE "+
			    "}"+
			"}"+
		    "]"+
		"}");
	
	    //writer.close();
		 System.out.println("Cylinder drawn successfully!");	
	}
	
	public void drawCone(String colour) throws IOException{
		AttributeDefinitions attributes= new AttributeDefinitions();
		attributes.initializeColours();
		//PrintWriter writer = new PrintWriter("new5.wrl", "UTF-8");
		//writer.println("#VRML V2.0 utf8");
		ObjectIdentifier.writer.println("Transform {"+
		    "translation 0.0 0.615 0.0"+
		    "children ["+
			"Shape {"+
			    "appearance DEF " +colour+ " Appearance {"+
				"material Material {"+
				    "diffuseColor "+attributes.colourTable.get(colour)+
				" }"+
			    "}"+
			    "geometry Cone {"+
			    " bottomRadius 1.0 "+
			    "height 2.0 "+
			    "side TRUE "+
			    "bottom TRUE "+
			    "}"+
			"}"+
		    "]"+
		"}");
	
	    //writer.close();
		 System.out.println("Cone drawn successfully!!");	
	}
	
	//custom shapes
	public void drawTable(String colour) throws IOException{
		AttributeDefinitions attributes= new AttributeDefinitions();
		attributes.initializeColours();
		//PrintWriter writer = new PrintWriter("new5.wrl", "UTF-8");
		//writer.println("#VRML V2.0 utf8");
		ObjectIdentifier.writer.println("Transform {"+
		    "translation 0.0 0.615 0.0"+
		    "children ["+
			"Shape {"+
			    "appearance DEF " +colour+ " Appearance {"+
				"material Material {"+
				    "diffuseColor "+attributes.colourTable.get(colour)+
				" }"+
			    "}"+
			    "geometry Cylinder {"+
				"radius 0.7"+
				"height 0.03"+
			    "}"+
			"}"+
		    "]"+
		"}"+
		"Transform {"+
		    "translation 0.0 0.3075 0.0"+
		    "children ["+
			"Shape {"+
			    "appearance USE "+ colour+
			   " geometry Box {"+
				"size 0.09 0.57 0.09"+
			    "}"+
			"}"+
		    "]}"+
		"Transform {"+
		    "translation 0.0 0.015 0.0"+
		    "children ["+
			"Shape {"+
			    "appearance USE "+ colour+
			    " geometry Box {"+
				"size 0.5 0.03 0.5"+
			    "}"+
			"}]}"+
		"Transform {"+
		    "translation 0.0 0.045 0.0"+
		    "children ["+
			"Shape {"+
			    "appearance USE " +colour +
			    " geometry Box {"+
				"size 0.35 0.03 0.35" +
			    "}}]}");
	
	    //writer.close();
		System.out.println("Table drawn successfully!");	
	}
	
	public void drawChair(String colour) throws IOException{
		AttributeDefinitions attributes= new AttributeDefinitions();
		attributes.initializeColours();
		//PrintWriter writer = new PrintWriter("new5.wrl", "UTF-8");
		//writer.println("#VRML V2.0 utf8");
		ObjectIdentifier.writer.println("Transform {"+

				    "translation 0.0 0.5 0.0 "+
				    "children [ "+
					"Shape { "+
					    "appearance DEF "+colour+ " Appearance { "+
						"material Material { "+
						    "diffuseColor "+attributes.colourTable.get(colour)+
						" }}"+
					    "geometry Box {"+
						"size 0.39 0.03 0.41 "+
					    "}}]}"+

				"Transform { "+
				    "translation 0.1575 0.2485 0.1575 "+
				    "children [ "+
					"DEF Leg Shape { "+
					    "appearance USE "+colour+
					    " geometry Box {"+
						"size 0.03 0.497 0.03 "+
					    "}}]}"+
				"Transform {"+
				    "translation -0.1575 0.2485 0.1575 "+
				    "children [ USE Leg ] "+
				"} "+
				"Transform { "+
				    "translation -0.1575 0.2485 -0.1575 "+
				    "children [ USE Leg ] "+
				"}"+
				"Transform { "+
				    "translation 0.1575 0.2485 -0.1575 "+
				    "children [ USE Leg ] "+
				"} "+

				"Transform { "+
				    "translation 0.1875 0.5 0.0 "+
				    "rotation 0.0 0.0 1.0 -0.17 "+
				    "children [ "+
					"Transform { "+
					    "translation 0.0 0.54 0.0 "+
					    "children [ "+
						"Shape { "+
						    "appearance USE "+colour+
						    " geometry Box { "+
							"size 0.06 0.17 0.43 "+
						    "}}]}"+

					"Transform {"+
					    "translation 0.0 0.2275 0.0 "+
					    "children [ "+
						"DEF BackPole Shape { "+
						    "appearance USE "+colour +
						    " geometry Box { "+
							"size 0.02 0.455 0.02 "+
						    "}}]}"+
					"Transform { "+
					    "translation 0.0 0.2275 -0.083 "+
					    "children [ USE BackPole ] "+
					"}"+
					"Transform {"+
					    "translation 0.0 0.2275 0.083 "+
					    "children [ USE BackPole ] "+
					"}"+
					"Transform { "+
					    "translation 0.0 0.2275 -0.166 "+
					    "children [ USE BackPole ] "+
					"}"+
					"Transform {"+
					    "translation 0.0 0.2275 0.166"+
					    "children [ USE BackPole ]"+
					"}]}");
	
	    //writer.close();
		 System.out.println("Chair drawn successfully!");	
	}
}
