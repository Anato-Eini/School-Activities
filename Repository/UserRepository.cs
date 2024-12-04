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

    public async Task<User> GetUserById(int id)
    {
        return await _context.Users.FindAsync(id) ?? throw new KeyNotFoundException($"User with id {id} not found.");
    }

    public async Task<User> CreateUser(User user)
    {
        _context.Users.Add(user);
        await _context.SaveChangesAsync();
        return user;
    }

    public async Task<User> UpdateUser(User user)
    {
        _context.Entry(user).State = EntityState.Modified;
        await _context.SaveChangesAsync();
        return user;
    }

    public async Task<User> DeleteUser(int id)
    {
        var user = await _context.Users.FindAsync(id) ?? throw new KeyNotFoundException($"User with id {id} not found.");
        _context.Users.Remove(user);
        await _context.SaveChangesAsync();
        return user;
    }
}