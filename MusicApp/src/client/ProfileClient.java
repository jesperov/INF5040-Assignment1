package client;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import MusicApp.MusicProfile;
import MusicApp.MusicProfileHelper;

public class ProfileClient {

	static MusicProfile musicProfile;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			// Use NamingContextExt instead of NamingContext. This is 
			// part of the Interoperable naming Service.  
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// resolve the Object Reference in Naming
			String name = "Hello";
			musicProfile = MusicProfileHelper.narrow(ncRef.resolve_str(name));
			
			Calendar cal = Calendar.getInstance();
        	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        	String now = sdf.format(cal.getTime());

			/*String message = musicProfile.
			DO SOMETHING
        	
			System.out.println("Message from Server: " + message);
			*/
		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}	
	}

}
