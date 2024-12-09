using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using AutoMapper;

namespace ANI.Services
{
    public class RatingService(IRatingRepository ratingRepository, IMapper mapper) : IRatingService
    {
        private readonly IMapper _mapper = mapper;
        private readonly IRatingRepository _ratingRepository = ratingRepository;

        public async Task<IEnumerable<RatingDTO>> GetRatings()
        {
            return _mapper.Map<IEnumerable<RatingDTO>>(await _ratingRepository.GetRatings());

        }

        public async Task<RatingDTO> GetRating(Guid ratingID)
        {
            return _mapper.Map<RatingDTO>(await _ratingRepository.GetRating(ratingID));
        }

        public async Task<RatingDTO> CreateRating(RatingDTO rating)
        {
            return _mapper.Map<RatingDTO>(await _ratingRepository.CreateRating(_mapper.Map<Rating>(rating)));
        }

        public async Task<RatingDTO> UpdateRating(RatingDTO rating)
        {
            return _mapper.Map<RatingDTO> (await _ratingRepository.UpdateRating(_mapper.Map<Rating>(rating)));
        }

        public async Task<RatingDTO> DeleteRating(Guid ratingID)
        {
            Rating rating = await _ratingRepository.DeleteRating(ratingID);

            return new RatingDTO
            {
                RatingID = rating.RatingID,
                Name = rating.Name,
                Content = rating.Content,
                Stars = rating.Stars
            };
        }
    }
}
