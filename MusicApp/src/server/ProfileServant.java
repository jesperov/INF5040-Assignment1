package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import MusicApp.MusicProfilePOA;
import MusicApp.UserHolder;

public class ProfileServant extends MusicProfilePOA {

	static Map<String,Integer> songMap;//cache for the songs
	
	
	@Override
	public int getTimesPlayed(String song_id) {
		
		
		if (songMap.containsKey(song_id)){//check the cache first
			return songMap.get(song_id);
		}
		
		Scanner scanner = null;
		try {
			File file = new File("C://train_triplets.txt");
			scanner = new Scanner(file);
			
			
			
			while (scanner.hasNext()) {
				
				String user_id = scanner.next();
				String song_id2 = scanner.next();
				int played_count = scanner.nextInt();
				//System.out.println(song_id2);
				if (song_id.equals(song_id2)){
					return played_count;
				}
			}
			

		} catch (Exception e) {

		}
		return 1337;
	}

	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {
		Scanner scanner = null;
		try {
			File file = new File("C://train_triplets.txt");
			scanner = new Scanner(file);
			
			
			
			while (scanner.hasNext()) {
				
				String user_id2 = scanner.next();
				String song_id2 = scanner.next();
				int played_count = scanner.nextInt();
				//System.out.println(song_id2);
				if (song_id.equals(song_id2) && user_id.equals(user_id2)){
					return played_count;
				}
				
			}
			

		} catch (Exception e) {

		}
		return 0;
	}

	@Override
	public int getUserProfile(String user_id, String song_id, UserHolder user) {

		return 0;
	}
	
	public static void createSongCache(){
		
		
		songMap = new HashMap();
		
		Scanner scanner = null;
		
		System.out.println("Creating song cache...");
		try {
			File file = new File("C://train_triplets.txt");
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				
				String user_id = scanner.next();
				String song_id2 = scanner.next();
				int played_count = scanner.nextInt();

				if (songMap.get(song_id2) == null){
					songMap.put(song_id2, played_count);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Created the song cache with size: " + songMap.size());
	}

}
