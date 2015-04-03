package dataObjects.privacyService;

/**
 * ReturnAccessInfo class
 * NOTE: This class holds all the elements that needs to be returned to the service or module calling into Access Control module.
 * @author NeerajR.
 *
 */

public class ReturnAccessInfo {

	public Boolean IsAccessAllowed;
	public Boolean ShouldPrimaryReceiveMsg;
	public Boolean ShouldSecondaryReceiveMsg;
	public Boolean ShouldSystemMsgGetLogged;
	public String MessageToPrimary;
	public String MessageToSecondary;
	public String MessageSystemShouldLog;
}
