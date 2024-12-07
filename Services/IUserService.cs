using ANI.DTO;

namespace ANI.Services;

public interface IUserService
{
    public Task<IEnumerable<UserResponseDTO>> GetUsers();
    public Task<UserResponseDTO> GetUser(int id);
    public Task<string> Authenticate(UserLoginDTO userLoginDTO);
    public Task<UserResponseDTO> CreateUser(UserCreateDTO user);
    public Task<UserResponseDTO> UpdateUser(int id, UserCreateDTO user);
    public Task<UserResponseDTO> DeleteUser(int id);
}