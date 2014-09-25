package MusicApp;


/**
* MusicApp/User.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from hello.idl
* 25. september 2014 kl 14.31 CEST
*/

public abstract class User implements org.omg.CORBA.portable.StreamableValue
{
  public String id = null;
  public MusicApp.Song songs[] = null;

  private static String[] _truncatable_ids = {
    MusicApp.UserHelper.id ()
  };

  public String[] _truncatable_ids() {
    return _truncatable_ids;
  }

  public void _read (org.omg.CORBA.portable.InputStream istream)
  {
    this.id = istream.read_string ();
    int _len0 = istream.read_long ();
    this.songs = new MusicApp.Song[_len0];
    for (int _o1 = 0;_o1 < this.songs.length; ++_o1)
      this.songs[_o1] = MusicApp.SongHelper.read (istream);
  }

  public void _write (org.omg.CORBA.portable.OutputStream ostream)
  {
    ostream.write_string (this.id);
    ostream.write_long (this.songs.length);
    for (int _i0 = 0;_i0 < this.songs.length; ++_i0)
      MusicApp.SongHelper.write (ostream, this.songs[_i0]);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MusicApp.UserHelper.type ();
  }
} // class User
