package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import MusicApp.MusicProfilePOA;
import MusicApp.UserHolder;

public class ProfileServant extends MusicProfilePOA {

	@Override
	public int getTimesPlayed(String song_id) {

		Scanner scanner = null;
		try {
			File file = new File("C://train_triplets.txt");
			scanner = new Scanner(file, "UTF-8");
			
			
			
			while (scanner.hasNext()) {
				
				String user_id = scanner.next();
				String song_id2 = scanner.next();
				int played_count = scanner.nextInt();
				System.out.println(song_id2);
				if (song_id.equals(song_id2)){
					return played_count;
				}
				
			}
			

		} catch (Exception e) {

		}
		/*
		 * HelloServer ready and waiting ...
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOAKIMP12A8C130995 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOAPDEY12A81C210A9 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBBMDR12A8C13253B 2
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBFNSP12AF72A0E22 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBFOVM12A58A7D494 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBNZDC12A6D4FC103 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBSUJE12A6D4F8CF5 2
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBVFZR12A6D4F8AE3 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBXALG12A8C13C108 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBXHDL12A81C204C0 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOBYHAJ12A6701BF1D 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SOCNMUH12A6D4F6E6D 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SODACBL12A8C13C273 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SODDNQT12A6D4F5F7E 5
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SODXRTY12AB0180F3B 1
		 * b80344d063b5ccb3212f76538f3d9e43d87dca9e SODZWFT12A8C13C0E4 1
		 * b80344d0
		 */

		return 1337;
	}

	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUserProfile(String user_id, String song_id, UserHolder user) {

		return 0;
	}

}
