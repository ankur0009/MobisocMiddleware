package dataObjects.dataMiningService;

import core.data.dto.CoreDto;

public class all_users_and_visit_count extends CoreDto{
	/**
	 * This is a generic class used to get any kind of long value from from the
	 * database 
	 * @author Maverick
	 */
		private static final long serialVersionUID = 1L;
		public long userid;
		public long visitcount;
		
		public all_users_and_visit_count()
		{
			super();
		}
		
		public all_users_and_visit_count(long x_userId, long x_visitCount)
		{
			this();
			userid = x_userId;
			visitcount = x_visitCount;
		}
		
}
