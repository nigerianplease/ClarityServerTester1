import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestonClubWebsite {
	public static void main(String[] args) {
		Document doc;
		try {

			doc = Jsoup
					.connect(
							"https://loudoun.gradebook.net/clarity/Gradebook/Logon.aspx")
					.get();
			Elements elems = doc.select(
					"input#ctl00_ContentPlaceHolder_Username");
			//if(elems.contains("Please enter your username.")){
				//System.out.println("true");
			//}
			//elems.val("718241");
			System.out.println(elems.text());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}