package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import storage_model.Storage;
import trail_model.TrailStore;
import user_model.UserStore;
/**
 * This class is to backup and restore the Storage to and from
 * a binary data file. It uses input and output streams to accomplish this.
 * 
 * @author Nick Broome
 *
 */
public class Backup {
	
	/**
	 * The method loads two files from the backup_data folder, and tries to restore them
	 * into the storage. If the files exist, then they will be loaded into the storage's
	 * trail and user storage.
	 * 
	 * @param storage The main overarching data structure that contains the app's user and
	 * storage data.
	 */
	public static void restore(Storage storage) {
		FileInputStream fis = null;
		File trailFile = new File("backup_data/trailStorage.dat");
		File userFile = new File("backup_data/userStorage.dat");
		try {
			if (trailFile.exists()) {
				fis = new FileInputStream("backup_data/trailStorage.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				storage.setTrailStorage((TrailStore) (ois.readObject()));
				ois.close();
			}	
			if (userFile.exists()) {
				fis = new FileInputStream("backup_data/userStorage.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				storage.setUserStorage((UserStore) (ois.readObject()));
				ois.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Restored.");
	}

	/**
	 * The method attempts to load storage's trail and userStorage into a binary file
	 * in the backup_data folder through use of File and Object outputStreams.
	 * 
	 * @param storageThe main overarching data structure that contains the app's user and
	 * storage data.
	 */
	public static void backup(Storage storage) {
			try {
				FileOutputStream fos = new FileOutputStream("backup_data/trailStorage.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(storage.getTrailStorage());
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				FileOutputStream fos = new FileOutputStream("backup_data/userStorage.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(storage.getUserStorage());
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			System.out.println("Storage has been backed up.");

		}
	}


