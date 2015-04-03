package serviceFunctionality.socialStateLearning.Matching.Facebook;

import java.sql.Timestamp;

import core.data.dao.CoreDaoException;
import core.util.log.Log;
import dataObjects.socialStateLearning.Matching.AppUserDAO;
import dataObjects.socialStateLearning.Matching.Facebook_profileDAO;
import edu.njit.sc.core.dao.SmartCampusDAO;

public class FacebookPersistance {

	public static Facebook_profileDAO getProfile(long uid) {

		String pidprofilequery = "select * from facebook_user where uid='"
				+ uid + "'";

		try {
			Facebook_profileDAO[] profile = (Facebook_profileDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Facebook_profileDAO.class);
			if (profile.length > 0) {
				return profile[0];
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static AppUserDAO getAppUserTable(long uid) {

		String pidprofilequery = "select * from facebook_appuser where user_id='"
				+ uid + "'";

		try {
			AppUserDAO[] profile = (AppUserDAO[]) new SmartCampusDAO().query(
					pidprofilequery, AppUserDAO.class);
			if (profile.length > 0) {
				return profile[0];
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static AppUserDAO[] getAppUsers() {

		String pidprofilequery = "select * from facebook_appuser";

		try {
			AppUserDAO[] profile = (AppUserDAO[]) new SmartCampusDAO().query(
					pidprofilequery, AppUserDAO.class);
			if (profile.length > 0) {
				return profile;
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean saveFriend(int uid, int fid) {
		String fofInsert = "INSERT INTO facebook_friends (uid,fid)  VALUES(?,?)";
		String fofupdate = "No Update";
		int rowsUpdated = 0;
		try {
			Object[] params = new Object[] { new Long(uid), // userid1
					new Long(fid) // userid2
			};
			// Invoke update method on DAO to perform insert

			rowsUpdated = new SmartCampusDAO().update(fofInsert, params);

			if (rowsUpdated > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean saveGroup(long uid, long gid) {
		String fofInsert = "INSERT INTO facebook_group (uid,gid)  VALUES(?,?)";
		String fofupdate = "No Update";
		int rowsUpdated = 0;
		try {
			Object[] params = new Object[] { new Long(uid), // userid1
					new Long(gid) // userid2
			};
			// Invoke update method on DAO to perform insert

			rowsUpdated = new SmartCampusDAO().update(fofInsert, params);

			if (rowsUpdated > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}
		return false;
	}

	public static boolean saveProfile(FacebookProfileWrapper f) {
		String fofInsert = "INSERT INTO facebook_user (uid,about_me,activities,books,first_name,has_added_app,interests,is_app_user,last_name, meeting_for,meeting_sex,movies,music,name,notes_count,political,relationship_status,religion,significant_other_id,sex,status,timezone,tv,wall_count)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String fofupdate = "No Update";
		try {
			Object[] params = new Object[] {
					new Long(f.getUid()), // userid1
					f.getAbout_me(), f.getActivities(), f.getBooks(),
					f.getFirst_name(), f.getHas_added_app(), f.getInterests(),
					f.getIs_app_user(), f.getLast_name(), f.getMeeting_for(),
					f.getMeeting_sex(), f.getMovies(), f.getMusic(),
					f.getName(), f.getNotes_count(), f.getPolitical(),
					f.getRelationship_status(), f.getReligion(),
					f.getSignificant_other_id(), f.getSex(), f.getStatus(),
					f.getTimezone(), f.getTv(), f.getWall_count() };
			// Invoke update method on DAO to perform insert
			int rowsUpdated;
			try {
				rowsUpdated = new SmartCampusDAO().update(fofInsert, params);
			} catch (Exception e) {
				// e.printStackTrace();
				String del = "Delete from facebook_user where uid='"
						+ f.getUid() + "'";
				rowsUpdated = new SmartCampusDAO().update(del, null);
				rowsUpdated = new SmartCampusDAO().update(fofInsert, params);
				if (rowsUpdated > 0) {
					return true;
				}
			}
			Log.put("rowsUpdated", "=" + rowsUpdated);
			if (rowsUpdated > 0) {
				return true;
			}

		}

		catch (CoreDaoException e) {
			System.out.println(fofupdate);
			e.printStackTrace();
		}
		return false;
	}

	public static boolean addAppUser(int uid) {
		String fofInsert = "INSERT INTO facebook_appuser (user_id)  VALUES(?)";

		int rowsUpdated = 0;
		try {
			Object[] params = new Object[] { new Long(uid) // userid1

			};
			// Invoke update method on DAO to perform insert

			rowsUpdated = new SmartCampusDAO().update(fofInsert, params);

			if (rowsUpdated > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	public static boolean updateLastUpdate(int uid) {
		String query = "UPDATE facebook_appuser SET lastupdate = ?" + "WHERE user_id = '" + uid
				+ "'";
		System.out.println(query);
		try {
			Object[] params = new Object[] {new Timestamp(System.currentTimeMillis())};
			int rowsUpdated = new SmartCampusDAO().update(query, params);

			if (rowsUpdated > 0) {
				return true;
			}
		} catch (CoreDaoException e) {
			System.out.println(query);
			e.printStackTrace();
		}
		return false;
	}
}

// database order
// about_me
// activities
// books
// first_name
// has_added_app
// interests
// is_app_user
// last_name
// meeting_for
// meeting_sex
// movies
// music
// name
// notes_count
// political
// relationship_status
// religion
// significant_other_id
// sex
// status
// timezone
// tv
// wall_count

// about_me,
// activities,
// books,
// first_name,
// has_added_app,
// interests,
// is_app_user,
// last_name,
// meeting_for,
// meeting_sex,
// movies,
// music,
// name,
// notes_count,
// political,
// relationship_status,
// religion,
// significant_other_id,
// sex,
// status,
// timezone,
// tv,
// wall_count,

