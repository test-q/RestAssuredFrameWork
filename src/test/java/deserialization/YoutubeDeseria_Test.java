package deserialization;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.practise.pojoObject.YoutubePojo;

public class YoutubeDeseria_Test {
	
	String filePath = "src/main/java/com/qa/practise/json/youtube.json";
	YoutubePojo youtubeObj;
	
	@Test
	public void YouTube_Test() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			youtubeObj = mapper.readValue(new File(filePath), YoutubePojo.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println(youtubeObj);
		
		System.out.println("Next Page Token: " + youtubeObj.getNextPageToken());
		System.out.println();
		
		System.out.println("Total Result: " + youtubeObj.getPageInfo().getTotalResults());
		System.out.println();
		
		int size = youtubeObj.getItems().size();
		
		for(int i=0; i<size; i++) {
			System.out.println("Iteam Kind " + i + " : " + youtubeObj.getItems().get(i).getKind());
			System.out.println("Iteam ETag " + i + " : " + youtubeObj.getItems().get(i).getEtag());
			System.out.println("Iteam ID Kind " + i + " : " + youtubeObj.getItems().get(i).getId().getKind());
			System.out.println("Iteam ID ChannelId " + i + " : " + youtubeObj.getItems().get(i).getId().getChannelId());
			System.out.println("Iteam ID VideoId " + i + " : " + youtubeObj.getItems().get(i).getId().getVideoId());
			System.out.println();
		}
		
	}

}
