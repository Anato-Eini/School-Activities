using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using AutoMapper;
using ANI.Libraries;

namespace ANI.Services;

public class RatingService(IRatingRepository ratingRepository, IMapper mapper) : IRatingService
{
    private readonly IMapper _mapper = mapper;
    private readonly IRatingRepository _ratingRepository = ratingRepository;

    public async Task<IEnumerable<RatingResponseDTO>> GetRatings() => _mapper.Map<IEnumerable<RatingResponseDTO>>(await _ratingRepository.GetRatings())
                                                                    .Select(static rating =>
                                                                        {
                                                                            rating.ImageUrl = Library.PrependUrl(rating.ImageUrl);
                                                                            return rating;
                                                                        });

    public async Task<RatingResponseDTO> GetRating(Guid ratingID)
    {
        RatingResponseDTO ratingDTO = _mapper.Map<RatingResponseDTO>(await _ratingRepository.GetRating(ratingID));
        ratingDTO.ImageUrl = Library.PrependUrl(ratingDTO.ImageUrl);
        return ratingDTO;
    }

    public async Task<RatingResponseDTO> CreateRating(RatingCreateDTO rating)
    {
        Rating ratingForDB = _mapper.Map<Rating>(rating);

        if (rating.ImageUrl is not null)
            ratingForDB.ImageUrl = await Library.SaveImage("Ratings", rating.ImageUrl);

        RatingResponseDTO ratingDTO = _mapper.Map<RatingResponseDTO>(await _ratingRepository.CreateRating(ratingForDB)); 
        ratingDTO.ImageUrl = Library.PrependUrl(ratingDTO.ImageUrl);

        return ratingDTO;
    }

    public async Task<RatingResponseDTO> UpdateRating(RatingCreateDTO rating)
    {
        Rating updateProduct = await _ratingRepository.GetRating(rating.ProductID);

        updateProduct.Content = rating.Content;
        updateProduct.Stars = rating.Stars;

        if (rating.ImageUrl is not null)
        {
            if (updateProduct.ImageUrl is not null)
                Library.DeleteImage(updateProduct.ImageUrl);

            updateProduct.ImageUrl = await Library.SaveImage("Ratings",rating.ImageUrl);
        }

        RatingResponseDTO ratingDTO = _mapper.Map<RatingResponseDTO>(await _ratingRepository.UpdateRating(updateProduct));
        ratingDTO.ImageUrl = Library.PrependUrl(ratingDTO.ImageUrl);

        return ratingDTO;
    }

    public async Task<RatingResponseDTO> DeleteRating(Guid ratingID)
    {
        RatingResponseDTO ratingDTO = _mapper.Map<RatingResponseDTO>(await _ratingRepository.DeleteRating(ratingID));
        Library.DeleteImage(ratingDTO.ImageUrl);
        ratingDTO.ImageUrl = Library.PrependUrl(ratingDTO.ImageUrl);
        return ratingDTO;
    }
}