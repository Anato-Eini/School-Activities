using ANI.DTO;
using ANI.Models;
using ANI.Repository;

namespace ANI.Services;
public class UserService(IUserRepository userRepository) : IUserService
{
    private readonly IUserRepository _userRepository = userRepository;

    public async Task<IEnumerable<UserDTO>> GetUsers()
    {
        List<UserDTO> users = new();
        foreach (var user in await _userRepository.GetUsers())
        {
            users.Add(new UserDTO
            {
                Username = user.Username,
                FirstName = user.FirstName,
                LastName = user.LastName,
                PhoneNumber = user.PhoneNumber,
                Address = user.Address
            });
        }

        return users;
    }
    public async Task<UserDTO> GetUser(int id)
    {
        User user = await _userRepository.GetUserById(id);
        return new UserDTO
        {
            Username = user.Username,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        };
    }
    public async Task<UserDTO> CreateUser(UserDTO user)
    {
        User useMade = await _userRepository.CreateUser(new User
        {
            Username = user.Username,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        });

        return new UserDTO
        {
            Username = useMade.Username,
            FirstName = useMade.FirstName,
            LastName = useMade.LastName,
            PhoneNumber = useMade.PhoneNumber,
            Address = useMade.Address
        };
    }
    public async Task<UserDTO> UpdateUser(int id, UserDTO user)
    {
        User userUpdated = await _userRepository.UpdateUser(new User
        {
            Username = user.Username,
            FirstName = user.FirstName,
            LastName = user.LastName,
            PhoneNumber = user.PhoneNumber,
            Address = user.Address
        });

        return new UserDTO
        {
            Username = userUpdated.Username,
            FirstName = userUpdated.FirstName,
            LastName = userUpdated.LastName,
            PhoneNumber = userUpdated.PhoneNumber,
            Address = userUpdated.Address
        };
    }
    public async Task<UserDTO> DeleteUser(int id)
    {
        User userdeleted = await _userRepository.DeleteUser(id);

        return new UserDTO
        {
            Username = userdeleted.Username,
            FirstName = userdeleted.FirstName,
            LastName = userdeleted.LastName,
            PhoneNumber = userdeleted.PhoneNumber,
            Address = userdeleted.Address
        };
    }
}