package interfaces;

public interface ILobbyNotLoggedIn {

	/**
	 * In this function a User instance will be created according to the parameter information, and saved. Also it will return the ILobbyLoggedIn interface.
	 * @param username The username of the user.
	 * @param password The password of the user.
	 */
	public ILobbyLoggedIn login(String username, String password);

	/**
	 * This method will attempt to register a new user. It returns the ILobbyLoggedIn interface.
	 * @param username The username of the new user. This needs to be a unique name.
	 * @param password The password of the new user. There are no restrictions to this.
	 */
	public ILobbyLoggedIn register(String username, String email, String password);
}