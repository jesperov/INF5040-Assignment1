package MusicApp;


/**
* MusicApp/MusicProfilePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from hello.idl
* 25. september 2014 kl 14.31 CEST
*/

public abstract class MusicProfilePOA extends org.omg.PortableServer.Servant
 implements MusicApp.MusicProfileOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getTimesPlayed", new java.lang.Integer (0));
    _methods.put ("getTimesPlayedByUser", new java.lang.Integer (1));
    _methods.put ("getUserProfile", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // MusicApp/MusicProfile/getTimesPlayed
       {
         String song_id = in.read_string ();
         int $result = (int)0;
         $result = this.getTimesPlayed (song_id);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // MusicApp/MusicProfile/getTimesPlayedByUser
       {
         String user_id = in.read_string ();
         String song_id = in.read_string ();
         int $result = (int)0;
         $result = this.getTimesPlayedByUser (user_id, song_id);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 2:  // MusicApp/MusicProfile/getUserProfile
       {
         String user_id = in.read_string ();
         String song_id = in.read_string ();
         MusicApp.UserHolder user = new MusicApp.UserHolder ();
         int $result = (int)0;
         $result = this.getUserProfile (user_id, song_id, user);
         out = $rh.createReply();
         out.write_long ($result);
         MusicApp.UserHelper.write (out, user.value);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:MusicApp/MusicProfile:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public MusicProfile _this() 
  {
    return MusicProfileHelper.narrow(
    super._this_object());
  }

  public MusicProfile _this(org.omg.CORBA.ORB orb) 
  {
    return MusicProfileHelper.narrow(
    super._this_object(orb));
  }


} // class MusicProfilePOA
