package MusicApp;


/**
* MusicApp/Song.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from hello.idl
* 25. september 2014 kl 14.31 CEST
*/

public abstract class Song implements org.omg.CORBA.portable.StreamableValue
{
  public String id = null;
  public int play_count = (int)0;

  private static String[] _truncatable_ids = {
    MusicApp.SongHelper.id ()
  };

  public String[] _truncatable_ids() {
    return _truncatable_ids;
  }

  public void _read (org.omg.CORBA.portable.InputStream istream)
  {
    this.id = istream.read_string ();
    this.play_count = istream.read_long ();
  }

  public void _write (org.omg.CORBA.portable.OutputStream ostream)
  {
    ostream.write_string (this.id);
    ostream.write_long (this.play_count);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MusicApp.SongHelper.type ();
  }
} // class Song
