using ANI.DTO;
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

    public async Task<User> CreateUser(UserCreateDTO user)
    {
        _context.Users.Add(new User{
            Username = user.Username,
            Password = user.Password,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        });

        await _context.SaveChangesAsync();

        return new User
        {
            Username = user.Username,
            Password = user.Password,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        };
    }

    public async Task<User> UpdateUser(UserCreateDTO userDto)
    {
        User user = await _context.Users.FindAsync(userDto.Username) ?? throw new KeyNotFoundException($"User with username {userDto.Username} not found.");
        user.Username = userDto.Username;
        user.Password = userDto.Password;
        user.FirstName = userDto.FirstName;
        user.LastName = userDto.LastName;
        user.PhoneNumber = userDto.PhoneNumber;
        user.Address = userDto.Address;

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