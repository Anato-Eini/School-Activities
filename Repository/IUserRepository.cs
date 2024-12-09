using ANI.Models;

namespace ANI.Repository;

public interface IUserRepository
{
    public Task<IEnumerable<User>> GetUsers();
    public Task<User> GetUser(Guid id);
    public Task<User> GetUser(string username);
    public Task<User> CreateUser(User user);
    public Task<User> UpdateUser(User user);
    public Task<User> DeleteUser(int id);
}