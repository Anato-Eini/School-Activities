using Microsoft.AspNetCore.Mvc;
using ANI.DTO;
using ANI.Services;

namespace ANI.Controllers;

[Route("api/[controller]")]
public class RatingsController(IRatingService ratingService) : ControllerBase
{
    private readonly IRatingService _ratingService = ratingService;

    // GET: api/RatingDTOs
    [HttpGet]
    public async Task<ActionResult<IEnumerable<RatingDTO>>> GetRatings()
    {
        return Ok(await _ratingService.GetRatings());
    }

    // GET: api/RatingDTOs/5
    [HttpGet("{id}")]
    public async Task<ActionResult<UserResponseDTO>> GetRating(Guid id)
    {
        try
        {
            return Ok(await _ratingService.GetRating(id));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    // POST: api/ProductDTOs
    [HttpPost]
    public async Task<ActionResult<RatingDTO>> PostRating([FromBody] RatingDTO rating)
    {
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        }

        RatingDTO createdRating = await _ratingService.CreateRating(rating);

        return CreatedAtAction(nameof(GetRating), new { id = createdRating.RatingID }, createdRating);
    }

    // PUT: api/RatingDTOs/5
    [HttpPut("{id}")]
    public async Task<ActionResult<RatingDTO>> PutRating([FromBody] RatingDTO rating)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        try
        {
            return Ok(await _ratingService.UpdateRating(rating));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    // DELETE: api/RatingDTOs/5
    [HttpDelete("{id}")]
    public async Task<ActionResult<RatingDTO>> DeleteRating(Guid id)
    {
        try
        {
            return Ok(await _ratingService.DeleteRating(id));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }
}
