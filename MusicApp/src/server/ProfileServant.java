package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import MusicApp.MusicProfilePOA;
import MusicApp.Song;
import MusicApp.SongImpl;
import MusicApp.UserHolder;
import MusicApp.UserImpl;

public class ProfileServant extends MusicProfilePOA {

	static Map<String,Integer> songMap;//cache for the songs
	static Map<String, UserImpl> userMap;//cache for users
	static List<UserImpl> userList;
	
	@Override
	public int getTimesPlayed(String song_id) {
		
		
		if (songMap.containsKey(song_id)){//check the cache first
			try {
				Thread.currentThread().sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		
		UserImpl user = userMap.get(user_id);
		if (user == null){
			return -1;
		}
		return user.get_song(song_id).play_count;
		
		/*
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
		*/
		
	}

	@Override
	public int getUserProfile(String user_id, String song_id, UserHolder user) {

		return 0;
	}
	
	public static void createSongCache(){
		songMap = new HashMap<String, Integer>();
	
		Scanner scanner = null;
		
		System.out.println("Creating song cache...");
		try {
			File file = new File("C://train_triplets.txt");
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				
				String user_id = scanner.next();
				String song_id = scanner.next();
				int played_count = scanner.nextInt();
				
				if (songMap.get(song_id) == null){
					songMap.put(song_id, played_count);
				}
				else{
					songMap.put(song_id, songMap.get(song_id) + played_count);
				}

				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Created the song cache with size: " + songMap.size());
		
	}
	public static void createUserCache(){
		userMap = new HashMap<String, UserImpl>();//insertion ordered map
		userList = new ArrayList<UserImpl>();
		
		Scanner scanner = null;
		
		System.out.println("Creating user cache...");
		try {
			File file = new File("C://train_triplets.txt");
			scanner = new Scanner(file);
			
			while (scanner.hasNext()) {
				
				String user_id = scanner.next();
				String song_id = scanner.next();
				int played_count = scanner.nextInt();
				
				insertIntoUserMap(user_id, song_id, played_count);

				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Created the user cache with size: " + userMap.size());
		
	}
	
	public static void insertIntoUserMap(String user_id, String song_id, int playedCount){
		
		
		UserImpl user = userMap.get(user_id);
		if (user == null){//if the user is not found
			
			user = new UserImpl(user_id);
			
			user.songs.add(new SongImpl(song_id, playedCount));
			
			int i = 0;
			while (i < userList.size()){
				
				//loop downwards in the list till play count is smaller or equal to the current one in the list
				if (userList.get(i).get_total_play_count() > user.get_total_play_count()){
					i++;
				}
				else{
					break;
				}
			}
			userList.add(i,user);
			
			userMap.put(user_id, user);
			
		}else{//if the user is found
			
			user.songs.add(new SongImpl(song_id, playedCount));
			
			userList.remove(user);//remove it before we insert it again
			
			int i = 0;
			while (i < userList.size()){
				
				//loop downwards in the list till play count is smaller or equal to the current one in the list
				if (userList.get(i).get_total_play_count() > user.get_total_play_count()){
					i++;
				}
				else{
					
					break;
				}
			}
			userList.add(i,user);
			
			
		}
		System.out.println(userList.size());
		if (userList.size() > 1000){//remove the last element
			
			userMap.remove(userList.get(userList.size()-1));
			userList.remove(userList.size()-1);
		}
		
		
		
	}
	
	static class ValueComparator implements Comparator<String> {

	    Map<String, UserImpl> user;
	    public ValueComparator(Map<String, UserImpl> user) {
	        this.user = user;
	        
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {
	        if (user.get(a).get_total_play_count() <= user.get(b).get_total_play_count()) {
	            return 1;
	        } else {
	            return -1;
	        } // returning 0 would merge keys
	    }
	}
}
