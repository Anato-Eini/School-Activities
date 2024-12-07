using ANI.DTO;
using ANI.Models;
using ANI.Repository;

namespace ANI.Services;
public class UserService(IUserRepository userRepository) : IUserService
{
    private readonly IUserRepository _userRepository = userRepository;

    public async Task<IEnumerable<UserResponseDTO>> GetUsers()
    {
        List<UserResponseDTO> users = (await _userRepository.GetUsers()).Select(user => new UserResponseDTO
        {
            Username = user.Username,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        }).ToList();

        return users;
    }
    public async Task<UserResponseDTO> GetUser(int id)
    {
        User user = await _userRepository.GetUserById(id);
        return new UserResponseDTO
        {
            Username = user.Username,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        };
    }
    public async Task<UserResponseDTO> CreateUser(UserCreateDTO  user)
    {
        User userMade = await _userRepository.CreateUser(new User{
            Username = user.Username,
            Password = user.Password,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        });

        return new UserResponseDTO
        {
            Username = userMade.Username,
            FirstName = userMade.FirstName,
            LastName = userMade.LastName,
            PhoneNumber = userMade.PhoneNumber,
            Address = userMade.Address
        };
    }
    public async Task<UserResponseDTO> UpdateUser(int id, UserCreateDTO user)
    {
        User userUpdated = await _userRepository.UpdateUser(new User{
            Username = user.Username,
            Password = user.Password,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        });

        return new UserResponseDTO
        {
            Username = userUpdated.Username,
            FirstName = userUpdated.FirstName,
            LastName = userUpdated.LastName,
            PhoneNumber = userUpdated.PhoneNumber,
            Address = userUpdated.Address
        };
    }
    public async Task<UserResponseDTO> DeleteUser(int id)
    {
        User userdeleted = await _userRepository.DeleteUser(id);

        return new UserResponseDTO
        {
            Username = userdeleted.Username,
            FirstName = userdeleted.FirstName,
            LastName = userdeleted.LastName,
            PhoneNumber = userdeleted.PhoneNumber,
            Address = userdeleted.Address
        };
    }
}