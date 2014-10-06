package client;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import MusicApp.MusicProfile;
import MusicApp.MusicProfileHelper;
import MusicApp.User;
import MusicApp.UserHolder;
import MusicApp.UserImpl;

public class ProfileClient {

	static MusicProfile musicProfile;
	/**
	 * @param args
	 */
	static Map<String, User> userMap = new HashMap<String, User>();

	public static void main(String[] args) {
		try{
			
			
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			// Use NamingContextExt instead of NamingContext. This is 
			// part of the Interoperable naming Service.  
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// resolve the Object Reference in Naming
			String name = "Hello";
			musicProfile = MusicProfileHelper.narrow(ncRef.resolve_str(name));
			
			
			File inputFile = new File("input.txt");
			Scanner scanner = new Scanner(inputFile);
			
			File file1 = new File("getTimesPlayed_output.txt");
			PrintWriter pw1 = new PrintWriter(file1);
			
			File file2 = new File("getTimesPlayedByUser_output.txt");
			PrintWriter pw2 = new PrintWriter(file2);
			
			File file3 = new File("getUserProfile_output.txt");
			PrintWriter pw3 = new PrintWriter(file3);
			
			long startTime = System.currentTimeMillis();
			while (scanner.hasNext()){
				String functionName = scanner.next();
				
				if (functionName.equals("getTimesPlayed")){
					String song_id = scanner.next();
					
					int played_count = musicProfile.getTimesPlayed(song_id);
					pw1.println("song_id: "+song_id + " has been played " + played_count + "times");
					System.out.println("song_id: "+song_id + " has been played " + played_count + "times");
					
				}/*
				if (functionName.equals("getTimesPlayedByUser")){//this is for the getTimesPlayedByUser function
					String user_id = scanner.next();
					String song_id = scanner.next();
					int played_count = musicProfile.getTimesPlayedByUser(user_id, song_id);
					
					pw2.println("user_id: "+user_id + " song_id: "+song_id + " has been played " + played_count + "times");
					System.out.println("user_id: "+user_id + " song_id: "+song_id + " has been played " + played_count + "times");
				}*/
				if (functionName.equals("getTimesPlayedByUser")){//this is for the getProfile function
					String user_id = scanner.next();
					String song_id = scanner.next();
					
					UserImpl user = null;
					int played_count=-1;
					if (userMap.containsKey(user_id)){
						user = (UserImpl)userMap.get(user_id);
						played_count = user.get_song(song_id).play_count;
					}else{
						UserHolder userHolder = new UserHolder();
						played_count = musicProfile.getUserProfile(user_id, song_id, userHolder);
						user = (UserImpl)userHolder.value;
						userMap.put(user_id, user);
					}
					
					pw3.println("user_id: "+user_id + " song_id: "+song_id + " has been played " + user.get_song(song_id).play_count + "times");
					System.out.println("user_id: "+user_id + " song_id: "+song_id + " has been played " + user.get_song(song_id).play_count + "times or " + played_count);
					
					
				}
			}
			
			long endTime = System.currentTimeMillis();
			long runtime = (endTime-startTime);
        	System.out.println("Client ran for: " + runtime + " ms");
			
			pw1.println("Client ran for: " + runtime + " ms");
			pw2.println("Client ran for: " + runtime + " ms");
			pw3.println("Client ran for: " + runtime + " ms");
			
        	scanner.close();
        	pw1.close();
        	pw2.close();
        	pw3.close();
        	
        	
			
			System.out.println("Client is done");
			
		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}	
	}

}
