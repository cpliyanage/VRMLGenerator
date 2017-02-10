import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;


public class POSTagger {
	
	public String tagContent(String input) throws IOException{
		String output="";
		ArrayList<VRMLObject> objectArray = new ArrayList<VRMLObject>();
		int counter =0;
		ObjectIdentifier objectIdentifier = new ObjectIdentifier();
		POSModel model = new POSModelLoader()	
		.load(new File("en-pos-maxent.bin"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);
	
		ObjectStream<String> lineStream = new PlainTextByLineStream(
			new StringReader(input));
		perfMon.start();
	
		String line;
		VRMLObject currentElement = new VRMLObject();
		while ((line = lineStream.read()) != null) {
 
			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
				.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);
		
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			String[] taggedWords=sample.toString().split(" ");
			String[] current;
			for(String a:taggedWords){								
				System.out.println(a);
				current=a.split("_");
				if(current[1].equals("JJ")){
					currentElement.attributes.add(current[0].toLowerCase());					
				}
				if(current[1].equals("NN")){
					currentElement.name=current[0].toLowerCase();	
					objectArray.add(counter, currentElement);
					counter++;
					currentElement = new VRMLObject();
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
