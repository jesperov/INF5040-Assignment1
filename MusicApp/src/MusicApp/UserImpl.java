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
	public boolean addSong(SongImpl song){
		total_play_count += song.get_play_count();
		return songs.add(song);
	}
	public Song removeSong(int index){
		total_play_count -= songs.get(index).play_count;
		return songs.remove(index);
	}
	
	public int get_total_play_count(){
		return total_play_count;
	}
	public Song get_song(String song_id){
		int i = 0;
		for (i=0 ; i < songs.size() ; i++){
			
			if (songs.get(i).id.equals(song_id)){
				return songs.get(i);
			}
		}
		return null;
	}
}
