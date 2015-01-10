package interfaces;

import components.User;

public interface ILobbyLoggedIn {

	/**
	 * This method creates and returns the interface IGameWaiting.
	 * @param type This defines the type of game.
	 * Types: "SingleFun", "SingleCompetition", "MultiFun", "MultiCompetition".
	 * @param user The user who created the game (will be the 1st Player/Host Player).
	 */
	public IWaitingGame createGame(String type, User user);

	/**
	 * A method that lets other players join an existing game. Returns the IGameWaiting interface.
	 * @param game The game that the user wants to join.
	 * @param user The user who wants to join the game (will be the next Player).
	 */
	public IWaitingGame joinGame(IWaitingGame game, User user);

	/**
	 * This method will null the user component (log it off) and return the ILobbyNotLoggedIn interface
	 */
	public ILobbyNotLoggedIn logout();
}