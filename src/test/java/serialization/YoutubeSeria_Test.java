package serialization;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.gorest.util.TestUtil;
import com.qa.practise.pojoObject.Id;
import com.qa.practise.pojoObject.Items;
import com.qa.practise.pojoObject.PageInfo;
import com.qa.practise.pojoObject.YoutubePojo;

public class YoutubeSeria_Test {
	List<Items> iteamList = new ArrayList<Items>();
	String jsonPayload = null;
	
	@Test
	public void YouTubeJosn_Test() {
	Id id1 = new Id("youtube#channel", "UCJowOS1R0FnhipXVqEnYU1A", null);
	Items item1 = new Items("youtube#searchResult", "\"m2yskBQFythfE4irbTIeOgYYfBU/QpOIr3QKlV5EUlzfFcVvDiJT0hw\"", id1);
	
	Id id2 = new Id("youtube#video",null, "Eqa2nAAhHN0");
	Items item2 = new Items("youtube#searchResult", "\"m2yskBQFythfE4irbTIeOgYYfBU/AWutzVOt_5p1iLVifyBdfoSTf9E\"", id2);
	
	iteamList.add(item1);
	iteamList.add(item2);
	PageInfo pInfo = new PageInfo(4249, 5);
	
	YoutubePojo youtubeObj = new YoutubePojo("youtube#searchListResponse", "\"m2yskBQFythfE4irbTIeOgYYfBU/PaiEDiVxOyCWelLPuuwa9LKz3Gk\"", 
			"CAUQAA", "KE", pInfo, iteamList);
	
	ObjectMapper mapper = new ObjectMapper();
	try {
		jsonPayload= mapper.writeValueAsString(youtubeObj);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	System.out.println(jsonPayload);
	}

}
