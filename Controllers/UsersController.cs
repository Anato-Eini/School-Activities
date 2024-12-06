using Microsoft.AspNetCore.Mvc;
using ANI.DTO;
using ANI.Services;

namespace Ani.Controllers;

[Route("api/[controller]")]
[ApiController]
public class UserDTOsController(IUserService userService) : ControllerBase
{
    private readonly IUserService _userService = userService;

    // GET: api/UserDTOs
    [HttpGet]
    public async Task<ActionResult<IEnumerable<UserResponseDTO>>> GetUsers()
    {
        return Ok(await _userService.GetUsers());
    }

    // GET: api/UserDTOs/5
    [HttpGet("{id}")]
    public async Task<ActionResult<UserResponseDTO>> GetUser(int id)
    {
        try
        {
            return Ok(await _userService.GetUser(id));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    // POST: api/UserDTOs
    [HttpPost]
    public async Task<ActionResult<UserResponseDTO>> PostUser([FromBody] UserCreateDTO user)
    {
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        }

        UserResponseDTO createdUser = await _userService.CreateUser(user);
        return CreatedAtAction(nameof(GetUser), createdUser);
    }

    // PUT: api/UserDTOs/5
    [HttpPut("{id}")]
    public async Task<ActionResult<UserResponseDTO>> PutUser(int id, UserCreateDTO user)
    {
        try
        {
            return Ok(await _userService.UpdateUser(id, user));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    // DELETE: api/UserDTOs/5
    [HttpDelete("{id}")]
    public async Task<ActionResult<UserResponseDTO>> DeleteUser(int id)
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