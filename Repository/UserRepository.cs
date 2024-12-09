using ANI.Models;
using Microsoft.EntityFrameworkCore;

namespace ANI.Repository;

public class UserRepository(AniContext context) : IUserRepository
{
    private readonly AniContext _context = context;

    public async Task<IEnumerable<User>> GetUsers()
    {
        return await _context.Users.ToListAsync();
    }

    public async Task<User> GetUser(Guid id)
    {
        return await _context.Users.FindAsync(id) ?? throw new KeyNotFoundException($"User with id {id} not found.");
    }

    public async Task<User> GetUser(string username)
    {
        return await _context.Users.SingleOrDefaultAsync(u => u.Username == username) ?? throw new KeyNotFoundException($"User with username {username} not found.");
    }

    public async Task<User> CreateUser(User user)
    {
        _context.Users.Add(user);

        await _context.SaveChangesAsync();

        return user;
    }

    public async Task<User> UpdateUser(User userUpdate)
    {
        _context.Entry(userUpdate).State = EntityState.Modified;

        await _context.SaveChangesAsync();

        return userUpdate;
    }

    public async Task<User> DeleteUser(Guid id)
    {
        User user = await _context.Users.FindAsync(id) ?? throw new KeyNotFoundException($"User with id {id} not found.");

        _context.Users.Remove(user);
        await _context.SaveChangesAsync();

        return user;
    }
}