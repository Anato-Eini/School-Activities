using ANI.DTO;

namespace ANI.Services
{
    public interface IRatingService
    {
        Task<IEnumerable<RatingDTO>> GetRatings();
        Task<RatingDTO> GetRating(Guid ratingID);
        Task<RatingDTO> CreateRating(RatingDTO rating);
        Task<RatingDTO> UpdateRating(RatingDTO rating);
        Task<RatingDTO> DeleteRating(Guid ratingID);
    }
}
