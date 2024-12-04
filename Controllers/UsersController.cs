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
    public async Task<ActionResult<IEnumerable<UserDTO>>> GetUsers()
    {
        return Ok(await _userService.GetUsers());
    }

    // GET: api/UserDTOs/5
    [HttpGet("{id}")]
    public async Task<ActionResult<UserDTO>> GetUser(int id)
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
    public async Task<ActionResult<UserDTO>> PostUser(UserDTO user)
    {
        return Ok(await _userService.CreateUser(user));
    }

    // PUT: api/UserDTOs/5
    [HttpPut("{id}")]
    public async Task<ActionResult<UserDTO>> PutUser(int id, UserDTO user)
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
    public async Task<ActionResult<UserDTO>> DeleteUser(int id)
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