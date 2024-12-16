using ANI.DTO;
using ANI.Models;

namespace ANI.Repository;

/// <summary>
/// Interface for managing ratings in the repository.
/// </summary>
public interface IRatingRepository
{
    /// <summary>
    /// Retrieves all ratings.
    /// </summary>
    /// <returns>A task that represents the asynchronous operation. The task result contains an enumerable collection of ratings.</returns>
    Task<IEnumerable<Rating>> GetRatings();

    /// <summary>
    /// Retrieves all ratings for a specific product.
    /// </summary>
    /// <param name="productID">The ID of the product to retrieve ratings for.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains an enumerable collection of ratings for the specified product.</returns>
    Task<IEnumerable<Rating>> GetRatingsByProduct(Guid productID);
    /// <summary>
    /// Retrieves a rating by its ID.
    /// </summary>
    /// <param name="ratingID">The ID of the rating to retrieve.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the rating with the specified ID.</returns>
    Task<Rating> GetRating(Guid ratingID);

    /// <summary>
    /// Creates a new rating.
    /// </summary>
    /// <param name="rating">The rating to create.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the created rating.</returns>
    Task<Rating> CreateRating(Rating rating);

    /// <summary>
    /// Updates an existing rating.
    /// </summary>
    /// <param name="rating">The rating to update.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the updated rating.</returns>
    Task<Rating> UpdateRating(Rating rating);

    /// <summary>
    /// Deletes a rating by its ID.
    /// </summary>
    /// <param name="ratingID">The ID of the rating to delete.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the deleted rating.</returns>
    Task<Rating> DeleteRating(Guid ratingID);
}