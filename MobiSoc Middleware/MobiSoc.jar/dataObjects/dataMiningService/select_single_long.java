package dataObjects.dataMiningService;

import core.data.dto.CoreDto;

public class select_single_long  extends CoreDto{
	/**
	 * This is a generic class used to get any kind of long value from from the
	 * database 
	 * @author Maverick
	 */
		private static final long serialVersionUID = 1L;
		public long field;
		
		public select_single_long()
		{
			super();
		}
		
		public select_single_long(long x_field)
		{
			this();
			field = x_field;
		}
		
}
