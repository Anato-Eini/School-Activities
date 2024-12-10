using ANI.DTO;

namespace ANI.Services;

/// <summary>
/// Interface for user service operations.
/// </summary>
public interface IUserService
{
    /// <summary>
    /// Retrieves all users.
    /// </summary>
    /// <returns>A task that represents the asynchronous operation. The task result contains a collection of UserSecDTO.</returns>
    Task<IEnumerable<UserSecDTO>> GetUsers();

    /// <summary>
    /// Retrieves a user by username.
    /// </summary>
    /// <param name="username">The username of the user.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    Task<UserResponseDTO> GetUser(string username);

    /// <summary>
    /// Retrieves a user by ID.
    /// </summary>
    /// <param name="id">The ID of the user.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    Task<UserResponseDTO> GetUser(Guid id);

    /// <summary>
    /// Authenticates a user.
    /// </summary>
    /// <param name="userLoginDTO">The user login data transfer object.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    Task<UserResponseDTO> Authenticate(UserLoginDTO userLoginDTO);

    /// <summary>
    /// Creates a new user.
    /// </summary>
    /// <param name="user">The user creation data transfer object.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    Task<UserResponseDTO> CreateUser(UserCreateDTO user);

    /// <summary>
    /// Updates an existing user.
    /// </summary>
    /// <param name="newUser">The user update data transfer object.</param>
    /// <param name="guid">The ID of the user to update.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    Task<UserResponseDTO> UpdateUser(UserUpdateDTO newUser, Guid guid);

    /// <summary>
    /// Deletes a user by ID.
    /// </summary>
    /// <param name="id">The ID of the user to delete.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains a UserResponseDTO.</returns>
    Task<UserResponseDTO> DeleteUser(Guid id);
}