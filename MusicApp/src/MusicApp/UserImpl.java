package MusicApp;

import java.util.ArrayList;
import java.util.List;

public class UserImpl extends User {
	public UserImpl(){
		
	}
	public UserImpl(String id){
		songs = new ArrayList<Song>();
		this.id=id;
	}
	public String get_id(){
		return id;
	}
	public List<Song> get_songs(){
		return songs;
	}
	public int get_total_play_count(){
		int play_count = 0;
		
		for (int i = 0 ; i < songs.size() ; i++){
			play_count += songs.get(i).play_count;
		}
		
		return play_count;
	}
	public Song get_song(String song_id){
		int i = 0;
		for (i=0 ; i < songs.size() ; i++){
			
			if (songs.get(i).id == song_id){
				return songs.get(i);
			}
		}
		return null;
	}
}
