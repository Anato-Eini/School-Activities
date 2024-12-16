using ANI.Models;
using Microsoft.EntityFrameworkCore;

namespace ANI.Repository;

/// <summary>
/// Repository class for managing Rating entities in the database.
/// </summary>
public class RatingRepository(AniContext context) : IRatingRepository
{
    private readonly AniContext _context = context;

    /// <summary>
    /// Retrieves all ratings from the database.
    /// </summary>
    /// <returns>A list of all ratings.</returns>
    public async Task<IEnumerable<Rating>> GetRatings() 
            => await _context.Ratings.ToListAsync();

    /// <summary>
    /// Retrieves all ratings for a specific product.
    /// </summary>
    /// <param name="productID">The ID of the product to retrieve ratings for.</param>
    /// <returns>A list of all ratings for the specified product.</returns>
    /// <exception cref="KeyNotFoundException">Thrown when a product with the specified ID is not found.</exception>
    public async Task<IEnumerable<Rating>> GetRatingsByProduct(Guid productID) 
            => await _context.Ratings.Where(rating => rating.ProductID == productID).ToListAsync();

    /// <summary>
    /// Retrieves a specific rating by its ID.
    /// </summary>
    /// <param name="ratingID">The ID of the rating to retrieve.</param>
    /// <returns>The rating with the specified ID.</returns>
    /// <exception cref="KeyNotFoundException">Thrown when a rating with the specified ID is not found.</exception>
    public async Task<Rating> GetRating(Guid ratingID) 
            => await _context.Ratings.FindAsync(ratingID) ?? throw new KeyNotFoundException($"Product with id {ratingID} not found.");

    /// <summary>
    /// Creates a new rating in the database.
    /// </summary>
    /// <param name="rating">The rating to create.</param>
    /// <returns>The created rating.</returns>
    public async Task<Rating> CreateRating(Rating rating)
    {
        _context.Ratings.Add(rating);

        await _context.SaveChangesAsync();

        return rating;
    }

    /// <summary>
    /// Updates an existing rating in the database.
    /// </summary>
    /// <param name="rating">The rating to update.</param>
    /// <returns>The updated rating.</returns>
    public async Task<Rating> UpdateRating(Rating rating)
    {
        _context.Entry(rating).State = EntityState.Modified;

        await _context.SaveChangesAsync();

        return rating;
    }

    /// <summary>
    /// Deletes a rating from the database.
    /// </summary>
    /// <param name="ratingID">The ID of the rating to delete.</param>
    /// <returns>The deleted rating.</returns>
    /// <exception cref="KeyNotFoundException">Thrown when a rating with the specified ID is not found.</exception>
    public async Task<Rating> DeleteRating(Guid ratingID)
    {
        Rating rating = await _context.Ratings.FindAsync(ratingID) ?? throw new KeyNotFoundException($"Product with id {ratingID} not found.");

        _context.Ratings.Remove(rating);
        await _context.SaveChangesAsync();

        return rating;
    }
}