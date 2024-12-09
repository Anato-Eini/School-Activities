using ANI.Models;
using Microsoft.EntityFrameworkCore;

namespace ANI.Repository
{
    public class RatingRepository(AniContext context) : IRatingRepository
    {
        private readonly AniContext _context = context;

        public async Task<IEnumerable<Rating>> GetRatings()
        {
            return await _context.Ratings.ToListAsync();
        }

        public async Task<Rating> GetRating(Guid ratingID)
        {
            return await _context.Ratings.FindAsync(ratingID) ?? throw new KeyNotFoundException($"Product with id {ratingID} not found.");
        }

        public async Task<Rating> CreateRating(Rating rating)
        {
            _context.Ratings.Add(rating);

            await _context.SaveChangesAsync();

            return rating;
        }

        public async Task<Rating> UpdateRating(Rating rating)
        {
            Rating updateProduct = await _context.Ratings.FindAsync(rating.ProductID) ?? throw new KeyNotFoundException($"Product with id {rating.ProductID} not found.");

            updateProduct.Name = rating.Name;
            updateProduct.Content = rating.Content;
            updateProduct.Stars = rating.Stars;

            _context.Entry(updateProduct).State = EntityState.Modified;

            await _context.SaveChangesAsync();

            return rating;
        }

        public async Task<Rating> DeleteRating(Guid ratingID)
        {
            Rating rating = await _context.Ratings.FindAsync(ratingID) ?? throw new KeyNotFoundException($"Product with id {ratingID} not found.");

            _context.Ratings.Remove(rating);
            await _context.SaveChangesAsync();

            return rating;
        }
    }
}
