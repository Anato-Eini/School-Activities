using ANI.Models;

namespace ANI.Repository;

/// <summary>
/// Interface for user repository operations.
/// </summary>
public interface IUserRepository
{
    /// <summary>
    /// Gets all users.
    /// </summary>
    /// <returns>A task that represents the asynchronous operation. The task result contains an enumerable of users.</returns>
    Task<IEnumerable<User>> GetUsers();

    Task<IEnumerable<User>> GetFarmers();

    /// <summary>
    /// Gets a user by their unique identifier.
    /// </summary>
    /// <param name="id">The unique identifier of the user.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the user.</returns>
    Task<User> GetUser(Guid id);

    /// <summary>
    /// Gets a user by their username.
    /// </summary>
    /// <param name="username">The username of the user.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the user.</returns>
    Task<User> GetUser(string username);

    /// <summary>
    /// Creates a new user.
    /// </summary>
    /// <param name="user">The user to create.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the created user.</returns>
    Task<User> CreateUser(User user);

    /// <summary>
    /// Updates an existing user.
    /// </summary>
    /// <param name="user">The user to update.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the updated user.</returns>
    Task<User> UpdateUser(User user);

    /// <summary>
    /// Deletes a user by their unique identifier.
    /// </summary>
    /// <param name="id">The unique identifier of the user to delete.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the deleted user.</returns>
    Task<User> DeleteUser(Guid id);
}