package MusicApp;

public class UserImpl extends User {
	public UserImpl(){
		
	}
	public String get_id(){
		return id;
	}
	public Song[] get_songs(){
		return songs;
	}
}
