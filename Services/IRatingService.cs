using ANI.DTO;

namespace ANI.Services;

/// <summary>
/// Defines the contract for rating-related operations.
/// </summary>
public interface IRatingService
{
    /// <summary>
    /// Retrieves all ratings.
    /// </summary>
    /// <returns>A task that represents the asynchronous operation. The task result contains an enumerable collection of <see cref="RatingDTO"/> objects.</returns>
    Task<IEnumerable<RatingDTO>> GetRatings();

    /// <summary>
    /// Retrieves a rating by its identifier.
    /// </summary>
    /// <param name="ratingID">The unique identifier of the rating.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the <see cref="RatingDTO"/> object.</returns>
    Task<RatingDTO> GetRating(Guid ratingID);

    /// <summary>
    /// Creates a new rating.
    /// </summary>
    /// <param name="rating">The rating data transfer object containing the details of the rating to create.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the created <see cref="RatingDTO"/> object.</returns>
    Task<RatingDTO> CreateRating(RatingDTO rating);

    /// <summary>
    /// Updates an existing rating.
    /// </summary>
    /// <param name="rating">The rating data transfer object containing the updated details of the rating.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the updated <see cref="RatingDTO"/> object.</returns>
    Task<RatingDTO> UpdateRating(RatingDTO rating);

    /// <summary>
    /// Deletes a rating by its identifier.
    /// </summary>
    /// <param name="ratingID">The unique identifier of the rating to delete.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the deleted <see cref="RatingDTO"/> object.</returns>
    Task<RatingDTO> DeleteRating(Guid ratingID);
}