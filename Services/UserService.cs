using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using AutoMapper;
using Microsoft.AspNetCore.Identity;
using ANI.Libraries;

namespace ANI.Services;

/// <summary>
/// Service class for managing user-related operations.
/// </summary>
public class UserService(IUserRepository userRepository, IMapper mapper, IPasswordHasher<User> passwordHasher) : IUserService
{
    private readonly IUserRepository _userRepository = userRepository;
    private readonly IMapper _mapper = mapper;
    private readonly IPasswordHasher<User> _passwordHasher = passwordHasher;

    /// <summary>
    /// Retrieves all users.
    /// </summary>
    /// <returns>A task that represents the asynchronous operation. The task result contains a collection of UserSecDTO.</returns>
    public async Task<IEnumerable<UserSecDTO>> GetUsers() => _mapper.Map<IEnumerable<UserSecDTO>>(await _userRepository.GetUsers()).Select(user =>
                                                                  {
                                                                      user.ProfilePictureUrl = Library.PrependUrl(user.ProfilePictureUrl)!;
                                                                      return user;
                                                                  });

    /// <summary>
    /// Retrieves a user by username.
    /// </summary>
    /// <param name="username">The username of the user.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    public async Task<UserResponseDTO> GetUser(string username)
    {
        UserResponseDTO userResponseDTO = _mapper.Map<UserResponseDTO>(await _userRepository.GetUser(username));
        userResponseDTO.ProfilePictureUrl = Library.PrependUrl(userResponseDTO.ProfilePictureUrl)!;

        return userResponseDTO;
    }

    /// <summary>
    /// Retrieves a user by ID.
    /// </summary>
    /// <param name="id">The ID of the user.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    public async Task<UserResponseDTO> GetUser(Guid id)
    {
        UserResponseDTO userResponseDTO = _mapper.Map<UserResponseDTO>(await _userRepository.GetUser(id));
        userResponseDTO.ProfilePictureUrl = Library.PrependUrl(userResponseDTO.ProfilePictureUrl)!;

        return userResponseDTO;
    }

    /// <summary>
    /// Authenticates a user.
    /// </summary>
    /// <param name="userLoginDTO">The user login data transfer object.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    public async Task<UserResponseDTO> Authenticate(UserLoginDTO userLoginDTO)
    {
        try
        {
            User userFetch = await _userRepository.GetUser(userLoginDTO.Username);

            if (_passwordHasher.VerifyHashedPassword(userFetch, userFetch.Password, userLoginDTO.Password) == PasswordVerificationResult.Failed)
                throw new KeyNotFoundException("Invalid password.");

            UserResponseDTO userResponseDTO = _mapper.Map<UserResponseDTO>(userFetch);
            userResponseDTO.ProfilePictureUrl = Library.PrependUrl(userFetch.ProfilePictureUrl)!;

            return _mapper.Map<UserResponseDTO>(userResponseDTO);
        }
        catch (KeyNotFoundException e)
        {
            throw new KeyNotFoundException(e.Message);
        }
    }

    /// <summary>
    /// Retrieves a user by login data.
    /// </summary>
    /// <param name="userLoginDTO">The user login data transfer object.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    public async Task<UserResponseDTO> GetUser(UserLoginDTO userLoginDTO)
    {
        try
        {
            User userFetch = await _userRepository.GetUser(userLoginDTO.Username);

            if (_passwordHasher.VerifyHashedPassword(userFetch, userFetch.Password, userLoginDTO.Password) == PasswordVerificationResult.Failed)
                throw new KeyNotFoundException("Invalid password.");

            UserResponseDTO userResponseDTO = _mapper.Map<UserResponseDTO>(userFetch);
            userResponseDTO.ProfilePictureUrl = Library.PrependUrl(userFetch.ProfilePictureUrl)!;

            return userResponseDTO;
        }
        catch (KeyNotFoundException e)
        {
            throw new KeyNotFoundException(e.Message);
        }
    }

    /// <summary>
    /// Creates a new user.
    /// </summary>
    /// <param name="user">The user creation data transfer object.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    public async Task<UserResponseDTO> CreateUser(UserCreateDTO user)
    {
        User userModel = _mapper.Map<User>(user);

        if (user.ProfilePictureUrl is not null)
            userModel.ProfilePictureUrl = await Library.SaveImage("Profiles", user.ProfilePictureUrl);

        userModel.Password = _passwordHasher.HashPassword(userModel, userModel.Password);

        return _mapper.Map<UserResponseDTO>(await _userRepository.CreateUser(userModel));
    }

    /// <summary>
    /// Updates an existing user.
    /// </summary>
    /// <param name="newUser">The user update data transfer object.</param>
    /// <param name="guid">The ID of the user to update.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    public async Task<UserResponseDTO> UpdateUser(UserUpdateDTO newUser, Guid guid)
    {
        User user = await _userRepository.GetUser(guid);

        if (_passwordHasher.VerifyHashedPassword(user, user.Password, newUser.Password) == PasswordVerificationResult.Failed)
            throw new KeyNotFoundException("Invalid password.");

        if (newUser.ProfilePictureUrl != null)
            user.ProfilePictureUrl = await Library.SaveImage("Profiles", newUser.ProfilePictureUrl);

        user.Username = newUser.Username;
        if (!string.IsNullOrEmpty(newUser.NewPassword))
            user.Password = _passwordHasher.HashPassword(user, newUser.NewPassword);
        user.FirstName = newUser.FirstName;
        user.LastName = newUser.LastName;
        user.PhoneNumber = newUser.PhoneNumber;
        user.Address = newUser.Address;
        user.IsFarmer = newUser.IsFarmer;

        UserResponseDTO userResponseDTO = _mapper.Map<UserResponseDTO>(await _userRepository.UpdateUser(user));

        userResponseDTO.ProfilePictureUrl = Library.PrependUrl(userResponseDTO.ProfilePictureUrl)!;

        return userResponseDTO;
    }

    /// <summary>
    /// Deletes a user by ID.
    /// </summary>
    /// <param name="id">The ID of the user to delete.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    public async Task<UserResponseDTO> DeleteUser(Guid id)
    {
        UserResponseDTO userResponseDTO = _mapper.Map<UserResponseDTO>(await _userRepository.DeleteUser(id));
        Library.DeleteImage(userResponseDTO.ProfilePictureUrl);
        userResponseDTO.ProfilePictureUrl = Library.PrependUrl(userResponseDTO.ProfilePictureUrl)!;
        return userResponseDTO;
    }
}