using Microsoft.AspNetCore.Mvc;
using ANI.DTO;
using ANI.Services;

namespace ANI.Controllers;

/// <summary>
/// Controller for managing ratings.
/// </summary>
[Route("api/[controller]")]
[ApiController]
public class RatingsController(IRatingService ratingService) : ControllerBase
{
    private readonly IRatingService _ratingService = ratingService;

    /// <summary>
    /// Gets all ratings.
    /// </summary>
    /// <returns>A list of RatingDTO objects.</returns>
    [HttpGet]
    public async Task<ActionResult<IEnumerable<RatingResponseDTO>>> GetRatings() => Ok(await _ratingService.GetRatings());


    /// <summary>
    /// Gets a rating by ID.
    /// </summary>
    /// <param name="id">The ID of the rating.</param>
    /// <returns>A RatingDTO object.</returns>
    [HttpGet("{id}")]
    public async Task<ActionResult<RatingResponseDTO>> GetRating(Guid id)
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


    [HttpGet("product/{productID}")]
    public async Task<IEnumerable<RatingFetchDTO>> GetRatingsByProduct(Guid productID) =>
                await _ratingService.GetRatingsByProduct(productID);

    /// <summary>
    /// Creates a new rating.
    /// </summary>
    /// <param name="rating">The RatingDTO object to create.</param>
    /// <returns>The created RatingDTO object.</returns>
    [HttpPost]
    public async Task<ActionResult<RatingResponseDTO>> PostRating([FromForm] RatingCreateDTO rating)
    {

        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        try
        {
            RatingResponseDTO createdRating = await _ratingService.CreateRating(rating);
            return CreatedAtAction(nameof(GetRating), new { id = createdRating.RatingID }, createdRating);
        }
        catch (InvalidOperationException e)
        {
            return Conflict(e.Message);
        }
    }

    /// <summary>
    /// Updates an existing rating.
    /// </summary>
    /// <param name="rating">The RatingDTO object to update.</param>
    /// <returns>The updated RatingDTO object.</returns>
    [HttpPut]
    public async Task<ActionResult<RatingResponseDTO>> PutRating([FromForm] RatingCreateDTO rating)
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

    /// <summary>
    /// Deletes a rating by ID.
    /// </summary>
    /// <param name="id">The ID of the rating to delete.</param>
    /// <returns>The deleted RatingDTO object.</returns>
    [HttpDelete("{id}")]
    public async Task<ActionResult<RatingResponseDTO>> DeleteRating(Guid id)
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
