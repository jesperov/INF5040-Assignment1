package MusicApp;


/**
* MusicApp/MusicProfileHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from hello.idl
* 25. september 2014 kl 14.31 CEST
*/

abstract public class MusicProfileHelper
{
  private static String  _id = "IDL:MusicApp/MusicProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, MusicApp.MusicProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static MusicApp.MusicProfile extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (MusicApp.MusicProfileHelper.id (), "MusicProfile");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static MusicApp.MusicProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_MusicProfileStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, MusicApp.MusicProfile value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static MusicApp.MusicProfile narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MusicApp.MusicProfile)
      return (MusicApp.MusicProfile)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MusicApp._MusicProfileStub stub = new MusicApp._MusicProfileStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static MusicApp.MusicProfile unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MusicApp.MusicProfile)
      return (MusicApp.MusicProfile)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MusicApp._MusicProfileStub stub = new MusicApp._MusicProfileStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
