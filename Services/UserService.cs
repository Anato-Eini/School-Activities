using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using AutoMapper;
using Microsoft.AspNetCore.Identity;

namespace ANI.Services;

public class UserService(IUserRepository userRepository, IMapper mapper, IPasswordHasher<User> passwordHasher) : IUserService
{
    private readonly IUserRepository _userRepository = userRepository;
    private readonly IMapper _mapper = mapper;
    private readonly IPasswordHasher<User> _passwordHasher = passwordHasher;

    public async Task<IEnumerable<UserResponseDTO>> GetUsers()
    {
        return _mapper.Map<IEnumerable<UserResponseDTO>>(await _userRepository.GetUsers());
    }

    public async Task<UserResponseDTO> GetUser(int id)
    {
        return _mapper.Map<UserResponseDTO>(await _userRepository.GetUser(id));
    }

    public async Task<UserResponseDTO> GetUser(UserLoginDTO userLoginDTO)
    {
        try
        {
            User userFetch = await _userRepository.GetUser(userLoginDTO.Username);

            if (_passwordHasher.VerifyHashedPassword(userFetch, userFetch.Password, userLoginDTO.Password) == PasswordVerificationResult.Failed)
                throw new KeyNotFoundException("Invalid password.");

            return _mapper.Map<UserResponseDTO>(userFetch);
        } catch (KeyNotFoundException e)
        {
            throw new KeyNotFoundException(e.Message);
        }
    }

    public async Task<UserResponseDTO> CreateUser(UserCreateDTO user)
    {
        User userModel = _mapper.Map<User>(user);

        userModel.Password = _passwordHasher.HashPassword(userModel, userModel.Password);

        return _mapper.Map<UserResponseDTO>(await _userRepository.CreateUser(_mapper.Map<User>(userModel)));
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