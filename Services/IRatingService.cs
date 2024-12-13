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
    /// <returns>A task that represents the asynchronous operation. The task result contains an enumerable collection of <see cref="RatingResponseDTO"/> objects.</returns>
    Task<IEnumerable<RatingResponseDTO>> GetRatings();

    /// <summary>
    /// Retrieves a rating by its identifier.
    /// </summary>
    /// <param name="ratingID">The unique identifier of the rating.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the <see cref="RatingResponseDTO"/> object.</returns>
    Task<RatingResponseDTO> GetRating(Guid ratingID);

    /// <summary>   
    /// Retrieves all ratings for a product.
    /// </summary>
    Task<IEnumerable<RatingFetchDTO>> GetRatingsByProduct(Guid productID);

    /// <summary>
    /// Creates a new rating.
    /// </summary>
    /// <param name="rating">The rating data transfer object containing the details of the rating to create.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the created <see cref="RatingResponseDTO"/> object.</returns>
    Task<RatingResponseDTO> CreateRating(RatingCreateDTO rating);

    /// <summary>
    /// Updates an existing rating.
    /// </summary>
    /// <param name="rating">The rating data transfer object containing the updated details of the rating.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the updated <see cref="RatingResponseDTO"/> object.</returns>
    Task<RatingResponseDTO> UpdateRating(RatingCreateDTO rating);

    /// <summary>
    /// /// Deletes a rating by its identifier.
    /// </summary>
    /// <param name="ratingID">The unique identifier of the rating to delete.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the deleted <see cref="RatingResponseDTO"/> object.</returns>
    Task<RatingResponseDTO> DeleteRating(Guid ratingID);
}