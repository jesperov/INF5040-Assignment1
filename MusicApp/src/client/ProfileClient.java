package client;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import MusicApp.MusicProfile;
import MusicApp.MusicProfileHelper;

public class ProfileClient {

	static MusicProfile musicProfile;
	/**
	 * @param args
	 */
	
	
	
	
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
			
			
			File file = new File("input-test.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()){
				String functionName = scanner.next();
				if (functionName.equals("getTimesPlayed")){
					String song_id = scanner.next();
					int played_count = musicProfile.getTimesPlayed(song_id);
					System.out.println("song_id: "+song_id + " has been played " + played_count + "times");
				}
				if (functionName.equals("getTimesPlayedByUser")){
					String user_id = scanner.next();
					String song_id = scanner.next();
					int played_count = musicProfile.getTimesPlayedByUser(user_id, song_id);
					System.out.println("user_id: "+user_id + " song_id: "+song_id + " has been played " + played_count + "times");
				}
				
				
			}
        	
        	
        	
        	//System.out.println("client got: " +message);
			/*
			DO SOMETHING
        	
			System.out.println("Message from Server: " + message);
			*/
		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}	
	}

}
