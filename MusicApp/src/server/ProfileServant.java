package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;

import MusicApp.MusicProfilePOA;
import MusicApp.Song;
import MusicApp.SongImpl;
import MusicApp.UserHolder;
import MusicApp.UserImpl;

public class ProfileServant extends MusicProfilePOA {

	static Map<String, Integer> songMap;// cache for the songs
	static Map<String, UserImpl> userMap;// cache for users

	@Override
	public int getTimesPlayed(String song_id) {

		if (songMap.containsKey(song_id)) {// check the cache first
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
				// System.out.println(song_id2);
				if (song_id.equals(song_id2)) {
					return played_count;
				}
			}

		} catch (Exception e) {

		}
		return 0;
	}

	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {

		UserImpl user = userMap.get(user_id);//checck the cache first
		if (user != null) {
			return user.get_song(song_id).play_count;
		}

		Scanner scanner = null;

		try {
			File file = new File("C://train_triplets.txt");
			scanner = new Scanner(file);

		} catch (Exception e) {
			
		}
		
		while (scanner.hasNext()) {

			String user_id2 = scanner.next();
			String song_id2 = scanner.next();
			int played_count = scanner.nextInt(); // System.out.println(song_id2);
			if (song_id.equals(song_id2) && user_id.equals(user_id2)) {
				return played_count;
			}

		}
		return 0;

	}

	@Override
	public int getUserProfile(String user_id, String song_id, UserHolder userHolder) {//#########################
		UserImpl user = userMap.get(user_id);//checck the cache first
		if (user != null) {
			userHolder = new UserHolder(user);
			return user.get_song(song_id).play_count;
		}

		Scanner scanner = null;

		try {
			File file = new File("C://train_triplets.txt");
			scanner = new Scanner(file);

		} catch (Exception e) {
			
		}
		
		while (scanner.hasNext()) {

			String user_id2 = scanner.next();
			String song_id2 = scanner.next();
			int played_count = scanner.nextInt(); // System.out.println(song_id2);
			if (song_id.equals(song_id2) && user_id.equals(user_id2)) {
				
				userHolder = new UserHolder(user);
				return played_count;
			}

		}
		return 0;
	}

	public static void createSongCache() {
		songMap = new HashMap<String, Integer>();

		Scanner scanner = null;
		
		long totalSize=0;
		long currentSize=0;
		
		System.out.println("Creating song cache...");
		try {
			File file = new File("C://train_triplets.txt");
			totalSize = file.length();
			scanner = new Scanner(file);

		} catch (Exception e) {
			System.out.println(e);
		}
		
		while (scanner.hasNext()) {

			String user_id = scanner.next();
			String song_id = scanner.next();
			int played_count = scanner.nextInt();
			
			currentSize += user_id.length();
			currentSize += song_id.length();
			currentSize += 4;//int is 4 bytes long
			
			if (songMap.get(song_id) == null) {
				songMap.put(song_id, played_count);
			} else {
				songMap.put(song_id, songMap.get(song_id) + played_count);
			}
			
			float percentageDone = (float)(100*(double)currentSize/(double)totalSize);
			
			if ((int)percentageDone+0.000001 >= percentageDone )//just a way to not print it too often
				System.out.println((int)percentageDone + "%");

		}
		System.out.println("Created the song cache with size: "
				+ songMap.size());

	}

	public static void createUserCache() {
		List<UserImpl> userList = new ArrayList<UserImpl>();

		Scanner scanner = null;

		System.out.println("Creating user cache...");
		int counter = 0;
		int users = 0;

		long totalSize=0;
		long currentSize=0;
		
		try {
			File file = new File("C://train_triplets.txt");
			totalSize = file.length();
			scanner = new Scanner(file);

		} catch (Exception e) {
			System.out.println(e);
		}

		String old_user_id = "";
		UserImpl user = null;

		while (scanner.hasNext()) {
			counter++;

			String user_id = scanner.next();
			String song_id = scanner.next();
			int played_count = scanner.nextInt();
			
			currentSize += user_id.length();
			currentSize += song_id.length();
			currentSize += 4;//int is 4 bytes long

			if (old_user_id.equals(user_id)) {
				user.addSong(new SongImpl(song_id, played_count));

			} else {
				insertIntoSortedList(userList, user);

				user = new UserImpl(user_id);
				old_user_id = user_id;

				user.addSong(new SongImpl(song_id, played_count));
				
			}
			float percentageDone = (float)(100*(double)currentSize/(double)totalSize);
			
			if ((int)percentageDone+0.000001 >= percentageDone )//just a way to not print it too often
				System.out.println((int)percentageDone + "%");
			
		}
		System.out.println("/nUSER CACHE;");

		userMap = new HashMap<String, UserImpl>();

		for (UserImpl it : userList) {
			userMap.put(it.get_id(), it);
			System.out.println(it.get_total_play_count());
		}
		userList.clear();
		System.out.println("Created user cache with size: "+userMap.size());

	}

	private static boolean insertIntoSortedList(List<UserImpl> list,
			UserImpl user) {
		if (user != null) {

			int i = list.size() - 1;
			while (i >= 0) {

				if (user.get_total_play_count() < list.get(i)
						.get_total_play_count()) {
					break;
				} else {
					i--;
				}
			}

			list.add(i + 1, user);
			if (list.size() > 1000) {
				list.remove(list.size() - 1);
			}
		}
		return true;
	}

}
