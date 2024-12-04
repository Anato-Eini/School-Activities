using ANI.DTO;

namespace ANI.Services;

public interface IUserService
{
    public Task<IEnumerable<UserDTO>> GetUsers();
    public Task<UserDTO> GetUser(int id);
    public Task<UserDTO> CreateUser(UserDTO user);
    public Task<UserDTO> UpdateUser(int id, UserDTO user);
    public Task<UserDTO> DeleteUser(int id);
}