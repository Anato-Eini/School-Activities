using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using ANI.Mappings;
using AutoMapper;

namespace ANI.Services;
public class UserService(IUserRepository userRepository, IMapper mapper) : IUserService
{
    private readonly IUserRepository _userRepository = userRepository;
    private readonly IMapper _mapper = mapper;

    public async Task<IEnumerable<UserResponseDTO>> GetUsers()
    {
        return _mapper.Map<IEnumerable<UserResponseDTO>>(await _userRepository.GetUsers());
    }

    public async Task<UserResponseDTO> GetUser(int id)
    {
        return _mapper.Map<UserResponseDTO>(await _userRepository.GetUserById(id));
    }

    public async Task<UserResponseDTO> CreateUser(UserCreateDTO user)
    {
        return _mapper.Map<UserResponseDTO>(await _userRepository.CreateUser(_mapper.Map<User>(user)));
    }
    public async Task<UserResponseDTO> UpdateUser(int id, UserCreateDTO user)
    {
        return _mapper.Map<UserResponseDTO>(await _userRepository.UpdateUser(_mapper.Map<User>(user)));
    }
    public async Task<UserResponseDTO> DeleteUser(int id)
    {
        return _mapper.Map<UserResponseDTO>(await _userRepository.DeleteUser(id));
    }
}