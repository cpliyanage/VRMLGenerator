import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;


public class TaggerAndParser {
	
	public static String tagContent(String input) throws IOException{
		
		String output="";
		String[] locations={"left","right","above","below","front","behind"};
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
			String[] whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
				.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);		
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			//POS tagger end
			
			//Chunker start
			InputStream is = new FileInputStream("en-chunker.bin");
			ChunkerModel cModel = new ChunkerModel(is);
		 
			ChunkerME chunkerME = new ChunkerME(cModel);
			String chunkerResult[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
			
			System.out.println("Chunker output: ");
			System.out.println("");
			
			for (String s : chunkerResult)
				System.out.println(s);
		 
			Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
			for (Span s : span)
				System.out.println(s.toString());
			//Chunker end
			
			//Parser start
			InputStream is2 = new FileInputStream("en-parser-chunking.bin");
			 
			ParserModel model2 = new ParserModel(is2);
		 
			Parser parser = ParserFactory.create(model2);

			Parse topParses[] = ParserTool.parseLine(input, parser, 1);
			
			System.out.println("Parser output: ");
			System.out.println("");
			
			for (Parse p : topParses)
				p.show();
		 
			is.close();
			//Parser end
			
			//Identifying objects and attributes
			String[] taggedWords=sample.toString().split(" ");
			VRMLObject currentElement = new VRMLObject();
			
			System.out.println("Span length: "+span.length);
			for(int i=0;i<span.length;i++){
				System.out.println("span" + i);
				int start=0;
				int end=0;
				int endIndex=0;				
				endIndex=getEndIndex(span[i].toString());
				System.out.println("Phrase: "+span[i].toString().substring(1,endIndex));
				String[] sub=(span[i].toString().substring(1,endIndex)).split("\\.\\.");
				
				//System.out.println(sub.length);
				
				System.out.println("Start: " +sub[0]);
				System.out.println("End: "+sub[1]);
				
				start= Integer.parseInt(sub[0]);
				end= Integer.parseInt(sub[1]);				
				
				for(int j=start;j<end;j++){
					System.out.println("Tagged word "+j+": "+taggedWords[j]);
					String[] current=taggedWords[j].split("_");
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
			}	
			
			objectIdentifier.defineObject(objectArray);
			perfMon.incrementCounter();
		}
		perfMon.stopAndPrintFinalResult();
		
			return output;
		}
	
	static int getEndIndex(String phrase){
		int endIndex;
		endIndex=phrase.indexOf(')');
		//System.out.println("End index = "+endIndex);
		return endIndex;
	}
}
