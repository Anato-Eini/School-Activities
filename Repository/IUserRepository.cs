using ANI.DTO;
using ANI.Models;

namespace ANI.Repository;

public interface IUserRepository
{
    public Task<IEnumerable<User>> GetUsers();
    public Task<User> GetUserById(int id);
    public Task<User> CreateUser(UserCreateDTO user);
    public Task<User> UpdateUser(UserCreateDTO user);
    public Task<User> DeleteUser(int id);
}