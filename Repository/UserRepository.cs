using ANI.Models;
using Microsoft.EntityFrameworkCore;

namespace ANI.Repository;

/// <summary>
/// Repository for managing user data.
/// </summary>
public class UserRepository(AniContext context) : IUserRepository
{
    private readonly AniContext _context = context;

    /// <summary>
    /// Gets all users.
    /// </summary>
    /// <returns>A list of users.</returns>
    public async Task<IEnumerable<User>> GetUsers() => 
                    await _context.Users.ToListAsync();

    /// <summary>
    /// Gets a user by their unique identifier.
    /// </summary>
    /// <param name="id">The unique identifier of the user.</param>
    /// <returns>The user with the specified identifier.</returns>
    /// <exception cref="KeyNotFoundException">Thrown when a user with the specified identifier is not found.</exception>
    public async Task<User> GetUser(Guid id) => 
                    await _context.Users.FindAsync(id) ?? throw new KeyNotFoundException($"User with id {id} not found.");

    /// <summary>
    /// Gets a user by their username.
    /// </summary>
    /// <param name="username">The username of the user.</param>
    /// <returns>The user with the specified username.</returns>
    /// <exception cref="KeyNotFoundException">Thrown when a user with the specified username is not found.</exception>
    public async Task<User> GetUser(string username) => 
                    await _context.Users.SingleOrDefaultAsync(u => u.Username == username) ?? throw new KeyNotFoundException($"User with username {username} not found.");

    /// <summary>
    /// Creates a new user.
    /// </summary>
    /// <param name="user">The user to create.</param>
    /// <returns>The created user.</returns>
    public async Task<User> CreateUser(User user)
    {
        _context.Users.Add(user);

        await _context.SaveChangesAsync();

        return user;
    }

    /// <summary>
    /// Updates an existing user.
    /// </summary>
    /// <param name="userUpdate">The user with updated information.</param>
    /// <returns>The updated user.</returns>
    public async Task<User> UpdateUser(User userUpdate)
    {
        _context.Entry(userUpdate).State = EntityState.Modified;

        await _context.SaveChangesAsync();

        return userUpdate;
    }

    /// <summary>
    /// Deletes a user by their unique identifier.
    /// </summary>
    /// <param name="id">The unique identifier of the user to delete.</param>
    /// <returns>The deleted user.</returns>
    /// <exception cref="KeyNotFoundException">Thrown when a user with the specified identifier is not found.</exception>
    public async Task<User> DeleteUser(Guid id)
    {
        User user = await _context.Users.FindAsync(id) ?? throw new KeyNotFoundException($"User with id {id} not found.");

        _context.Users.Remove(user);
        await _context.SaveChangesAsync();

        return user;
    }
}