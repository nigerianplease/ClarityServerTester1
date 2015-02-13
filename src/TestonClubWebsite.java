import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestonClubWebsite {
	public static void main(String[] args) {
		Document doc;
		try {
			Connection.Response res = Jsoup
					.connect(
							"https://loudoun.gradebook.net/clarity/Gradebook/Logon.aspx")
					.userAgent("Chrome").method(Method.GET).execute();

			System.out.println("connected");

			Map<String, String> cookies = res.cookies();
			doc = res.parse();
			//System.out.println(cookies);
			Element pageUniqueId = doc.select("input#PageUniqueId").first();
			Element viewState = doc.select("input#__VIEWSTATE").first();
			Element eventValidation = doc.select("input#__EVENTVALIDATION")
					.first();
			Map<String, String> payload = new HashMap<String, String>();
			payload.put("__LASTFOCUS", "");
			payload.put("__EVENTTARGET", "");
			payload.put("__VIEWSTATE", viewState.attr("value"));
			payload.put("__EVENTVALIDATION", eventValidation.attr("value"));
			payload.put("ctl00_ContentPlaceHolder_Username", "718241");
			payload.put("ctl00_ContentPlaceHolder_Password", "shubhamp1");
			payload.put("ctl00_ContentPlaceHolder_lstDomains", "Pinnacle");
			//payload.put("ctl00_ContentPlaceHolder_lstDomains", "Pinnacle");
			payload.put("ctl00_ContentPlaceHolder_LogonButton", "Sign in");
			//payload.put("ctl00_ContentPlaceHolder_LogonButton", "Sign in");
			payload.put("PageUniqueId", pageUniqueId.attr("value"));
		//	System.out.println(payload);	
			res = Jsoup
					.connect(
							"https://loudoun.gradebook.net/clarity/Gradebook/Logon.aspx")
					.header("Content-Type",
							"application/x-www-form-urlenclosed").data(payload)
					.cookies(cookies).followRedirects(false).method(Method.POST)
					.execute();
			if(res.hasCookie("PinnacleWeb.ASPXAUTH")){
				System.out.println("ithasit");
			}
			else{
				System.out.println("nope");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}