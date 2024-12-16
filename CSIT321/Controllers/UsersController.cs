using Microsoft.AspNetCore.Mvc;
using ANI.DTO;
using ANI.Services;

namespace ANI.Controllers;

/// <summary>
/// Controller for handling user-related operations.
/// </summary>
[Route("api/[controller]")]
[ApiController]
public class UserDTOsController(IUserService userService) : ControllerBase
{
    private readonly IUserService _userService = userService;

    /// <summary>
    /// Retrieves a list of users.
    /// </summary>
    /// <returns>A list of UserSecDTO objects.</returns>
    [HttpGet]
    public async Task<ActionResult<IEnumerable<UserSecDTO>>> GetUsers()
    {
        return Ok(await _userService.GetUsers());
    }

    [HttpGet("farmers")]
    public async Task<ActionResult<IEnumerable<UserSecDTO>>> GetFarmers()
    {
        return Ok(await _userService.GetFarmers());
    }

    /// <summary>
    /// Authenticates a user based on the provided login details.
    /// </summary>
    /// <param name="user">The user login details.</param>
    /// <returns>A UserResponseDTO object if authentication is successful.</returns>
    [HttpPost("login")]
    public async Task<ActionResult<UserResponseDTO>> Login([FromBody] UserLoginDTO user)
    {
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        }

        try
        {
            return Ok(await _userService.Authenticate(user));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    /// <summary>
    /// Retrieves a user by their ID.
    /// </summary>
    /// <param name="userID">The ID of the user to retrieve.</param>
    /// <returns>A UserResponseDTO object if the user is found.</returns>
    [HttpGet("{userID}")]
    public async Task<ActionResult<UserResponseDTO>> GetUser(Guid userID)
    {
        try
        {
            return Ok(await _userService.GetUser(userID));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    /// <summary>
    /// Creates a new user.
    /// </summary>
    /// <param name="user">The user details to create.</param>
    /// <returns>The created UserResponseDTO object.</returns>
    [HttpPost]
    public async Task<ActionResult<UserResponseDTO>> PostUser([FromForm] UserCreateDTO user)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        UserResponseDTO createdUser = await _userService.CreateUser(user);

        return CreatedAtAction(nameof(GetUser), new { userID = createdUser.UserID }, createdUser);
    }

    /// <summary>
    /// Updates an existing user.
    /// </summary>
    /// <param name="user">The updated user details.</param>
    /// <param name="id">The ID of the user to update.</param>
    /// <returns>The updated UserResponseDTO object.</returns>
    [HttpPut("{id}")]
    public async Task<ActionResult<UserResponseDTO>> PutUser([FromForm] UserUpdateDTO user, Guid id)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        try
        {
            return Ok(await _userService.UpdateUser(user, id));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    /// <summary>
    /// Deletes a user by their ID.
    /// </summary>
    /// <param name="id">The ID of the user to delete.</param>
    /// <returns>The deleted UserResponseDTO object.</returns>
    [HttpDelete("{id}")]
    public async Task<ActionResult<UserResponseDTO>> DeleteUser(Guid id)
    {
        try
        {
            return Ok(await _userService.DeleteUser(id));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }
}