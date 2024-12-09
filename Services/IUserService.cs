using ANI.DTO;

namespace ANI.Services;

public interface IUserService
{
    public Task<IEnumerable<UserResponseDTO>> GetUsers();
    public Task<UserResponseDTO> GetUser(string username);
    public Task<UserResponseDTO> GetUser(Guid id);
    public Task<UserResponseDTO> Authenticate(UserLoginDTO userLoginDTO);
    public Task<UserResponseDTO> CreateUser(UserCreateDTO user);
    public Task<UserResponseDTO> UpdateUser(UserUpdateDTO newUser, Guid guid);
    public Task<UserResponseDTO> DeleteUser(int id);
}