package serviceFunctionality.socialStateLearning.Matching.Facebook;


import core.data.dao.CoreDaoException;
import dataObjects.socialStateLearning.Matching.Facebook_profileDAO;
import dataObjects.socialStateLearning.Matching.Gis_SpaceDAO;
import dataObjects.socialStateLearning.Matching.facebook_elementratingsDAO;
import edu.njit.sc.core.dao.SmartCampusDAO;

public class FacebookDataBaseQuery {

	/**
	 * @param uid1
	 * @param uid2
	 * @return Presence count for uid1,uid2 minimum of (location uid1,location
	 *         uid2, where uid1.location=uid2.location)
	 */
	public static int getTotalSpaceCount(long uid1, long uid2) {

		String pidprofilequery = "select sum(tb3.mn) as sum from (select tb1.name,min (tb1.cm) as mn from ((select sp.name as name,count(*) as cm from gis_space sp, gis_location loc where sp.type='Building' and loc.user_id='"
				+ uid1
				+ "' and contains ((select location from gis_space sp2 where sp.space_id=sp2.space_id),(select location from gis_location loc2 where loc2.time=loc.time and loc2.user_id='"
				+ uid1
				+ "')) group by sp.name) union (select sp.name as name,count(*) as cm from gis_space sp, gis_location loc where sp.type='Building' and loc.user_id='"
				+ uid2
				+ "' and contains ((select location from gis_space sp2 where sp.space_id=sp2.space_id),(select location from gis_location loc2 where loc2.time=loc.time and loc2.user_id='"
				+ uid2
				+ "')) group by sp.name)) as tb1,(select g1.name,tb1.cm,tb2.cm  from gis_space g1, (select sp.name as name,count(*) as cm from gis_space sp, gis_location loc where sp.type='Building' and loc.user_id='"
				+ uid1
				+ "' and contains ((select location from gis_space sp2 where sp.space_id=sp2.space_id),(select location from gis_location loc2 where loc2.time=loc.time and loc2.user_id='"
				+ uid1
				+ "')) group by sp.name) as tb1,(select sp.name as name,count(*) as cm from gis_space sp, gis_location loc where sp.type='Building' and loc.user_id='"
				+ uid2
				+ "' and contains ((select location from gis_space sp2 where sp.space_id=sp2.space_id),(select location from gis_location loc2 where loc2.time=loc.time and loc2.user_id='"
				+ uid2
				+ "')) group by sp.name) as tb2 where tb1.name=tb2.name and tb1.name=g1.name  order by g1.name) as tb2 where tb1.name=tb2.name group by tb1.name order by name) as tb3";

		try {
			Gis_SpaceDAO[] profile = (Gis_SpaceDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Gis_SpaceDAO.class);
			if (profile.length > 0) {
				return profile[0].sum.intValue();
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * @param uid1
	 * @return number of location points for uid1 when uid1 is inside a building
	 */
	public static int getTotalSpaceCount(long uid1) {

		String pidprofilequery = "select sum(tb1.sm) as sum from (select count(*) as sm from gis_space sp, gis_location loc where sp.type='Building' and loc.user_id='"
				+ uid1
				+ "' and contains (sp.location,loc.location) group by sp.name order by sp.name) as tb1";

		try {
			Gis_SpaceDAO[] profile = (Gis_SpaceDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Gis_SpaceDAO.class);
			if (profile.length > 0) {
				return profile[0].sum.intValue();
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * @param uid1
	 * @param uid2
	 * @return location update count when both uid1 and uid2 were online
	 *         together and location.type=building
	 */
	public static int getCountOnlineTogether(long uid1, long uid2) {

		String pidprofilequery = "select count(*) as count from (select distinct on(loc1.time) loc1.time,loc2.time,sp.name,sp2.name,loc2.time-loc1.time  from gis_location loc1,gis_location loc2,gis_space sp,gis_space sp2 where loc1.user_id='"
				+ uid1
				+ "' and loc2.user_id='"
				+ uid2
				+ "' and interval '-1 min' < loc2.time-loc1.time and  loc2.time-loc1.time < interval '1 min' and contains(sp.location,loc1.location) and contains(sp2.location,loc2.location) and sp.type='Building' and sp2.type='Building') as tb1";

		try {
			Gis_SpaceDAO[] profile = (Gis_SpaceDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Gis_SpaceDAO.class);
			if (profile.length > 0) {
				return profile[0].count.intValue();
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * @param uid1
	 * @param uid2
	 * @return co presence count, when uid1 and uid2 are together in a building
	 */
	public static int getCountCopresense(long uid1, long uid2) {

		String pidprofilequery = "select count(*) as count from (select distinct on(loc1.time) loc1.time,loc2.time,sp.name,sp2.name,loc2.time-loc1.time  from gis_location loc1,gis_location loc2,gis_space sp,gis_space sp2 where loc1.user_id='"
				+ uid1
				+ "' and loc2.user_id='"
				+ uid2
				+ "' and interval '-1 min' < loc2.time-loc1.time and  loc2.time-loc1.time < interval '1 min' and contains(sp.location,loc1.location) and contains(sp2.location,loc2.location) and sp.type='Building' and sp2.type='Building' and sp.space_id=sp2.space_id) as tb1";

		try {
			Gis_SpaceDAO[] profile = (Gis_SpaceDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Gis_SpaceDAO.class);
			if (profile.length > 0) {
				return profile[0].count.intValue();
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static Gis_SpaceDAO[] getSpaceCount(long uid1, long uid2) {

		String pidprofilequery = "select tb1.name,min (tb1.cm) as count from "
				+ "((select sp.name as name,count(*) as cm from gis_space sp, gis_location "
				+ "loc where sp.type='Building' and loc.user_id='"
				+ uid1
				+ "' and contains "
				+ "((select location from gis_space sp2 where sp.space_id=sp2.space_id),"
				+ "(select location from gis_location loc2 where loc2.time=loc.time and "
				+ "loc2.user_id='"
				+ uid1
				+ "')) group by sp.name) union (select sp.name as "
				+ "name,count(*) as cm from gis_space sp, gis_location loc where sp.type='Building' "
				+ "and loc.user_id='"
				+ uid2
				+ "' and contains ((select location from gis_space sp2 "
				+ "where sp.space_id=sp2.space_id),(select location from gis_location loc2 "
				+ "where loc2.time=loc.time and loc2.user_id='"
				+ uid2
				+ "')) group by sp.name)) "
				+ "as tb1,(select g1.name,tb1.cm,tb2.cm  from gis_space g1, (select sp.name"
				+ " as name,count(*) as cm from gis_space sp, gis_location loc "
				+ "where sp.type='Building' and loc.user_id='"
				+ uid1
				+ "' and contains "
				+ "((select location from gis_space sp2 where sp.space_id=sp2.space_id),"
				+ "(select location from gis_location loc2 where loc2.time=loc.time and "
				+ "loc2.user_id='"
				+ uid1
				+ "')) group by sp.name) as tb1,"
				+ "(select sp.name as name,count(*) as cm from gis_space sp, "
				+ "gis_location loc where sp.type='Building' and loc.user_id='"
				+ uid2
				+ "'"
				+ " and contains ((select location from gis_space sp2 "
				+ "where sp.space_id=sp2.space_id),(select location from gis_location"
				+ " loc2 where loc2.time=loc.time and loc2.user_id='"
				+ uid2
				+ "')) group by sp.name)"
				+ " as tb2 where tb1.name=tb2.name and tb1.name=g1.name  order by g1.name)"
				+ "as tb2 where tb1.name=tb2.name group by tb1.name order by name";

		try {
			Gis_SpaceDAO[] profile = (Gis_SpaceDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Gis_SpaceDAO.class);
			if (profile.length > 0) {
				return profile;
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Facebook_profileDAO[] getGroups(long uid) {

		String pidprofilequery = "select * from facebook_group where uid='"
				+ uid + "'";

		try {
			Facebook_profileDAO[] profile = (Facebook_profileDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Facebook_profileDAO.class);
			if (profile.length > 0) {
				return profile;
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static facebook_elementratingsDAO[] getFacebookElementRatings(
			long uid, int typeid) {

		String pidprofilequery = "select * from facebook_elementratings where user_id='"
				+ uid + "' and typeid='" + typeid + "' and score >0";

		try {
			facebook_elementratingsDAO[] profile = (facebook_elementratingsDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, facebook_elementratingsDAO.class);
			if (profile.length > 0) {
				return profile;
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static facebook_elementratingsDAO[] getCommonFacebookElementRatings(
			long uid, long fid, int typeid) {

		String pidprofilequery = "select t1.name,t1.score,t2.score as score2 from facebook_elementratings t1,facebook_elementratings t2 where t1.user_id='"
				+ uid
				+ "' and t1.typeid='"
				+ typeid
				+ "' and t1.score >0 and t2.user_id='"
				+ fid
				+ "' and t2.typeid='"
				+ typeid
				+ "' and t2.score >0 and t1.name=t2.name";

		try {
			facebook_elementratingsDAO[] profile = (facebook_elementratingsDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, facebook_elementratingsDAO.class);
			if (profile.length > 0) {
				return profile;
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Facebook_profileDAO[] getFriends(long uid) {

		String pidprofilequery = "select * from facebook_friends where uid='"
				+ uid + "'";

		try {
			Facebook_profileDAO[] profile = (Facebook_profileDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Facebook_profileDAO.class);
			if (profile.length > 0) {
				return profile;
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Facebook_profileDAO[] getCommonFriends(long uid, long fid) {

		String pidprofilequery = "select t1.fid from facebook_friends t1 where t1.uid='"
				+ uid
				+ "' and t1.fid IN (select fid from facebook_friends where uid='"
				+ fid + "')";

		try {
			Facebook_profileDAO[] profile = (Facebook_profileDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Facebook_profileDAO.class);
			if (profile.length > 0) {
				return profile;
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Facebook_profileDAO[] getCommonGroups(long uid, long fid) {

		String pidprofilequery = "select t1.gid from facebook_group t1 where t1.uid='"
				+ uid
				+ "' and t1.gid IN (select gid from facebook_group where uid='"
				+ fid + "')";

		try {
			Facebook_profileDAO[] profile = (Facebook_profileDAO[]) new SmartCampusDAO()
					.query(pidprofilequery, Facebook_profileDAO.class);
			if (profile.length > 0) {
				return profile;
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return null;
	}
}
