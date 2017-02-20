import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;


public class POSTagger {
	
	public static String tagContent(String input) throws IOException{
		
		String output="";
		String locations[]={"left","right","above","below","front","behind","top", "under","on"};
		String[] objects = { "table", "chair", "box","cone","sphere", "cylinder"};
		String[] attributes={"red", "green", "blue","brown","black", "white","small","regular","large","round","square"};

		ArrayList<VRMLObject> objectArray = new ArrayList<VRMLObject>();
		int counter =0;
		ObjectIdentifier objectIdentifier = new ObjectIdentifier();
		
		//POS tagger start
		POSModel model = new POSModelLoader()	
		.load(new File("en-pos-maxent.bin"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);
	
		ObjectStream<String> lineStream = new PlainTextByLineStream(
			new StringReader(input));
		perfMon.start();
	
		String line;	
		while ((line = lineStream.read()) != null) { 
			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
				.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);		
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			//POS tagger end
			
			String[] taggedWords=sample.toString().split(" ");
			String[] current;
			VRMLObject currentElement = new VRMLObject();
			for(String a:taggedWords){								
				System.out.println(a);
				current=a.split("_");
				if(current[1].equals("JJ")&&Arrays.asList(attributes).contains(current[0])){
					currentElement.attributes.add(current[0].toLowerCase());					
				}
				else if(current[1].equals("NN")&&Arrays.asList(attributes).contains(current[0])){
					currentElement.attributes.add(current[0].toLowerCase());
				}
				else if(current[1].equals("NN")&&Arrays.asList(objects).contains(current[0])){
					currentElement.name=current[0].toLowerCase();
					currentElement.location="";
					objectArray.add(counter, currentElement);
					counter++;
					currentElement = new VRMLObject();
				}
				else if(Arrays.asList(locations).contains(current[0])){
					VRMLObject temp = new VRMLObject();
					temp=objectArray.get(counter-1);
					temp.location=current[0];
					objectArray.set(counter-1, temp);					
				}
			}
			System.out.println(sample.toString());
			objectIdentifier.defineObject(objectArray);
			perfMon.incrementCounter();
		}
		perfMon.stopAndPrintFinalResult();
		
			return output;
		}
}
