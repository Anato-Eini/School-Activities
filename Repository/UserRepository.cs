using ANI.Models;
using Microsoft.EntityFrameworkCore;

namespace ANI.Repository;

public class UserRepository(AniContext context) : IUserRepository
{
    private readonly AniContext _context = context;

    {
        return await _context.Users.ToListAsync();
    }

    public async Task<User> GetUser(int id)
    {
        return await _context.Users.FindAsync(id) ?? throw new KeyNotFoundException($"User with id {id} not found.");
    }

    public async Task<User> GetUser(string username)
    {
        return await _context.Users.FirstOrDefaultAsync(u => u.Username == username) ?? throw new KeyNotFoundException($"User with username {username} not found.");
    }

    public async Task<User> CreateUser(User user)
    {
        _context.Users.Add(user);

        await _context.SaveChangesAsync();

        return user;
    }

    public async Task<User> UpdateUser(User userUpdate)
    {
        User user = await _context.Users.FindAsync(userUpdate.Username) ?? throw new KeyNotFoundException($"User with username {userUpdate.Username} not found.");

        user.Username = userUpdate.Username;
        user.Password = userUpdate.Password;
        user.FirstName = userUpdate.FirstName;
        user.LastName = userUpdate.LastName;
        user.PhoneNumber = userUpdate.PhoneNumber;
        user.Address = userUpdate.Address;

        _context.Entry(user).State = EntityState.Modified;

        await _context.SaveChangesAsync();

        return user;
    }

    public async Task<User> DeleteUser(int id)
    {
        User user = await _context.Users.FindAsync(id) ?? throw new KeyNotFoundException($"User with id {id} not found.");

        _context.Users.Remove(user);
        await _context.SaveChangesAsync();

        return user;
    }
}