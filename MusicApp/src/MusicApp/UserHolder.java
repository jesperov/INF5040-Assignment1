package MusicApp;

/**
* MusicApp/UserHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from hello.idl
* 25. september 2014 kl 14.31 CEST
*/

public final class UserHolder implements org.omg.CORBA.portable.Streamable
{
  public MusicApp.User value = null;

  public UserHolder ()
  {
  }

  public UserHolder (MusicApp.User initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MusicApp.UserHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MusicApp.UserHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MusicApp.UserHelper.type ();
  }

}
