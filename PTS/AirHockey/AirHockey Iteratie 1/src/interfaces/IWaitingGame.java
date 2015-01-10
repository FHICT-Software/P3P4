package interfaces;

public interface IWaitingGame {

	/**
	 * This method will start a game and return the IStartedGame interface.
	 */
	public IStartedGame start();

	/**
	 * This method will stop the game (nullify the component) and return the ILobbyLoggedIn interface.
	 */
	public ILobbyLoggedIn cancel();
}