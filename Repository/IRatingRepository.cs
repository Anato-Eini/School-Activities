using ANI.Models;

namespace ANI.Repository
{
    public interface IRatingRepository
    {
        public Task<IEnumerable<Rating>> GetRatings();
        public Task<Rating> GetRating(Guid ratingID);
        public Task<Rating> CreateRating(Rating rating);
        public Task<Rating> UpdateRating(Rating rating);
        public Task<Rating> DeleteRating(Guid ratingID);
    }
}
