package dataObjects.dataMiningService;

import core.data.dto.CoreDto;


public class Database_Count extends CoreDto {

	private static final long serialVersionUID = 1L;

	/* Define *public*  members with names matching columns in fof_user table */
	public int count;
	
	public Database_Count() {
		super();
	}

	public Database_Count (int count)
    {
		this();
		this.count = count;
	}
}
