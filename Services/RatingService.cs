using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using AutoMapper;
using ANI.Libraries;

namespace ANI.Services;

/// <summary>
/// Service class for managing ratings.
/// </summary>
public class RatingService(IRatingRepository ratingRepository, IMapper mapper, IUserRepository userRepository, IProductRepository productRepository) : IRatingService
{
    private readonly IMapper _mapper = mapper;
    private readonly IRatingRepository _ratingRepository = ratingRepository;
    private readonly IUserRepository _userRepository = userRepository;
    private readonly IProductRepository _productRepository = productRepository;

    /// <summary>
    /// Retrieves all ratings.
    /// </summary>
    /// <returns>A collection of RatingResponseDTO objects.</returns>
    public async Task<IEnumerable<RatingResponseDTO>> GetRatings() =>
                _mapper.Map<IEnumerable<RatingResponseDTO>>(await _ratingRepository.GetRatings())
                    .Select(static rating =>
                        {
                            rating.ImageUrl = Library.PrependUrl(rating.ImageUrl);
                            return rating;
                        });

    /// <summary>
    /// 
    /// </summary>
    /// <param name="productID"></param>
    /// <returns></returns>
    public async Task<IEnumerable<RatingFetchDTO>> GetRatingsByProduct(Guid productID)
    {
        IEnumerable<Rating> ratings = await _ratingRepository.GetRatingsByProduct(productID);

        IEnumerable<RatingFetchDTO> fetchDTOs = _mapper.Map<IEnumerable<RatingFetchDTO>>(ratings);

        for (int i = 0; i < fetchDTOs.Count(); i++){
            fetchDTOs.ElementAt(i).Username = (await _userRepository.GetUser(ratings.ElementAt(i).UserID)).Username;
            fetchDTOs.ElementAt(i).ImageUrl = Library.PrependUrl(fetchDTOs.ElementAt(i).ImageUrl);
        }

        return fetchDTOs;
    }


    /// <summary>
    /// Retrieves a specific rating by its ID.
    /// </summary>
    /// <param name="ratingID">The ID of the rating to retrieve.</param>
    /// <returns>A RatingResponseDTO object.</returns>
    public async Task<RatingResponseDTO> GetRating(Guid ratingID)
    {
        RatingResponseDTO ratingDTO = _mapper.Map<RatingResponseDTO>(await _ratingRepository.GetRating(ratingID));
        ratingDTO.ImageUrl = Library.PrependUrl(ratingDTO.ImageUrl);
        return ratingDTO;
    }

    /// <summary>
    /// Creates a new rating.
    /// </summary>
    /// <param name="rating">The rating data to create.</param>
    /// <returns>A RatingResponseDTO object representing the created rating.</returns>
    public async Task<RatingResponseDTO> CreateRating(RatingCreateDTO rating)
    {
        Rating ratingForDB = _mapper.Map<Rating>(rating);

        if (rating.ImageUrl is not null)
            ratingForDB.ImageUrl = await Library.SaveImage("Ratings", rating.ImageUrl);

        ratingForDB.User = await _userRepository.GetUser(rating.UserID);
        ratingForDB.Product = await _productRepository.GetProduct(rating.ProductID);

        RatingResponseDTO ratingDTO = _mapper.Map<RatingResponseDTO>(await _ratingRepository.CreateRating(ratingForDB));
        ratingDTO.ImageUrl = Library.PrependUrl(ratingDTO.ImageUrl);

        return ratingDTO;
    }

    /// <summary>
    /// Updates an existing rating.
    /// </summary>
    /// <param name="rating">The rating data to update.</param>
    /// <returns>A RatingResponseDTO object representing the updated rating.</returns>
    public async Task<RatingResponseDTO> UpdateRating(RatingCreateDTO rating)
    {
        Rating updateProduct = await _ratingRepository.GetRating(rating.ProductID);

        updateProduct.Content = rating.Content;
        updateProduct.Stars = rating.Stars;

        if (rating.ImageUrl is not null)
        {
            if (updateProduct.ImageUrl is not null)
                Library.DeleteImage(updateProduct.ImageUrl);

            updateProduct.ImageUrl = await Library.SaveImage("Ratings", rating.ImageUrl);
        }

        RatingResponseDTO ratingDTO = _mapper.Map<RatingResponseDTO>(await _ratingRepository.UpdateRating(updateProduct));
        ratingDTO.ImageUrl = Library.PrependUrl(ratingDTO.ImageUrl);

        return ratingDTO;
    }

    /// <summary>
    /// Deletes an existing rating by its ID.
    /// </summary>
    /// <param name="ratingID">The unique identifier of the rating to delete.</param>
    /// <returns>A RatingResponseDTO object representing the deleted rating.</returns>
    public async Task<RatingResponseDTO> DeleteRating(Guid ratingID)
    {
        RatingResponseDTO ratingDTO = _mapper.Map<RatingResponseDTO>(await _ratingRepository.DeleteRating(ratingID));
        Library.DeleteImage(ratingDTO.ImageUrl);
        ratingDTO.ImageUrl = Library.PrependUrl(ratingDTO.ImageUrl);
        return ratingDTO;
    }
}