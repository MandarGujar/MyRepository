
import java.util.Scanner;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.Person;
 
public class TestLink 
{
	String Myname;
	int connectionCount;
 
    private static Scanner s;
 
    TestLink()
    {
 
        String linkedinKey = "772zakzooki0py";    //add your LinkedIn key
        String linkedinSecret = "MPRO7iI0UoY3hkje"; //add your LinkedIn Secret
         
        LinkedInOAuthService oauthService;
        LinkedInRequestToken requestToken;
           
        System.out.println("Fetching request token from LinkedIn...");  
        String authUrl = null;
        String authToken,authTokenSecret;
         
        oauthService= LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(linkedinKey,linkedinSecret); 
        requestToken= oauthService.getOAuthRequestToken();
        authToken= requestToken.getToken();  
        authTokenSecret = requestToken.getTokenSecret();  
        
        System.out.println("Request token " +requestToken);
        System.out.println("Auth token" +authToken);
        System.out.println("Auth token secret" +authTokenSecret);
         
        authUrl = requestToken.getAuthorizationUrl();
 
        System.out.println("Copy below link in web browser to authorize. Copy the PIN obtained\n" + authUrl);
        System.out.println("Enter the PIN code:");
         
        String pin;
         
        try
            {
         
                s = new Scanner(System.in);
                pin = s.next();  
                System.out.println("Fetching access token from LinkedIn...");
         
                LinkedInAccessToken accessToken =  oauthService.getOAuthAccessToken(requestToken, pin);
                System.out.println("Access token : " +  accessToken.getToken());
                System.out.println("Token secret : " +  accessToken.getTokenSecret());
                final LinkedInApiClientFactory factory =  LinkedInApiClientFactory.newInstance(linkedinKey,linkedinSecret);
                final LinkedInApiClient client =  factory.createLinkedInApiClient(accessToken);
        
                //posting status to profile
                client.updateCurrentStatus("");
                //Person profile = client.getProfileById("FCnZza0oC8");
                Person profile = client.getProfileForCurrentUser();
                Connections connections = client.getConnectionsForCurrentUser();
                
                connectionCount=0;
                for (Person person : connections.getPersonList()) 
                {
                    System.out.println("\n\t"+person.getId() + "\t: " + person.getFirstName() + " " + person.getLastName() + "\t: " + person.getHeadline());
                    connectionCount++;
                }
                System.out.println("Conenction count = "+connectionCount);
                
                Myname = profile.getFirstName();
                System.out.println("\nId = "+profile.getId());
                System.out.println("Name:" + profile.getFirstName() + " " + profile.getLastName());
                System.out.println("Headline:" + profile.getHeadline());
                System.out.println("Summary:" + profile.getSummary());
                System.out.println("Industry:" + profile.getIndustry());
                System.out.println("Picture:" + profile.getPictureUrl());
                System.out.println("Skills: "+profile.getSkills());
             
            }
 
        finally
        {
            System.out.println("Updated status!");
        }
    }

	public String getName()
	{
		return Myname;
	}

	public int getConnectionCount()
	
	{
		return connectionCount;
	}
}