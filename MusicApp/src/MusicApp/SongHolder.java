package MusicApp;

/**
* MusicApp/SongHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from hello.idl
* 25. september 2014 kl 14.31 CEST
*/

public final class SongHolder implements org.omg.CORBA.portable.Streamable
{
  public MusicApp.Song value = null;

  public SongHolder ()
  {
  }

  public SongHolder (MusicApp.Song initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MusicApp.SongHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MusicApp.SongHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MusicApp.SongHelper.type ();
  }

}