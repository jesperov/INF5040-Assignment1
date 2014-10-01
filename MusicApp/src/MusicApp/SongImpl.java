package MusicApp;

public class SongImpl extends Song {
	public SongImpl(){
		
	}
	public SongImpl(String id, int play_count){
		this.id = id;
		this.play_count = play_count;
		
	}
	public String get_id(){
		return id;
	}
	public int get_play_count(){
		return play_count;
	}
}
