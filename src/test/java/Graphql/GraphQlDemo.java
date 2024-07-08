package Graphql;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.json.*;

public class GraphQlDemo {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String response = given().log().all().contentType(ContentType.JSON).body(
				"{\"query\":\"query($episodeId :Int!,$characterId:Int!){\\n  \\n  episode(episodeId:$episodeId){\\n    \\n    name\\n    id\\n    episode\\n    air_date\\n   \\n  }\\n  characters(filters:{name:\\\"Rahul\\\"}){\\n  \\n  info{\\n    \\n    count\\n    \\n    \\n  } result{\\n    \\n    id\\n  }\\n   \\n}\\n  \\n  \\n   character(characterId:$characterId) {\\n      \\n      name\\n    }\\n  \\n \\n  \\n  episodes(filters:{episode:\\\"hulu\\\"}){\\n    \\n    info{\\n      \\n      count\\n      \\n           \\n    }\\n    result{\\n      name\\n      \\n      characters{\\n        \\n        name\\n        id\\n      }\\n    }\\n    \\n  }\\n}\",\"variables\":{\"episodeId\":8,\"characterId\":276}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
		System.out.println("Response" + response);
		JsonPath jsonPath = new JsonPath(response);
		String episodeName = jsonPath.get("data.episode.name");
		System.out.println("Episode name " + episodeName);

		String mutationResponse = given().log().all().contentType(ContentType.JSON).body("{\r\n"
				+ "  \"query\": \"mutation($characterName:String!,$LocationName:String!){\\n  \\n  \\n  createLocation(location:{name:$LocationName,type:\\\"southzone\\\",dimension:\\\"234\\\"}){\\n    id\\n    \\n  }\\n  \\n  createCharacter(character:{name:$characterName,type:\\\"Good\\\",status:\\\"good\\\",species:\\\"fantacy\\\",gender:\\\"female\\\",image:\\\"png\\\",originId:10846,locationId:10846}){\\n    \\n    id\\n  }\\n}\",\r\n"
				+ "  \"variables\": {\r\n"
				+ "    \"characterName\": \"ABCD\",\r\n"
				+ "    \"LocationName\": \"AUS\"\r\n"
				+ "  }\r\n"
				+ "}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
		System.out.println("MutationResponse" + mutationResponse);
	
	}

}
