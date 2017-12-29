import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DictionarySearch2 {
	private String address;
	public DictionarySearch2(){
		this.address = "http://www.dictionary.com/browse/";
	}

	public ArrayList<String> getWords(String word, int length) {
		ArrayList<String> definitions = new ArrayList<String>();
		String url = this.address + word;
		try {
			Document documents = Jsoup.connect(url).get();
			String html = documents.html();
			Document document = Jsoup.parse(html);

			if (isSpelledCorrectly(html)) {
				Elements elements = document.getElementsByClass("def-set");
				for (int i = 0; i < elements.size(); i++) {
					String definitionOfWord= elements.get(i).text();
					definitionOfWord = definitionOfWord.substring(1,definitionOfWord.length());
					String[] splited = definitionOfWord.split("\\s+");
					for (int j = 0; j < splited.length; j++) {
						if (splited[j].length() == length) {
							definitions.add(splited[j].toUpperCase());
						}
					}
				}
			}  
		} 
		catch(IOException e) {
			System.out.println("Word was not found from dictionary");
		}
		return definitions;
	}
	public boolean isSpelledCorrectly (String html) {
		Document document = Jsoup.parse(html);
		Elements elements = document.getElementsByClass("misspelled");

		if (elements.isEmpty())
			return true;
		else
			return false;
	}
	public static void main (String[] args) throws IOException{
		DictionarySearch2 s = new DictionarySearch2();
		ArrayList <String> definitions = s.getWords("Polish", 5);
		for (int i=0; i < definitions.size(); i ++){
			System.out.println((i+1) + ". " + definitions.get(i));
		}
	}
}
